package es.ebecerra.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

@Controller
public class ChatBotController {
	
	@Autowired
	IConversacionService conversacionService;
	@Autowired
	IMensajeService mensajeService;
	
	ConsejeroInformatico consejeroInformatico = new ConsejeroInformatico();
	Conversacion conversacion;

	@GetMapping("")
	public String chat(Model model) {
		
		conversacion = new Conversacion();
		conversacionService.guardarConversacion(conversacion);
		model.addAttribute("titulo", "El consejero informático");
		model.addAttribute("conversacion", mensajeService.findByConversacion(conversacion));
		
		return "chatbot";
	}
	
	@PostMapping("")
	public String enviarMensaje(@RequestParam("mensaje") String mensaje, Model model) {
		
		Mensaje mensajeBot = new Mensaje(conversacion, consejeroInformatico.enviarMensajeBot(mensaje), true);
		
		mensajeService.guardarMensaje(new Mensaje(conversacion, mensaje, false));
		mensajeService.guardarMensaje(mensajeBot);
		
		model.addAttribute("titulo", "El consejero informático");
		model.addAttribute("conversacion", mensajeService.findByConversacion(conversacion));
		
		return "chatbot";
	}
	
	@GetMapping("/admin/conversaciones")
	public String adminConversaciones(Model model, 
									  @RequestParam(name = "page", defaultValue = "1") Integer page, 
									  @RequestParam(name = "elements", defaultValue = "5") Integer elements){
		model.addAttribute("conversaciones", conversacionService.findAll(PageRequest.of(page-1, elements)).getContent());
		return "conversaciones";
	}
}
