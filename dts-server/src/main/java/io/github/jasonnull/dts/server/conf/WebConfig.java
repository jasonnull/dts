package io.github.jasonnull.dts.server.conf;

import io.github.jasonnull.dts.server.controller.interceptor.CookieInterceptor;
import io.github.jasonnull.dts.server.controller.interceptor.PermissionInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CookieInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns("/**");
    }

    //    /**
//     * 设置500，,404界面
//     */
//    @Bean
//    public ConfigurableServletWebServerFactory containerCustomizer() {
//        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
//        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error");
//        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error");
//
//        factory.addErrorPages(error404Page, error500Page);
//        return factory;
//    }

    /**
     * 设置中文编码过滤器
     */
    @Bean
    public FilterRegistrationBean filterRegistration() {
        FilterRegistrationBean frBean = new FilterRegistrationBean();
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        frBean.setFilter(characterEncodingFilter);
        frBean.addUrlPatterns("/*");
        return frBean;
    }
}
