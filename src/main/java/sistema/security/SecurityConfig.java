package sistema.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import sistema.dao.UsuarioDAO;
import sistema.modelos.Papel;
import sistema.modelos.Usuario;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
private static UserDetailsService usuarioService = new UsuarioSistemaService();
	
	public SecurityConfig() {
		 
	}
	
   @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

       auth.inMemoryAuthentication().withUser("adm").password("123").roles("ADMIN");
	   auth.userDetailsService(usuarioService);
        
    }

    //@Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	//Controle feito pelo JSF
    	http.csrf().disable();
    	    	
    	//P�gina de acesso negado
        http.exceptionHandling().accessDeniedPage("/acessonegado.xhtml");
        
        //Libera todos os recursos do JSF
        http.authorizeRequests().antMatchers("/javax.faces.resource/**").permitAll();
        http.authorizeRequests().antMatchers("/pages/welcome.xhtml").permitAll();
        http.authorizeRequests().antMatchers("/pages/cadastroUsuario.xhtml").permitAll();
        
        //Controla o acesso a p�gina protegida do adm        
        //http.authorizeRequests().antMatchers("/pages/adm/**").hasRole("ADMIN");
    	
    	//Login
    	http.formLogin().loginPage("/login.xhtml").permitAll()
		.defaultSuccessUrl("/pages/inicio.xhtml", true)
		.failureUrl("/login.xhtml?error=true")
		.usernameParameter("username")
		.passwordParameter("password");
    	
    	//Logout
        http.logout().logoutUrl("/logout")
                     .logoutSuccessUrl("/pages/welcome.xhtml");

        // Todas as requisi��es para partes internas da aplica��o devem ser autenticadas
		http.authorizeRequests().anyRequest().authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
        web.ignoring().antMatchers("/javax.faces.resource/**");
    }
}
