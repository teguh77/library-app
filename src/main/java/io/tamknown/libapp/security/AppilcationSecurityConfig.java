package io.tamknown.libapp.security;

import io.tamknown.libapp.auth.ApplicationUserDetailService;
import io.tamknown.libapp.jwt.JwtAuthenticaionVerifierFilter;
import io.tamknown.libapp.jwt.JwtConfig;
import io.tamknown.libapp.jwt.JwtUsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static io.tamknown.libapp.security.ApplicationUserRole.ADMIN;
import static io.tamknown.libapp.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppilcationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final ApplicationUserDetailService applicationUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public AppilcationSecurityConfig(ApplicationUserDetailService applicationUserDetailService,
                                     PasswordEncoder passwordEncoder,
                                     JwtConfig jwtConfig,
                                     SecretKey secretKey) {
        this.applicationUserDetailService = applicationUserDetailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernamePasswordAuthenticationFilter(
                        authenticationManager(),
                        jwtConfig,
                        secretKey))
                .addFilterAfter(new JwtAuthenticaionVerifierFilter(
                        jwtConfig,
                        secretKey), JwtUsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "/index", "/register", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(daoAuthenticationProvider());
//        auth.inMemoryAuthentication()
//                .withUser("dio")
//                .password(passwordEncoder.encode("pass"))
//                .roles(STUDENT.name())
//                .and()
//                .withUser("tama")
//                .password(passwordEncoder.encode("pass"))
//                .roles(ADMIN.name())
//                .authorities("ROLE_" + ADMIN.name(),
//                        "student:read",
//                        "student:write",
//                        "book:read",
//                        "book:write");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(applicationUserDetailService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
