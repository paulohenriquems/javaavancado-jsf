package br.com.allianz.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.allianz.dao.LivrosDao;
import br.com.allianz.models.Livro;

@ManagedBean(name="beanLivro")
@RequestScoped
public class LivrosBean {
	
	private Livro livro;
	
	public LivrosBean() {
		if (livro == null) {
			livro = new Livro();
		}
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	//metodo de acao (action) que quando executado retorna o redirecionamento conforme o resultado da execucao
	public String incluirLivro() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();

		try {
			new LivrosDao().IncluirLivro(livro);
			
			msg.setSummary("OK");
			msg.setDetail("Livro Incluido");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			
			return "sucesso";
		} catch (Exception e) {

			msg.setSummary("ERRO: ");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			
			return "erro";
		} finally {
			
		} 
		
	}

	//propriedade usada para apresentar a lista dos livros cadastrados
	public List<Livro> getListaLivros() throws Exception {
		return new LivrosDao().listarLivros();
	}
	
}
