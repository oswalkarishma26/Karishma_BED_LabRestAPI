package com.collegefest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.collegefest.service.DomainUserDetailsService;

@EnableWebSecurity
@SuppressWarnings("deprecation")
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private DomainUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		// configure users
		authenticationManagerBuilder.userDetailsService(this.userDetailsService)
				.passwordEncoder(bcryptPasswordEncoder());
	}

	@Primary
	@Bean
	public PasswordEncoder bcryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().disable();
		httpSecurity.csrf().disable();
		httpSecurity.authorizeRequests()
				.antMatchers("/saveUser","/user","/registration/**", "user/registration/ADMIN/**","user/registration/USER/**", "/signin.js", "template/save.js").permitAll().and()
				.authorizeRequests().antMatchers("/edit/**","/delete/**").hasRole("ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "ADMIN").and()
				.authorizeRequests().antMatchers(HttpMethod.POST, "/**","/edit/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT, "/**","/edit/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/delete/**").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().and().httpBasic();

	}

}
