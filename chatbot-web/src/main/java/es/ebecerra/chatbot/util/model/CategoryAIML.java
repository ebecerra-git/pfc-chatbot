package es.ebecerra.chatbot.util.model;

public class CategoryAIML {

	private String topic;
	private String pattern;
	private String template;
	private String srai;
	private String that;
	private String think;
	private String condition;
	
	public CategoryAIML() {}
	
	public CategoryAIML(String pattern) {
		this.pattern = pattern;
	}
	
	public CategoryAIML(String pattern, String template) {
		this(pattern);
		this.template = template;
	}
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getThat() {
		return that;
	}
	public void setThat(String that) {
		this.that = that;
	}
	public String getThink() {
		return think;
	}
	public void setThink(String think) {
		this.think = think;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSrai() {
		return srai;
	}
	public void setSrai(String srai) {
		this.srai = srai;
	}
	
}
