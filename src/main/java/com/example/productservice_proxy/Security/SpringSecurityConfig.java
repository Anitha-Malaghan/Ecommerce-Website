package com.example.productservice_proxy.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;




@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{
    /*
    * Authenticate every request
    * @*/

    /*@Bean
    public SecurityFilterChain deafaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/products").hasAuthority("admin")
                                .requestMatchers("/search").permitAll())
                .formLogin(Customizer.withDefaults()
                );
        http
                .cors((cors) -> cors.disable())
                .csrf((csrf) -> csrf.disable());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/search").permitAll()
                        .requestMatchers("/products/**").hasAuthority("admin")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());
        // Form login handles the redirect to the login page from the
        // authorization server filter chain

        return http.build();
    }*/


    //my code
    @Bean
    public SecurityFilterChain deafaultSecurityFilterChain(HttpSecurity http) throws Exception {
         /*http
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());*/

       /* http.authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/products").hasAuthority("admin")
                                .requestMatchers("/search").permitAll())
                .formLogin(Customizer.withDefaults()
                );*/
        http
                .cors((cors) -> cors.disable())
                .csrf((csrf) -> csrf.disable());
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/search").permitAll()
                        .requestMatchers("/products/**").hasAuthority("admin")
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults());
        // Form login handles the redirect to the login page from the
        // authorization server filter chain

        return http.build();
    }


}
