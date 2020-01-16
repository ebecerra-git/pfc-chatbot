package es.ebecerra.chatbot.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.ebecerra.chatbot.ai.bot.ConsejeroInformatico;
import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.service.IConversacionService;
import es.ebecerra.chatbot.service.IMensajeService;
import es.ebecerra.chatbot.util.ImportarUtil;
import es.ebecerra.chatbot.util.converter.BotFileConverter;

@Controller
@Scope("session")
@RequestMapping("/admin/bot")
public class AdminBotController {
	
	@Autowired
	IConversacionService conversacionService;
	@Autowired
	IMensajeService mensajeService;
	
	ConsejeroInformatico consejeroInformatico = new ConsejeroInformatico();
	Conversacion conversacion;
	
	Authentication authentication;
	
	@GetMapping("/aimls")
	public String adminAimls(Model model) throws Exception{
		ChatBotController.añadirAutenticacion(model);
		File folder = new ClassPathResource("/static/bots/consejero-informatico/aiml").getFile();
		model.addAttribute("titulo", "AIML's");
		model.addAttribute("pag", "aimls");
		if(folder.exists()) {
			List<String> enlaces = Arrays.asList(folder.listFiles()).stream().map(aiml -> aiml.getAbsolutePath().substring(aiml.getAbsolutePath().lastIndexOf(File.separator)+1)).collect(Collectors.toList());
			model.addAttribute("enlaces", enlaces);
		}
		return "aimls";
	}
	
	@PostMapping("/aimls")
	public String importExcel(Model model, @RequestParam(name = "action", defaultValue = "none") String action, @RequestParam("file") MultipartFile file) throws Exception{
		ChatBotController.añadirAutenticacion(model);
		if(action.contentEquals("import") && file != null) {
			ImportarUtil.importExcelFile(file);
		}
		model.addAttribute("titulo", "AIML's");
		model.addAttribute("pag", "aimls");
		File folder = new ClassPathResource("/static/bots/consejero-informatico/aiml").getFile();
		if(folder.exists()) {
			List<String> enlaces = Arrays.asList(folder.listFiles()).stream().map(aiml -> aiml.getAbsolutePath().substring(aiml.getAbsolutePath().lastIndexOf(File.separator)+1)).collect(Collectors.toList());
			model.addAttribute("enlaces", enlaces);
		}
		return "aimls";
	}
	
	@GetMapping("/aimls/{fileName}")
	public String getAimls(Model model, @PathVariable String fileName){
		
		ChatBotController.añadirAutenticacion(model);
		
		try {
			File file = new ClassPathResource("/static/bots/consejero-informatico/aiml/"+fileName).getFile();
			model.addAttribute("aimls", BotFileConverter.aimlConverter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "AIML");
		model.addAttribute("pag", "aiml");

		return "aiml";
	}
	
	@GetMapping("/sets")
	public String adminSets(Model model) throws Exception{
		ChatBotController.añadirAutenticacion(model);
		File folder = new ClassPathResource("/static/bots/consejero-informatico/sets").getFile();
		model.addAttribute("titulo", "Set's");
		model.addAttribute("pag", "sets");
		if(folder.exists()) {
			List<String> enlaces = Arrays.asList(folder.listFiles()).stream().map(file -> file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separator)+1)).collect(Collectors.toList());
			model.addAttribute("enlaces", enlaces);
		}
		return "sets";
	}
	
	@GetMapping("/sets/{fileName}")
	public String getSets(Model model, @PathVariable String fileName){
		
		ChatBotController.añadirAutenticacion(model);
		
		try {
			File file = new ClassPathResource("/static/bots/consejero-informatico/sets/"+fileName).getFile();
			model.addAttribute("sets", BotFileConverter.setsConverter(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "Set");
		model.addAttribute("pag", "set");

		return "set";
	}
	
	@GetMapping("/config-files")
	public String adminConfigFiles(Model model) throws Exception{
		ChatBotController.añadirAutenticacion(model);
		File folder = new ClassPathResource("/static/bots/consejero-informatico/config").getFile();
		model.addAttribute("titulo", "Ficheros de Configuración");
		model.addAttribute("pag", "config-files");
		if(folder.exists()) {
			List<String> enlaces = Arrays.asList(folder.listFiles()).stream().map(file -> file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf(File.separator)+1)).collect(Collectors.toList());
			model.addAttribute("enlaces", enlaces);
		}
		return "configFiles";
	}
	
	@GetMapping("/config-files/{fileName}")
	public String getConfigFiles(Model model, @PathVariable String fileName){
		
		ChatBotController.añadirAutenticacion(model);
		
		try {
			File file = new ClassPathResource("/static/bots/consejero-informatico/config/"+fileName).getFile();
			model.addAttribute("configFiles", BotFileConverter.propertiesConverter(file).entrySet());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("titulo", "Fichero de configuración");
		model.addAttribute("pag", "config-file");

		return "configFile";
	}
	
	public void añadirAutenticacion(Model model) {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> roles = authentication.getAuthorities().stream()
			     .map(r -> r.getAuthority()).collect(Collectors.toList());
		model.addAttribute("autenticado", !authentication.getAuthorities().stream().map(authority -> authority.getAuthority()).collect(Collectors.toList()).contains("ROLE_ANONYMOUS"));
		if(authentication.isAuthenticated()) {
			model.addAttribute("user", authentication.getName());
			model.addAttribute("roles", roles);
		}
	}
}
