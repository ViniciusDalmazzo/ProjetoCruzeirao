package sistema.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import sistema.modelos.Usuario;

public class UsuarioSistema extends User {
	private static final long serialVersionUID = 2289014715682414218L;
	
	private Usuario usuario;
	
	public UsuarioSistema(Usuario usuario, Collection <? extends GrantedAuthority> papel){
		super(usuario.getUsername(), usuario.getSenha(), true, true, true, true, papel);
		this.usuario = usuario;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
}
