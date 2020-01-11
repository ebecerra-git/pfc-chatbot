package es.ebecerra.chatbot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ebecerra.chatbot.ai.bot.ConsejeroInformatico;
import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.model.Mensaje;
import es.ebecerra.chatbot.service.IConversacionService;
import es.ebecerra.chatbot.service.IMensajeService;

@Scope("session")
@Controller
public class ChatBotController {
	
	@Autowired
	IConversacionService conversacionService;
	@Autowired
	IMensajeService mensajeService;
	
	ConsejeroInformatico consejeroInformatico = new ConsejeroInformatico();
	Conversacion conversacion;
	
	private static Authentication authentication;

	@GetMapping({"", "/"})
	public String chat(Model model, @RequestParam(name = "action", defaultValue = "none") String action) {
		
		añadirAutenticacion(model);
		if(conversacion == null || action.contentEquals("delete")) {
			conversacion = null;
			model.addAttribute("conversacion", new ArrayList<Mensaje>());
		} else {
			model.addAttribute("conversacion", mensajeService.findByConversacion(conversacion));			
		}
		model.addAttribute("titulo", "El consejero informático");
		model.addAttribute("pag", "chat");
		
		return "chatbot";
	}
	
	@PostMapping("")
	public String enviarMensaje(@RequestParam("mensaje") String mensaje, Model model) {
		
		añadirAutenticacion(model);
		if(conversacion == null) {
			conversacion = new Conversacion();
			conversacionService.guardarConversacion(conversacion);
		}
		Mensaje mensajeBot = new Mensaje(conversacion, consejeroInformatico.enviarMensajeBot(mensaje), true);
		mensajeService.guardarMensaje(new Mensaje(conversacion, mensaje, false));
		mensajeService.guardarMensaje(mensajeBot);
		model.addAttribute("titulo", "El consejero informático");
		model.addAttribute("conversacion", mensajeService.findByConversacion(conversacion));
		model.addAttribute("pag", "chat");
		
		return "chatbot";
	}
	
	public static void añadirAutenticacion(Model model) {
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
