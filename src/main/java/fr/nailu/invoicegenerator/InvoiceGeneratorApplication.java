package fr.nailu.invoicegenerator;

import fr.nailu.invoicegenerator.service.JasperService;
import fr.nailu.invoicegenerator.service.ParsingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InvoiceGeneratorApplication {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = SpringApplication.run(InvoiceGeneratorApplication.class, args);

        ParsingService parsing = context.getBean(ParsingService.class);
        parsing.process();

        JasperService jasper = context.getBean(JasperService.class);
        jasper.generateInvoice();
    }

}