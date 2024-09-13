package com.BillManagementSystems;
import com.BillManagementSystems.Model.Product;
import com.BillManagementSystems.Repository.ProductRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class BillManagementSystemsApplication {


	public static void main(String[] args) {
		SpringApplication.run(BillManagementSystemsApplication.class, args);
		System.out.println("API is running...");
	}



	public void sendSms(String phonno, String outgoing, String message1) {
		Message message = Message.creator(
						new com.twilio.type.PhoneNumber(phonno),
						new com.twilio.type.PhoneNumber(outgoing),
						message1)
				.create();
	}
	@Autowired
	ProductRepository productRepository;

	@PostConstruct
	public void makeCSV(){
		try (FileWriter fileWriter = new FileWriter("G:\\Manasvi_AZG\\BillManagementSystems\\src\\main\\java\\com\\BillManagementSystems\\Service\\product.csv");
			 ICsvBeanWriter iCsvBeanWriter = new CsvBeanWriter(fileWriter, CsvPreference.STANDARD_PREFERENCE);) {

			String headers[] = {"productId", "productName", "productStock", "productPrice", "gst"};
			iCsvBeanWriter.writeHeader(headers);

			for (Product product : productRepository.findAll()) {
				iCsvBeanWriter.write(product, headers);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
        }
    }
}
