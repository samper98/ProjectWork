package org.generation.italy.newEnteSportivo2.servlet.config;

import org.generation.italy.newEnteSportivo2.servlet.control.StaffGaraServlet2EE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Autowired
    private StaffGaraServlet2EE staffGaraServlet2EE;

    @Bean
    ServletRegistrationBean<StaffGaraServlet2EE> CittadinoServletRegistration() {
        ServletRegistrationBean<StaffGaraServlet2EE> registrationBean =
                new ServletRegistrationBean<>(staffGaraServlet2EE, "/ente-sportivo/*");

        return registrationBean;
    }

}