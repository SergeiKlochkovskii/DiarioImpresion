package com.delonia.di.tomcat;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;

public class ServletContext {
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.setInitParameter("displayName", "Diario impreción");
    }

}
