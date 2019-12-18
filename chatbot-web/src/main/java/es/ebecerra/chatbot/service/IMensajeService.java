package es.ebecerra.chatbot.service;

import java.util.List;

import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.model.Mensaje;

public interface IMensajeService {
	
	public void guardarMensaje(Mensaje mensaje);
	public List<Mensaje> findByConversacion(Conversacion conversacion);
	public Mensaje findById(Long id);
	public Long count();

}
