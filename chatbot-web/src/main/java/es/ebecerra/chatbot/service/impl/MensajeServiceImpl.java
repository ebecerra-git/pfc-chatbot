package es.ebecerra.chatbot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.model.Mensaje;
import es.ebecerra.chatbot.repository.MensajeRepository;
import es.ebecerra.chatbot.service.IMensajeService;

@Service
public class MensajeServiceImpl implements IMensajeService {

	@Autowired
	private MensajeRepository mensajeRepository;
	
	@Override
	public void guardarMensaje(Mensaje mensaje) {
		mensajeRepository.save(mensaje);
	}
	
	@Override
	public List<Mensaje> findByConversacion(Conversacion conversacion) {
		return mensajeRepository.findByConversacion(conversacion);
	}

	@Override
	public Mensaje findById(Long id) {
		return mensajeRepository.findById(id).orElse(null);
	}

	@Override
	public Long count() {
		return mensajeRepository.count();
	}
}
