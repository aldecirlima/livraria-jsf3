package br.com.caelum.livraria.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();

	public String efetuaLogin() {
//		System.out.println("Fazendo login do usu�rio: " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();

		boolean existe = new UsuarioDao().existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("livro").toString();
		}
		
//		O escopo de flash mant�m o componente "Messages" ativo por duas requisi��es,
//			sendo assim, a mensagem estar� ativa nesta e na pr�xima requisi��o.
		context.getExternalContext().getFlash().setKeepMessages(true);;
		context.addMessage(null, new FacesMessage("Usu�rio ou senha inv�lidos"));
		
//		Caso o usu�rio erre a senha, redirecionar de volta para login para limpar o cache de usu�rio e senha. 
		return new RedirectView("login").toString();
	}

	public String deslogar() {

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("usuarioLogado");

		return new RedirectView("login").toString();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
