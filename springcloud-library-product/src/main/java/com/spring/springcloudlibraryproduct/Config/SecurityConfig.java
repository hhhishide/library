package com.spring.springcloudlibraryproduct.Config;

import com.spring.springcloudlibraryproduct.Controller.WebSocketController;
import com.spring.springcloudlibraryproduct.Service.EmpService;
import com.spring.springcloudlibraryproduct.Service.libraryService;
import com.spring.springcloudlibraryproduct.Service.libraryServiceImpl;
import com.spring.springcloudlibraryproduct.pojo.employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private EmpService empService;

    private static List<Object> userlist;

    /*@Autowired
    private libraryServiceImpl libraryService;*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();
        http.formLogin().usernameParameter("username").passwordParameter("password")
                .loginPage("/library/login").loginProcessingUrl("/library/login")
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
                            , AuthenticationException exception) throws IOException, ServletException {
                        logger.info("进入认证失败处理方法!");
                        request.getSession().setAttribute("message","登录失败,账号或密码错误!");
                        response.sendRedirect("/library/dologin");
                    }
                }).successHandler(new AuthenticationSuccessHandler() {
                    // 方法上锁，确保每次只能有一个用户进入
                    @Override
                    public synchronized void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
                            throws IOException, ServletException {
                        logger.info("认证成功!");
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        String username = securityContext.getAuthentication().getName();
                        // 将当前连接用户保存到sessionsSet中
                        String id = UUID.randomUUID().toString();
                        id = id.replace("-", "");
                        WebSocketController.setSessionid(id);
                        WebSocketController.getSessionsSet().put(id,username);
                        // END
                        employee empByname = empService.getEmpByname(username);
                        //request.getSession().setAttribute("now_User",sessionRegistry.getAllPrincipals().get(sessionRegistry.getAllPrincipals().size()));
                        request.getSession().setAttribute("USER", empByname);
                        response.sendRedirect("/library/doindex");
                    }
        }).and().headers().frameOptions().sameOrigin()
                .and().sessionManagement().maximumSessions(5);
        http.csrf().disable();
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/library/dologin").deleteCookies("SESSION", "remember-me","JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("进入configure(AuthenticationManagerBuilder auth)...........");
        auth.userDetailsService(userDetailsService).passwordEncoder(new MyPasswordEncoder());
    }

}
