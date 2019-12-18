package es.ebecerra.chatbot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import es.ebecerra.chatbot.model.Conversacion;

public interface IConversacionService {
	
	public void guardarConversacion(Conversacion conversacion);
	public Page<Conversacion> findAll(Pageable pageable);
	public Conversacion findById(Long id);
	public Long count();

}
