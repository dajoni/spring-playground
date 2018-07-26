package dajoni.spring.playground;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private PlaygroundProperties properties;

    public SecurityConfiguration(PlaygroundProperties properties) {
        this.properties = properties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/admin/**").hasRole("ADMIN")
                .mvcMatchers("/samples/**").permitAll()
                .antMatchers("/actuator/**").hasRole("MONITOR")
                .anyRequest().denyAll()
                .and().csrf().disable()
                .formLogin().disable()
                .httpBasic();
        http.headers().frameOptions().disable();
    }

    @Bean
    public UserDetailsService configureInMemoryUsers() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        properties.getApplicationUsers().forEach(user -> {
            userDetailsManager.createUser(User.builder()
                    .username(user.getUsername())
                    .password(encoder.encode(user.getPassword()))
                    .roles(user.getRoles()).build());

        });
        return userDetailsManager;
    }
}
