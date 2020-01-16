package es.ebecerra.chatbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.ebecerra.chatbot.ai.bot.ConsejeroInformatico;
import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.service.IConversacionService;
import es.ebecerra.chatbot.service.IMensajeService;
import es.ebecerra.chatbot.util.paginator.PageRender;

@Controller
@Scope("session")
@RequestMapping("/admin/conversaciones")
public class AdminConversacionesController {
	
	@Autowired
	IConversacionService conversacionService;
	@Autowired
	IMensajeService mensajeService;
	
	ConsejeroInformatico consejeroInformatico = new ConsejeroInformatico();
	Conversacion conversacion;
	
	Authentication authentication;

	@GetMapping("")
	public String adminConversaciones(Model model, 
									  @RequestParam(name = "page", defaultValue = "1") Integer paginaActual, 
									  @RequestParam(name = "elements", defaultValue = "10") Integer elementos){
		ChatBotController.añadirAutenticacion(model);
		PageRender<Conversacion> pageRender = new PageRender<Conversacion>("/admin/conversaciones", elementos, conversacionService.count().intValue(), paginaActual);
		model.addAttribute("titulo", "Conversaciones");
		model.addAttribute("conversaciones", conversacionService.findAll(PageRequest.of(paginaActual-1, elementos)).getContent());
		model.addAttribute("page", pageRender);
		model.addAttribute("pag", "conversaciones");
		
		return "conversaciones";
	}
	
	@GetMapping("/{id}")
	public String getConversacion(Model model, @PathVariable Long id){
		
		ChatBotController.añadirAutenticacion(model);
		
		model.addAttribute("titulo", "Conversacion");
		model.addAttribute("conversacion", mensajeService.findByConversacion(conversacionService.findById(id)));
		model.addAttribute("numConversacion", id);
		model.addAttribute("hasNext", id < conversacionService.count());
		model.addAttribute("hasPrevious", id > 1);
		model.addAttribute("ultima", conversacionService.count());
		model.addAttribute("pag", "conversacion");
		return "conversacion";
	}
}
