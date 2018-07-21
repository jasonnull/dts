package io.github.jasonnull.dts.server.conf;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by dty on 2018/7/21.
 */
@Configuration
public class WebConfig {
    /**
     * 设置500，,404界面
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/error");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error");
            container.addErrorPages(error404Page, error500Page);
        };
    }

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
