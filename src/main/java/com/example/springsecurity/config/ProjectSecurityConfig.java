package com.example.springsecurity.config;

import com.example.springsecurity.filters.AuthoritiesLoggingAfterFilter;
import com.example.springsecurity.filters.AuthoritiesLoggingAtFilter;
import com.example.springsecurity.filters.RequestValidationBeforeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				// To Allow the Url and ports
				config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
				// To allow HTTP methods Like Get, Post etc * means allow all
				config.setAllowedMethods(Collections.singletonList("*"));
				// Whether user credentials are supported
				config.setAllowCredentials(true);
				//Set the list of headers that a pre-flight request can list as allowed for use during an actual request.
				config.setAllowedHeaders(Collections.singletonList("*"));
				//Configure how long, in seconds, the response from a pre-flight request can be cached by clients.
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and().csrf().ignoringAntMatchers("/h2-console/*").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.and().addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
				.addFilterAfter(new AuthoritiesLoggingAfterFilter() , BasicAuthenticationFilter.class)
				.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
				.authorizeRequests()
//				.antMatchers("/myAccount").authenticated()  // Need to be authenticated
//				.antMatchers("/myBalance").authenticated()
//				.antMatchers("/myCards").authenticated()
//				.antMatchers("/myAccount").hasAuthority("WRITE") // Need to be authenticated
//				.antMatchers("/myBalance").hasAuthority("READ")
//				.antMatchers("/myCards").hasAuthority("DELETE")
				.antMatchers("/myAccount").hasRole("USER")
				.antMatchers("/myBalance").hasAnyRole("USER","ADMIN")
				.antMatchers("/myCards").hasRole("ROOT")
				.antMatchers("/myLoans").authenticated()
				.antMatchers("/user").authenticated()
				.antMatchers("/contact").permitAll()    // No Authentication required
				.antMatchers("/notices").permitAll()
				// For allowing Allowing h2-console
				.antMatchers("/h2-console/*").permitAll();
		// Disable CSRF (Cross Site Request Forgery) protection for h2
//		http.csrf().disable();
		// Since the H2 database console runs inside a frame,
		// you need to enable this in Spring Security.
		http.headers().frameOptions().disable();
		http.formLogin();
		http.httpBasic();

//    	Denying All the Request
//		http.authorizeRequests().anyRequest().denyAll();

//      PermitAll
//    	http.authorizeRequests().anyRequest().permitAll();
	}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
//    	auth.inMemoryAuthentication()
//				.withUser("admin").password("12345").authorities("admin")
//				.and()
//				.withUser("user").password("1234").authorities("read")
//				.and()
//				.passwordEncoder(NoOpPasswordEncoder.getInstance());
//	}

	// For using InMemoryUserDetailsManager
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//		UserDetails user1 = User.withUsername("admin").password("12345").authorities("admin").build();
//		UserDetails user2 = User.withUsername("user").password("1234").authorities("read").build();
//		userDetailsManager.createUser(user1);
//		userDetailsManager.createUser(user2);
//		auth.userDetailsService(userDetailsManager);
//	}

//	@Bean
//	public UserDetailsService userDetailsService(DataSource datasource){
//		return new JdbcUserDetailsManager(datasource);
//	}
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return NoOpPasswordEncoder.getInstance();
//	}

	// Using
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
