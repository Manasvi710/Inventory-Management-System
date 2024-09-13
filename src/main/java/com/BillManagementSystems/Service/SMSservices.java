package com.BillManagementSystems.Service;

import ch.qos.logback.classic.Logger;
import com.BillManagementSystems.BillManagementSystemsApplication;
import com.BillManagementSystems.Model.Customer;
import com.BillManagementSystems.Model.Order;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class SMSservices {
    @Autowired
    CustomerService customerService;

    @Value("${TWILIO_ACCOUNT_SID}")
    private String accountSid;

    @Value("${TWILIO_AUTH_TOKEN}")
    private String authToken;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    private String outgoingSmsNumber;


    @PostConstruct
    public void initialize() {
        Twilio.init(accountSid, authToken);
    }

    public void sendSMS(Integer customerId, Order order, int productId){
        Customer customer=customerService.getByCustomerId(customerId);
        long phoneNumber = customer.getPhoneNo();
        BillManagementSystemsApplication b=new BillManagementSystemsApplication();
        b.sendSms("+91"+customer.getPhoneNo(), "+12564458556","Your order is register and your payment is pending...");
    }
    public void sendSMSPayment(Integer customerId, Order order, int productId){
        Customer customer=customerService.getByCustomerId(customerId);
        long phoneNumber = customer.getPhoneNo();
        BillManagementSystemsApplication b=new BillManagementSystemsApplication();
        b.sendSms("+91"+customer.getPhoneNo(), "+12564458556","Your order is register and your payment is done...");
    }

    public void  sendonwhatsapp(Integer customerId, Order order, int productId)
    {
        Customer customer=customerService.getByCustomerId(customerId);
        long phoneNumber = customer.getPhoneNo();
        BillManagementSystemsApplication b=new BillManagementSystemsApplication();
        b.sendSms("whatsapp:+91"+customer.getPhoneNo(), "whatsapp:+14155238886","Your order is register and your payment is pending...");

    }

    public void  sendonwhatsappPayment(Integer customerId, Order order, int productId)
    {
        Customer customer=customerService.getByCustomerId(customerId);
        long phoneNumber = customer.getPhoneNo();
        BillManagementSystemsApplication b=new BillManagementSystemsApplication();
        b.sendSms("whatsapp:+91"+customer.getPhoneNo(), "whatsapp:+14155238886","Your order is register and your payment is done...");

    }

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String to,String subject,String text){
        try {
            SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(text);
            javaMailSender.send(simpleMailMessage);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
