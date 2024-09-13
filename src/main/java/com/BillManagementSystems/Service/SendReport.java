package com.BillManagementSystems.Service;

import jakarta.annotation.PostConstruct;
//import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SendReport {
    @Autowired
    JavaMailSender javaMailSender;
    @PostConstruct
    public void makeCSV(){
        try {
            MimeMessage mimeMessage=javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo("manasvimangrola@gmail.com");
            mimeMessageHelper.setFrom("mangrolamanasvi@gmail.com");
            mimeMessageHelper.setText("It's your Product Report");
            mimeMessageHelper.setSubject("Mailing Service");
            File file=new File("G:\\Manasvi_AZG\\BillManagementSystems\\src\\main\\java\\com\\BillManagementSystems\\Service\\product.csv");
            mimeMessageHelper.addAttachment(file.getName(), file);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
