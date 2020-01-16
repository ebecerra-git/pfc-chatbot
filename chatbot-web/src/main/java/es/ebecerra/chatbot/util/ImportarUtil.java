package es.ebecerra.chatbot.util;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import es.ebecerra.chatbot.util.model.CategoryAIML;

public class ImportarUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(ImportarUtil.class);
	public static final String pathAiml = "/static/bots/consejero-informatico/aiml";
	private static String srai;
	private static List<CategoryAIML> categorys;

	public static void importExcelFile(MultipartFile file) {
		try{
		
			File folderAiml = new ClassPathResource(pathAiml).getFile();
	        // Obtener el FileInputStream con el excel
	        InputStream excel = file.getInputStream();
	        
	        // Leer archivo excel
	        Workbook workbook = WorkbookFactory.create(excel);
	        
	        // Recorrer las páginas, filas y celdas
	        for(Sheet sheet : workbook) {
	        	
	            
	            LOG.debug(" + Hoja: "+sheet.getSheetName());
	            
	            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	    		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

	            Document doc = docBuilder.newDocument();
				
				Element aiml = doc.createElement("aiml");
				doc.appendChild(aiml);
				Attr version = doc.createAttribute("version");
				version.setValue("1.0");
				aiml.setAttributeNode(version);
	            
	            for(Row row : sheet) {
	                
	                LOG.debug(" ++ Row: "+row.getRowNum());
	                if(row.getRowNum() != 0) { 
	                	categorys = new LinkedList<CategoryAIML>();
	                    for(Cell cell : row) {
	                    	String [] preg;
	                        if (cell.getColumnIndex() == 0) {
	                        	preg = cell.getStringCellValue().split(" \\| ");
	                        	srai = preg[0];
	                        	Arrays.asList(preg).stream().forEach(pattern -> categorys.add(new CategoryAIML(pattern)));
	                        } else if (cell.getColumnIndex() == 1) { 
	                        	categorys.forEach(category -> {
	                        		if(categorys.indexOf(category) == 0) {
	                        			category.setTemplate(cell.getStringCellValue());
	                        		} else {
	                        			category.setSrai(srai);
	                        		}
	                        	});
	                        } else if (cell.getColumnIndex() == 2) { 
	                        	categorys.forEach( category -> category.setThat(cell.getStringCellValue()));
	                        } else if (cell.getColumnIndex() == 3) { 
	                        	categorys.forEach( category -> category.setTopic(cell.getStringCellValue()));
	                        }
	                    }
	                    for(CategoryAIML cat : categorys) {
	                    	aiml.appendChild(generarCategoriaAIML(doc, cat));
	                    }
	                }
	            }
	            TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(folderAiml.getAbsolutePath()+File.separator+sheet.getSheetName()+".aiml");
				transformer.transform(source, result);
	        }
	    } catch(Exception e) {
	        LOG.error(e.getMessage(), e);
	    }
	}
	
	private static Element generarCategoriaAIML(Document doc, CategoryAIML categoryAIML) {
		
		Element category = doc.createElement("category");
		if(categoryAIML.getPattern()!=null) {
			Element ePattern = doc.createElement("pattern");
			ePattern.setTextContent(categoryAIML.getPattern());
			category.appendChild(ePattern);
		}
		if(categoryAIML.getThat()!=null) {
			Element eThat = doc.createElement("that");
			eThat.setTextContent(categoryAIML.getThat());
			category.appendChild(eThat);
		}
		if(categoryAIML.getTemplate()!=null) {
			Element eTemplate = doc.createElement("template");
			eTemplate.setTextContent(categoryAIML.getTemplate());
			category.appendChild(eTemplate);
		} else if(srai!=null) {
			Element eTemplate = doc.createElement("template");
			Element eSrai = doc.createElement("srai");
			eSrai.setTextContent(srai);
			eTemplate.appendChild(eSrai);
			category.appendChild(eTemplate);
		}
		return category;
	}
	
}
