package com.codeup.svcs;

import com.codeup.Repositories.UserWithRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by violet on 6/26/17.
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UserWithRoles.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsLoader userDetails;

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/me") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
                .and()
                .authorizeRequests()
                .antMatchers("/", "/logout") // anyone can see the home and logout page
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
                .and()
                .authorizeRequests()
                .antMatchers("/posts/create") // only authenticated users can create ads
                .authenticated()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder());
    }


    //=====Add rules to restrict the access to a page by role.=====//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/posts/create") // only admin users can create ads
//                .hasAuthority("ADMIN")
//        ;
//    }

    //=====Check if the user has at least one valid role.=====//
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/posts/create") // either admin or sellers can create ads
//                .hasAnyAuthority("ADMIN", "SELLER")
//        ;
//    }

}
