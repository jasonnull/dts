package io.github.jasonnull.dts.server.config;


import io.github.jasonnull.dts.server.controller.interceptor.CookieInterceptor;
import io.github.jasonnull.dts.server.controller.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class WebIntecerptor extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
    }
}
