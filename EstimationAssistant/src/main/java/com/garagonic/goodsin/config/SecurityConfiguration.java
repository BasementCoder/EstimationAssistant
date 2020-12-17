package com.garagonic.estimationAssistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 *         Creation date: 6/25/17.
 * @since 1.0
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Autowired
//    MyUserDetailsService myUserDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {

        builder.inMemoryAuthentication().withUser("giuser").password("password") 
                .roles("USER","ADMIN");
        builder.inMemoryAuthentication().withUser("newuser").password("password")
                .roles("USER");


    }

    @Override
    protected void configure(HttpSecurity config) throws Exception {
        config.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
                .anyRequest().access("hasRole('USER')").and()
                .formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

}
