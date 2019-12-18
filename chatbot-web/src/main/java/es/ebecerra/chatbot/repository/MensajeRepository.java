package es.ebecerra.chatbot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ebecerra.chatbot.model.Conversacion;
import es.ebecerra.chatbot.model.Mensaje;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Long> {
	
	List<Mensaje> findByConversacion(Conversacion conversacion);

}
