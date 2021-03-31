package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/myAccount").authenticated()  // Need to be authenticated
				.antMatchers("/myBalance").authenticated()
				.antMatchers("/myCards").authenticated()
				.antMatchers("/myLoans").authenticated()
				.antMatchers("/contact").permitAll()    // No Authentication required
				.antMatchers("/notices").permitAll()
				// For allowing Allowing h2-console
				.antMatchers("/h2-console/*").permitAll();
		// Disable CSRF (Cross Site Request Forgery) protection for h2
		http.csrf().disable();
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
