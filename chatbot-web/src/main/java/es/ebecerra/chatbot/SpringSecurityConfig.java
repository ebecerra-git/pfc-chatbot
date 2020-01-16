package es.ebecerra.chatbot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configuracion(AuthenticationManagerBuilder builder) throws Exception{
		
		PasswordEncoder encoder = passwordEncoder();
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
		
		builder.inMemoryAuthentication().withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
										.withUser(users.username("quique").password("12345").roles("USER"));
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/", "/login", "/css/**", "/js/**", "/img/**", "/jquery/**", "/bootstrap/**").permitAll()
								.antMatchers("/admin",  "/admin/comnversaciones", "/admin/comnversaciones/**").hasAnyRole("USER")
								.antMatchers("/admin", "/admin/bot/**").hasAnyRole("ADMIN")
								.anyRequest().authenticated()
								.and()
			.formLogin().permitAll().and()
			.logout().permitAll().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
			.httpBasic();
		
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
