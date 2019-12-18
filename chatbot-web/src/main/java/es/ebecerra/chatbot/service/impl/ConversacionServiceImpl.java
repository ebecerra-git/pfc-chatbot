package es.ebecerra.chatbot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.repository.ConversacionRepository;
import es.ebecerra.chatbot.service.IConversacionService;

@Service
public class ConversacionServiceImpl implements IConversacionService {

	@Autowired
	private ConversacionRepository conversacionRepository;
	
	@Override
	public void guardarConversacion(Conversacion conversacion) {
		conversacionRepository.save(conversacion);
	}
	
	@Override
	public Page<Conversacion> findAll(Pageable pageable) {
		return conversacionRepository.findAll(pageable);
	}

	@Override
	public Conversacion findById(Long id) {
		return conversacionRepository.findById(id).orElse(null);
	}

	@Override
	public Long count() {
		return conversacionRepository.count();
	}
}
