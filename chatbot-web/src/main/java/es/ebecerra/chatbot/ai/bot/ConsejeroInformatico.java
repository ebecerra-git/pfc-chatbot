package es.ebecerra.chatbot.ai.bot;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alicebot.ab.Bot;
import org.alicebot.ab.Chat;
import org.springframework.core.io.ClassPathResource;

public class ConsejeroInformatico {
	
	public final String BOTNAME = "consejero-informatico";
	

	private Bot bot;
	private Chat chatSession;
	
	
	public ConsejeroInformatico() {
		try {
			this.bot = new Bot(BOTNAME, new ClassPathResource("static").getFile().getPath(), "aiml2csv");
		} catch (IOException e) {
			this.bot = new Bot();
			e.printStackTrace();
		}
		this.chatSession = new Chat(bot);
	}
	
	public String enviarMensajeBot(String mensaje) {
		
		return prepararRespuesta(chatSession.multisentenceRespond(prepararMensajeRecibido(mensaje)));
	}
	
	private String prepararMensajeRecibido(String mensaje) {
		Pattern patt = Pattern.compile("\\.[^ |.]+");
		Matcher mat = patt.matcher(mensaje);
		while (mat.find()) {
			mensaje = mensaje.replaceAll(mat.group(), mat.group().replace(".", "·"));
		}
		if(mensaje.contains(",") && (mensaje.toLowerCase().contains("hola") || 
									 mensaje.toLowerCase().contains("buenos dias") || 
									 mensaje.toLowerCase().contains("buenas tardes") || 
									 mensaje.toLowerCase().contains("que tal") || 
									 mensaje.toLowerCase().contains("que pasa") || 
									 mensaje.toLowerCase().startsWith("buenas"))) {
			mensaje = mensaje.replace(",", ".");
		}
		return mensaje;
	}
	
	private String prepararRespuesta(String mensaje) {
		mensaje = mensaje.replace("·", ".")
						 .replaceAll(" {4}", "</p><p class=\"mensaje\">")
						 .replaceAll("<br ?/>", "</p><p class=\"mensaje\">")
						 .replaceAll("<\\/href>", "\">").replaceAll("a><href>", "a href=\"")
						 .replaceAll("<\\/src>", "\">").replaceAll("img><src>", "img src=\"");
		return mensaje;
	}

public Bot getBot() {
		return bot;
	}

	public void setBot(Bot bot) {
		this.bot = bot;
	}

	public Chat getChatSession() {
		return chatSession;
	}

	public void setChatSession(Chat chatSession) {
		this.chatSession = chatSession;
	}
	
	

}
