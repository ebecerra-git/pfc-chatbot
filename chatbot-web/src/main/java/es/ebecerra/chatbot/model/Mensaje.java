package es.ebecerra.chatbot.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mensajes")
public class Mensaje implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private Conversacion conversacion;
	
	private String texto;
	private Boolean mensajeBot;
	private Date fechaEnvio;
	
	public Mensaje() {
		this.texto = "";
		this.mensajeBot = false;
		this.fechaEnvio = new Date();
	}
	
	public Mensaje(Conversacion conversacion, String mensaje, Boolean mensajeBot) {
		this.conversacion = conversacion;
		this.texto = mensaje;
		this.mensajeBot = mensajeBot;
		this.fechaEnvio = new Date();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Conversacion getConversacion() {
		return conversacion;
	}

	public void setConversacion(Conversacion conversacion) {
		this.conversacion = conversacion;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String mensaje) {
		this.texto = mensaje;
	}

	public Boolean getMensajeBot() {
		return mensajeBot;
	}

	public void setMensajeBot(Boolean mensajeBot) {
		this.mensajeBot = mensajeBot;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fecha) {
		this.fechaEnvio = fecha;
	}

	private static final long serialVersionUID = 5033945994025745836L;

}
