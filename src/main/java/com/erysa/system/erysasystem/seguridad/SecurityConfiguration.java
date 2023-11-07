package com.erysa.system.erysasystem.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.erysa.system.erysasystem.servicio.UsuarioServicio;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioServicio usuarioServicio;
	
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(usuarioServicio);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(
		
				"/js/**", 
				"/css/**", 
				"/css/styles.css**", 
				"/img/**", 
				
				"/registro**", 
				"/recuperar/**",
				"/Recuperar**",
				"/forgot_password**",
				"/reset_password**",
				"/reset_password_form**",
				
				"/index**",
				"/**",
				"**",
				"/productoindex/{id}/**",
				"/ProductDetail.css/**",
				"/factura/ver/{id}","/factura/form/{clienteId}","/factura/form","/factura/eliminar/{id}").permitAll()
		.antMatchers().hasRole("Cliente")
		.antMatchers("/dashboard**","/listar/**","/ver/{id}","/form","/form/{id}","/eliminar/{id}",
				"/exportarPDF","/exportarExcel","/categorias","/categorias_form","/categorias_form").hasAuthority("Administrador")
		
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login")
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout")
		.permitAll();
		
		
	}
}
