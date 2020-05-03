package com.yaqiu.config;

import com.yaqiu.interceptor.AuthenticationInterceptor;
import com.yaqiu.interceptor.VisitorLogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * @Description 登录验证拦截器
     * @author CiaoLee
     */
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    /**
     * @Description 访问者日志拦截器
     * @author CiaoLee
     */
    @Bean
    public VisitorLogInterceptor visitorLogInterceptor() {
        return new VisitorLogInterceptor();
    }

    /**
     * @Description 拦截器规则配置
     * @author CiaoLee
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /* 登录验证拦截器规则 */
        /*registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**");*/

        /* 访问者日志拦截器规则 */
        /*registry.addInterceptor(visitorLogInterceptor())
                .addPathPatterns("/*")
                .excludePathPatterns("/error");*/
    }
}
