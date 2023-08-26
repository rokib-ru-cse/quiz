package com.bitspondon.quiz.presentation.security;

import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    MvcRequestMatcher.Builder mvcPattern(HandlerMappingIntrospector introspector) {
        return new MvcRequestMatcher.Builder(introspector);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
        String css = "/css/**";
        String js = "/js/**";
        String images = "/images/**";
        String resources = "/resources/**";
        String actuator = "/actuator/**";
        String togglz = "/togglz/**";
        String togglzConsole = "/togglz-console/**";
        String togglzConsoleIndex = "/togglz-console/index";

//        String[] adminPublicUrls = new String[]{AdminUrl.REGISTER, AdminUrl.LOGIN};
//        String[] swaggerPublicUrls = new String[]{"swagger-ui/**", "/swagger-ui.html", "/swagger-ui*/*swagger-initializer.js", "/swagger-ui*/**", "/webjars/**"};
//        String[] togglzUrl = new String[]{"/actuator", "/actuator/*", "/togglz-console", "/togglz-console/**", "/togglz*", "/togglz**"};
//        String[] clientPublicUrls = new String[]{ClientUrl.SIGNUP, ClientUrl.LOGIN};

        http.authorizeHttpRequests(auth -> {
                    auth.requestMatchers(mvc.pattern(css), mvc.pattern(js), mvc.pattern(images)).permitAll();
                    auth.requestMatchers(mvc.pattern(AdminUrl.ADMIN)).hasRole(Constant.ROLE_ADMIN);
                    auth.requestMatchers(mvc.pattern(actuator)).hasRole(Constant.ROLE_ADMIN);
                    auth.requestMatchers(mvc.pattern(togglz)).hasRole(Constant.ROLE_ADMIN);
                    auth.requestMatchers(mvc.pattern(togglzConsoleIndex)).hasRole(Constant.ROLE_ADMIN);
                    auth.requestMatchers(mvc.pattern(togglzConsole)).hasRole(Constant.ROLE_ADMIN);
                    auth.requestMatchers(antMatcher(ClientUrl.AUTH + "/**")).permitAll();
                    auth.anyRequest().authenticated();
                }).csrf(AbstractHttpConfigurer::disable)
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").loginProcessingUrl("/login").failureUrl("/login?error=true").permitAll())
                .logout(out -> out.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}



