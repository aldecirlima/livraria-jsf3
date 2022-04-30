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
//		System.out.println("Fazendo login do usuário: " + this.usuario.getEmail());
		FacesContext context = FacesContext.getCurrentInstance();

		boolean existe = new UsuarioDao().existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return new RedirectView("livro").toString();
		}
		
//		O escopo de flash mantém o componente "Messages" ativo por duas requisições,
//			sendo assim, a mensagem estará ativa nesta e na próxima requisição.
		context.getExternalContext().getFlash().setKeepMessages(true);;
		context.addMessage(null, new FacesMessage("Usuário ou senha inválidos"));
		
//		Caso o usuário erre a senha, redirecionar de volta para login para limpar o cache de usuário e senha. 
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
