package br.com.bagarote.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	private static final String[] PUBLIC = {"https://auth.bagarote.com.br/auth/realms/bagarote-desafio/protocol/openid-connect/token"};
	
	private static final String[] VENDEDOR_OU_ADMINISTRADOR = {"/api/**"};
	
	private static final String[] VENDEDOR = {"/api/venda/**"};
	
	private static final String[] ADMINISTRADOR = {"/api**"};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
				.and()
				.authorizeRequests()
				.antMatchers(PUBLIC).permitAll()
				.antMatchers(HttpMethod.GET, VENDEDOR_OU_ADMINISTRADOR).permitAll()
				.antMatchers(HttpMethod.POST, VENDEDOR).hasRole("VENDEDOR")
				.antMatchers(ADMINISTRADOR).hasRole("ADMINISTRADOR")
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.oauth2ResourceServer()
				.jwt().jwtAuthenticationConverter(getJwtAuthenticationConverter());

	}

	private JwtAuthenticationConverter getJwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
		converter.setAuthoritiesClaimName("authorities");
		converter.setAuthorityPrefix("");
		JwtAuthenticationConverter authenticationConverter = new JwtAuthenticationConverter();
		authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);
		return authenticationConverter;
	}



	
	
}