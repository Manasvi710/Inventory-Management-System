package com.BillManagementSystems.Service;

import com.BillManagementSystems.Service.SMSservices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class AppConfig {
    @Bean
    public SMSservices smsServices() {
        return new SMSservices();
    }

}
