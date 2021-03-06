package es.ebecerra.chatbot.util.converter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.ebecerra.chatbot.util.model.CategoryAIML;

public class BotFileConverter {
	
	private static final Logger LOG = LoggerFactory.getLogger(BotFileConverter.class);
	
	public static Map<String, String> propertiesConverter(File file){
		Map<String, String> propiedades = new LinkedHashMap<String, String>();
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String linea;
			Integer indexSeparator = 0;
			while((linea = br.readLine()) != null) {
				if(linea.matches("\".*\".*\".*\"")) {
					indexSeparator = linea.indexOf(":", linea.indexOf("\"", 1)) != -1 ? linea.indexOf(":", linea.indexOf("\"", 1)) : linea.indexOf(",", linea.indexOf("\"", 1));
				} else {
					indexSeparator = linea.indexOf(":");
				}
				if(indexSeparator!=-1)
					propiedades.put(linea.substring(0, indexSeparator).trim(), linea.substring(indexSeparator+1).trim());
				else
					propiedades.put(linea, linea);
			}

			fr.close();
	    } catch(Exception e) {
	    	LOG.error("Excepcion leyendo fichero "+ file, e);
	    }
		
		return propiedades;
	}
	
	public static List<String> setsConverter(File file){
		List<String> propiedades = new LinkedList<String>();
		
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null)
				propiedades.add(linea.trim());

			fr.close();
	    } catch(Exception e) {
	    	LOG.error("Excepcion leyendo fichero "+ file, e);
	    }
		
		return propiedades;
	}
	
	public static List<CategoryAIML> aimlConverter(File file){
		
		
		List<CategoryAIML> aimlList = new LinkedList<CategoryAIML>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("aiml").item(0).getChildNodes();
			for(int i = 0; i < nList.getLength(); i++) {
				Node item = nList.item(i);
				añadirNodosAIML(item, aimlList, null);
			}
		} catch (Exception e) {
			LOG.error("Excepcion leyendo fichero "+ file, e);
		}
		return aimlList;
		
	}
	
	private static void añadirNodosAIML(Node item, List<CategoryAIML> aimlList, String topic) {
		if(item.getNodeName().contentEquals("category")) {
			CategoryAIML aiml = new CategoryAIML();
			NodeList hijos = item.getChildNodes();
			for(int i = 0; i < hijos.getLength(); i++) {
				Node hijo = hijos.item(i);
				if(hijo.getNodeName().contentEquals("pattern")) {
					aiml.setPattern(getTextNode(hijo).trim());
				} else if(hijo.getNodeName().contentEquals("template")) {
					aiml.setTemplate(getTextNode(hijo).trim());
				} else if(hijo.getNodeName().contentEquals("that")) {
					aiml.setThat(getTextNode(hijo).trim());
				} else if(hijo.getNodeName().contentEquals("think")) {
					aiml.setThink(getTextNode(hijo).trim());
				} else if(hijo.getNodeName().contentEquals("condition")) {
					aiml.setCondition(getTextNode(hijo).trim());
				} else if(topic != null && !topic.isEmpty()) {
					aiml.setTopic(topic);
				}
			}
			aimlList.add(aiml);
		} else if (item.getNodeName().contentEquals("topic")) {
			String topicValue = item.getAttributes().item(0).getTextContent();
			NodeList hijos = item.getChildNodes();
			for(int i = 0; i < hijos.getLength(); i++) {
				Node hijo = hijos.item(i);
				añadirNodosAIML(hijo, aimlList, topicValue);
			}
		}
	}
	
	private static String getTextNode(Node node) {
		String text = "";
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node item = node.getChildNodes().item(i);
			if(null == item.getNodeName() || item.getNodeName().contentEquals("#text")) {
				text += item.getTextContent()+" ";
			} else if (item.getNodeName().contentEquals("random")) {
				text += "&lt;"+item.getNodeName()+"&gt;";
				text += getTextNode(item);
				text += "&lt;/"+item.getNodeName()+"&gt; ";
			} else if (item.getNodeName().contentEquals("li")) {
				text += "&lt;"+item.getNodeName()+"&gt;";
				text += getTextNode(item);
				text += "&lt;/"+item.getNodeName()+"&gt; ";
			} else {
				text += "&lt;"+item.getNodeName()+"&gt; "+item.getTextContent()+" &lt;/"+item.getNodeName()+"&gt; ";
			}
		}
		return text;
	}

}
