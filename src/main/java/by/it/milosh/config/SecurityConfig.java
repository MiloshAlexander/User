package by.it.milosh.config;

import by.it.milosh.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity  // cancel all basic security settings
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private AuthenticationService authService;

    /**
     * Password stored in cookie.
     * Not expand. If there are some servers, request can be requested to server_1, but cookie are stored in server_2.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers().permitAll()        // allow to use this files without authentication (usually static files)
                .anyRequest().authenticated()     // other request must be secured
                .and()
                .formLogin().permitAll()          // add login-form
                .and()
                .logout().permitAll();
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * To work with database.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * Custom settings for username and password (redefine spring boot basic settings).
     * This is basic HTTP authentication. (Username and password get in each request ).
     * Password is passed in "Request headers", which is could decoder through Base64.getDecoder().decode(auth).
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }
     */

}
