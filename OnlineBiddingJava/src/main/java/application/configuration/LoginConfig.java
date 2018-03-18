//package application.configuration;
//
//import application.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//
//import java.util.Arrays;
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(basePackages = "application.service")
//public class LoginConfig extends WebSecurityConfigurerAdapter{
//
//    @Autowired
//    private UserService userService;
//
//    @Override
//    public void configure(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.csrf().disable();
//        httpSecurity.authorizeRequests().antMatchers("/", "/templates/**").permitAll()
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .antMatchers("/user/*").hasRole("USER")
//                .antMatchers("/login").permitAll()
//                .antMatchers(HttpMethod.OPTIONS, "**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().defaultSuccessUrl("http://localhost/home")
//                .loginPage("/login").usernameParameter("username").passwordParameter("password").permitAll()
//                .and().exceptionHandling().accessDeniedPage("/403");
//        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }
//
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//
//    public PasswordEncoder passwordEncoder(){
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder;
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
//        try {
//            auth.userDetailsService(this.userService)
//                    .passwordEncoder(passwordEncoder());
//        }catch (Exception ex){ ex.printStackTrace(); }
//    }
//
//}
