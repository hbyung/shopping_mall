package study.shopping_mall.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import study.shopping_mall.service.CustomOAuth2UserService;


@Configuration
// @EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

   private final AuthenticationConfiguration authenticationConfiguration;
   private final CustomOAuth2UserService customOAuth2UserService;

   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {

       return new BCryptPasswordEncoder();
   }

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

       return configuration.getAuthenticationManager();
   }

//    @Bean
//    public AccessDeniedHandler accessDeniedHandler() {
//        return (request, response, e) -> {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            response.setContentType("text/plain;charset=UTF-8");
//            response.getWriter().write("ACCESS DENIED");
//            response.getWriter().flush();
//            response.getWriter().close();
//        };
//    }
//
//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, e) -> {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("text/plain;charset=UTF-8");
//            response.getWriter().write("UNAUTHORIZED");
//            response.getWriter().flush();
//            response.getWriter().close();
//        };
//    }

   @Bean
   public RoleHierarchy roleHierarchy() {

       RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

       hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER\n" +
               "ROLE_USER > ROLE_BLACK");

       return hierarchy;
   }

   @Bean
   public SessionRegistry sessionRegistry() {
       return new SessionRegistryImpl();
   }// Register HttpSessionEventPublisher

   @Bean
   public static ServletListenerRegistrationBean httpSessionEventPublisher() {
       return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
   }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

//        //csrf disable
//        http
//                .csrf((auth) -> auth.disable());


//        //From 로그인 방식 disable
//        http
//                .formLogin((auth) -> auth.loginPage("/login")
//                        .loginProcessingUrl("/loginProc")
//                        .usernameParameter("email")
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/")
//                        .permitAll()
//                );

//        http
//                .logout((logout) ->
//                        logout.logoutUrl("/logout")
//                                .logoutSuccessUrl("/")
//                );

//        http
//                .exceptionHandling((handling) ->
//                        handling
// //                                .authenticationEntryPoint(authenticationEntryPoint())
// //                                .accessDeniedHandler(accessDeniedHandler())
//                                .accessDeniedPage("/errorPage")
//                );


//        //http basic 인증 방식 disable
//        http
//                .httpBasic((auth) -> auth.disable());


//        //중복로그인
//        http
//                .sessionManagement((auth) -> auth
//                        .invalidSessionUrl("/")
//                        .maximumSessions(2)
//                            .maxSessionsPreventsLogin(false)
//                             .expiredUrl("/login")
//                );

//        //세션 고정 보안
//        http
//                .sessionManagement((auth) -> auth
//                        .sessionFixation().changeSessionId());


//        http
//                .oauth2Login((oauth2) -> oauth2
//                                .loginPage("/login")
//                                .userInfoEndpoint((userInfoEndpointConfig ->
//                                        userInfoEndpointConfig.userService(customOAuth2UserService)))
//                );


//        //경로별 인가 작업
//        http
//                .authorizeHttpRequests((auth) -> auth
//                        .requestMatchers("/login/**", "/", "/join", "/error","/oauth2/**", "Member/**", "item/**", "ItemList" ).permitAll()
//                        .requestMatchers("/css/**","/js/**", "/assets/**", "/img/**", "/static/**").permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/cart/**","/order", "/orders", "/orders/**","user/**").hasRole("USER")
//                        .anyRequest().authenticated());


// //        http
// //                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);
// //
// //        http
// //                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil), UsernamePasswordAuthenticationFilter.class);


//        //세션 설정
// //        http
// //                .sessionManagement((session) -> session
// //                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        return http.build();
   }
}
