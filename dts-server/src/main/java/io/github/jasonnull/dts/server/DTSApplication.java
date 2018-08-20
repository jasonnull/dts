package io.github.jasonnull.dts.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by dty on 2018/7/19.
 */
@SpringBootApplication
@MapperScan("io.github.jasonnull.dts.server.dao")
@ComponentScan("io.github.jasonnull.dts.server")
public class DTSApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DTSApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DTSApplication.class);
    }
}