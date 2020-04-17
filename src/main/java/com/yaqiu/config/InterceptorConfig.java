package com.yaqiu.config;

import com.yaqiu.interceptor.AuthenticationInterceptor;
import com.yaqiu.interceptor.PageVisitInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 页面访问拦截器规则 */
        registry.addInterceptor(pageVisitInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/error");

        /* 登录验证拦截器规则 */
        /*registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");*/
    }

    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Bean
    public PageVisitInterceptor pageVisitInterceptor() {
        return new PageVisitInterceptor();
    }
}
