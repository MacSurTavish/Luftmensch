package com.luftmensch.admin.config;

import com.luftmensch.admin.handler.MyAuthenticationFailureHandler;
import com.luftmensch.admin.handler.MyAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //授权
        http.authorizeRequests()
                //放行"/index.html","login.html"，不需要认证
//                .antMatchers("/index.html","/login.html","/error.html").permitAll()
                .antMatchers("/**").permitAll()

                //放行静态资源
                .antMatchers("/css/**").permitAll()
                //所有请求都必须认证才能访问，必须登录
                .anyRequest().authenticated()

                .and()
                //formLogin：表单提交
                .formLogin()
                //自定义登陆页面
                .loginPage("/login.html")
                //自定义登录逻辑（绑定表单接口）
                .loginProcessingUrl("/login")
                .successHandler(new MyAuthenticationSuccessHandler("http://www.qinccy.com"))
                .failureHandler(new MyAuthenticationFailureHandler("/error.html"))

                .and()
                //关闭csrf防火墙
                .csrf().disable();
                /*.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
                    //放行这几种请求
                    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
                    //放行rest请求
                    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("^/rest/.*", null);
                    //放行druid
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        if (allowedMethods.matcher(request.getMethod()).matches()) {
                            return false;
                        }
                        String servletPath = request.getServletPath();
                        if (servletPath.contains("/druid")) {
                            return false;
                        }
                        return !unprotectedMatcher.matches(request);
                    }
                });*/
    }
}
