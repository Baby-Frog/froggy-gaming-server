//package com.forggygaming.froggygamingserver.security;
//
//import com.forggygaming.froggygamingserver.filter.CustomAuthenticationFilter;
//
//import com.forggygaming.froggygamingserver.filter.CustomAuthorizationFilter;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import static org.springframework.http.HttpMethod.*;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final UserDetailsService userDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
//        http.csrf().disable();
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.authorizeRequests().antMatchers("/login/**", "/api/token/refresh/**").permitAll();
//        http.authorizeRequests().antMatchers(POST,"/api/user/save/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/v1/category/**", "/api/v1/order-detail/**").permitAll();
//        http.authorizeRequests().antMatchers(PUT, "/api/user/**", "/api/v1/customer/**", "/api/v1/orderdetail/**", "/api/v1/order/**")
//                .hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST,
//
//                        "/api/v1/customer/**",
//                        "/api/v1/orderdetail/**",
//                        "/api/v1/brand/**",
//                        "/api/v1/category/**",
//                        "/api/v1/product/**",
//                        "/api/v1/productdetails/**")
//                .hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().anyRequest().authenticated();
//
//        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
//        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//
//    @Bean
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//}
