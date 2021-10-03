package com.onlineshop.security.config;

import com.onlineshop.security.JwtAuthenticationEntryPoint;
import com.onlineshop.security.filter.JwtTokenFilter;
import com.onlineshop.utils.Constants;
import com.onlineshop.utils.SCPHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint entryPoint;

    private final JwtTokenFilter tokenFilter;

    private final PasswordEncoder passwordEncoder;

    private final SCPHelper scpHelper;

    @Qualifier("userDetailsServiceImpl")
    private final UserDetailsService userDetailsServiceImpl;

    @Autowired
    public void configureGlobal(final AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsServiceImpl)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean()
            throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity httpSecurity)
            throws Exception {
        httpSecurity
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository
                            .withHttpOnlyFalse()
                    )
                .and()
                .requiresChannel()
                    .anyRequest()
                    .requiresSecure()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(entryPoint)
                .and()
                .formLogin()
                    .loginPage(Constants.LOGIN_PAGE)
                    .successForwardUrl(Constants.HOME_PAGE_URL)
                .and()
                .logout()
                    .logoutUrl(Constants.LOGOUT_URL)
                    .addLogoutHandler(new HeaderWriterLogoutHandler(
                            new ClearSiteDataHeaderWriter(
                                    ClearSiteDataHeaderWriter.Directive.ALL)
                            )
                    )
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl(Constants.LOGIN_PAGE)
                .and()
                .addFilterBefore(tokenFilter,
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                    .antMatchers("/swagger-ui.html/**")
                        .hasRole("ADMIN")
                .and()
                .headers()
                    .contentSecurityPolicy(scpHelper.getAllowList())
        ;
    }
}
