package es.ebecerra.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ebecerra.chatbot.model.Conversacion;

@Repository
public interface ConversacionRepository extends JpaRepository<Conversacion, Long> {

}
