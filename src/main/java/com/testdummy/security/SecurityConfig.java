package com.testdummy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


            http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api/viewemployees").hasAnyAuthority("CAN_VIEW_ALL_EMPLOYEES")// Allow public access to certain endpoints
                .antMatchers(HttpMethod.GET,"/api/viewfeedback").hasAnyAuthority("CAN_VIEW_ALL_FEEDBACKS")
                .antMatchers(HttpMethod.POST,"/api/addnewemployee").hasAnyAuthority("CAN_ADD_NEW_EMPLOYEE")
                .antMatchers(HttpMethod.PUT,"/api/updateemployee").hasAnyAuthority("CAN_UPDATE_EMPLOYEE")
                .antMatchers(HttpMethod.DELETE,"/api/fireemployee").hasAnyAuthority("CAN_FIRE_EMPLOYEE")
                .anyRequest().authenticated()
                .and()
                .formLogin(Customizer.withDefaults())
                ;


//        for(String key : generateEndpointMap().keySet()){
//            registery.antMatchers()
//        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  NoOpPasswordEncoder.getInstance();
    }

    private Map<String, List<Endpoint>> generateEndpointMap() {
        Map<String, List<Endpoint>> endpointMap = new HashMap<>();
        List<Permission> permissions = permissionRepository.findAll();
        for (Permission permission : permissions) {
            List<Endpoint> endpointList = new ArrayList<>();
            for (String endpoint : permission.getEndpoints()) {
                try {
                    endpointList.add(new Endpoint(endpoint));
                } catch (Exception e) {
                    // Handle the exception
                }
            }
            endpointMap.put(permission.getName(), endpointList);
        }

        return endpointMap;
    }

}

