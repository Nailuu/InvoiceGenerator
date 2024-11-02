package fr.nailu.invoicegenerator;

import fr.nailu.invoicegenerator.service.ParsingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class InvoiceGeneratorApplication {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: InvoiceGeneratorApplication <csv-file-path>");
            return;
        }

        ApplicationContext context = SpringApplication.run(InvoiceGeneratorApplication.class, args);

        ParsingService parsing = context.getBean(ParsingService.class);
        parsing.process(args[0]);

//        InvoiceService invoice = context.getBean(InvoiceService.class);
//        invoice.generate();
    }

}