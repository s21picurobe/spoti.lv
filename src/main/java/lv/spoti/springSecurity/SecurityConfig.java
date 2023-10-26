package lv.spoti.springSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer{

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	            .authorizeHttpRequests()
	            .requestMatchers("/users").hasRole("ADMIN")
	            .requestMatchers("/login", "/", "/all", "/uploads/**", "js/renderSpotsUser.js", "/error/**", "/spot", "/filterspots").permitAll()
	            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
	            .anyRequest().authenticated();
	    		http.csrf().disable();
	    http
	            .formLogin()
	            .loginPage("/login");
	   

	    return http.build();
	}

    @SuppressWarnings("deprecation")
    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}