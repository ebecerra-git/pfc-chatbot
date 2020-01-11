package es.ebecerra.chatbot.util.paginator;

import java.util.ArrayList;
import java.util.List;

public class PageRender<T> {

	private String url;
	private Integer totalPaginas;
	private Integer numElementosPorPaginas;
	private Integer paginaActual;
	
	private List<PageItem> paginas;
	
	public PageRender(String url, Integer numElementosPorPagina, Integer totalElementos, Integer pagActual) {
		this.paginas = new ArrayList<PageItem>();
		this.url = url;
		this.numElementosPorPaginas = numElementosPorPagina;
		totalPaginas = (int) Math.ceil(totalElementos.doubleValue()/numElementosPorPagina.doubleValue());
		paginaActual = pagActual;
		
		int desde, hasta;
		if(totalPaginas <= numElementosPorPaginas) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if(paginaActual <= numElementosPorPaginas/2) {
				desde = 1;
				hasta = numElementosPorPaginas;
			} else if(paginaActual >= totalPaginas - numElementosPorPaginas/2){
				desde = totalPaginas - numElementosPorPaginas + 1;
				hasta = numElementosPorPaginas;
			} else {
				desde = paginaActual - numElementosPorPaginas/2;
				hasta = numElementosPorPaginas;
			}
		}
		
		for(int i = 0; i < hasta; i++) {
			paginas.add(new PageItem(desde + i, paginaActual == desde+i));
		}
	}
	
	public String getUrl() {
		return url;
	}

	public Integer getTotalPaginas() {
		return totalPaginas;
	}

	public Integer getPaginaActual() {
		return paginaActual;
	}

	public List<PageItem> getPaginas() {
		return paginas;
	}
	
	public boolean isFirst() {
		return paginaActual==1;
	}
	
	public boolean isLast() {
		return paginaActual==totalPaginas;
	}
	
	public boolean isHasNext() {
		return !isLast();
	}
	
	public boolean isHasPrevious() {
		return !isFirst();
	}
}
