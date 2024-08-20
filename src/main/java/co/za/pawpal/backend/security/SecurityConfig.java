package co.za.pawpal.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        //define query to retrieve a user by username
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT Username, Password, 1 AS Enabled FROM user WHERE Username = ?"
        );
        //define query to retrieve the roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT u.Username, r.Role_Name FROM role r JOIN user u ON r.UserID = u.UserID WHERE u.Username = ?"
        );

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/users").hasRole("Staff")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("Volunteer","Adopter")
                        .requestMatchers(HttpMethod.POST, "/api/users").hasRole("Staff")
                        .requestMatchers(HttpMethod.PUT, "/api/users").hasAnyRole("Staff","Volunteer","Adopter")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("Admin")

        );

        //Using basic authentication
        http.httpBasic(Customizer.withDefaults());
        //disable csrf
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

   @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
