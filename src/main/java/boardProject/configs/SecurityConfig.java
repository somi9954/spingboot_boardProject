package boardProject.configs;


import boardProject.models.member.LoginFailureHandler;
import boardProject.models.member.LoginSuccessHandler;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        http.formLogin()
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/member/login");
    */
        http.formLogin(f -> {
             f.loginPage("/member/login")
                    .usernameParameter("userId")
                    .passwordParameter("userPw")
                    .successHandler(new LoginSuccessHandler())
                    .failureHandler(new LoginFailureHandler());
        });
        http.logout(f -> {
            f.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/member/login");
        });

        http.authorizeRequests(f -> {
                f.requestMatchers(new AntPathRequestMatcher("/mypage/**")).authenticated() // 회원 전용 Url
                .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("ADMIN") // 관리자 전용
                .anyRequest().permitAll(); // 그 외 모든 페이지는 모든 회원이 접근 가능
        });

        http.exceptionHandling(f -> {
                        f.authenticationEntryPoint((req, res, e) -> {
                            String URI = req.getRequestURI();

                            if (URI.indexOf("/admin") != -1) { // 관리자 페이지
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "NOT AUTHORIZED");
                            } else { // 회원 전용 페이지
                                String redirectURL = req.getContextPath() + "/member/login";
                                res.sendRedirect(redirectURL);
                            }
                        });
                    });
        http.headers(f -> {
            f.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
        });

        /*http.headers().frameOptions().sameOrigin();*/

        return http.build();
    }
    /*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return w -> w.ignoring().requestMatchers("/css/**", "/js/**", "/images/**", "/errors/**");
    }
    */
    @Bean
    @Order(0)
    SecurityFilterChain staticEndpoints(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/css/**", "/js/**", "/images/**", "/errors/**","/h2-console/**")
                .headers((headers) -> headers.cacheControl((cache) -> cache.disable()))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
                .csrf(c -> {
                   c.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"));
                });
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
