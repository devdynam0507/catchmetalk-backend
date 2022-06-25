package dev.community.gdg.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.community.gdg.common.ResultCode;
import dev.community.gdg.configuration.jwt.JwtService;
import dev.community.gdg.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String MEMBER_ROLE_NAME = "MEMBER";

    @Autowired
    private MemberService memberService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtService jwtService;

    public void configure(WebSecurity web) {
        web.ignoring()
                .mvcMatchers(
                        "/error",
                        "/favicon.ico",
                        "/swagger-ui/**",
                        "/webjars/springfox-swagger-ui/**",
                        "/swagger-resources/**",
                        "/v1/api-docs",
                        "/h2-console/**",
                        "/hello"
                );
    }

    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/v1/**")
                .authorizeRequests()
                .antMatchers("/v1/members/login").permitAll()
                .anyRequest().hasAuthority(MEMBER_ROLE_NAME);
        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable();
        http.logout().disable();
        http.formLogin().disable();
        http.httpBasic().disable();
        http.requestCache().disable();
        http.addFilterAt(tokenPreAuthFilter(), AbstractPreAuthenticatedProcessingFilter.class);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.exceptionHandling()
                .authenticationEntryPoint(
                        (HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) -> {
                            response.setStatus(HttpStatus.UNAUTHORIZED.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            objectMapper.writeValue(
                                    response.getOutputStream(),
                                    // TODO: ApiResponse
                                    ApiResponse.failure(ResultCode.UNAUTHORIZED)
                            );
                        })
                .accessDeniedHandler(
                        (HttpServletRequest request,
                         HttpServletResponse response,
                         AccessDeniedException accessDeniedException) -> {
                            response.setStatus(HttpStatus.FORBIDDEN.value());
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            objectMapper.writeValue(
                                    response.getOutputStream(),
                                    // TODO: ApiResponse
                                    ApiResponse.failure(ResultCode.FORBIDDEN)
                            );
                        });
    }

    @Bean
    public TokenPreAuthFilter tokenPreAuthFilter() {
        TokenPreAuthFilter tokenPreAuthFilter = new TokenPreAuthFilter();
        tokenPreAuthFilter.setAuthenticationManager(new ProviderManager(preAuthTokenProvider()));
        return tokenPreAuthFilter;
    }

    @Bean
    public PreAuthTokenProvider preAuthTokenProvider() {
        return new PreAuthTokenProvider(memberService, jwtService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
