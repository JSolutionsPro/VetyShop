package com.apolosolutions.Apolo.Seguridad;

import com.apolosolutions.Apolo.Handler.CustomSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ConfiguracionDeSeguridad extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource fuenteDatos;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Autowired
    public void configuracionAut(AuthenticationManagerBuilder ok) throws Exception{
        ok.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(fuenteDatos)
                .usersByUsernameQuery("select correo, contrasena, estado from usuario where correo=?")
                .authoritiesByUsernameQuery("select correo, rol from usuario where correo=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests()
                .antMatchers("/VerEmpresas/**").hasRole("ADMIN")
                .antMatchers("/EditarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/AgregarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/EliminarEmpresa/**").hasRole("ADMIN")
                .antMatchers("/VerUsuarios/**").hasRole("ADMIN")
                .antMatchers("/AgregarUsuario").hasRole("ADMIN")
                .antMatchers("/EditarUsuario/**").hasRole("ADMIN")
                .antMatchers("/EliminarUsuario/**").hasRole("ADMIN")
                .antMatchers("/VerMovimientos").hasRole("ADMIN")
                .antMatchers("/VerMovimiento").hasRole("ADMIN")
                .antMatchers("/VerMovimientos/MiEmpresa/").hasAnyRole("ADMIN","USER")
                .antMatchers("/VerMovimientos/MisMovimientos/").hasAnyRole("ADMIN","USER")
                .antMatchers("/AgregarMovimiento/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/EditarMovimiento/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/EliminarMovimiento/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/Inicio").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin()
                .loginPage("/login").successHandler(customSuccessHandler)
                .usernameParameter("email")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/Inicio",true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/").permitAll()
                .and().exceptionHandling().accessDeniedPage("/Denegado");
    }
}
