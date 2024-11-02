package fr.nailu.invoicegenerator.property;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {
    private String CSVPath;
    private String jasperTemplatePath;

    public String getCSVPath() {
        return CSVPath;
    }

    public void setCSVPath(String CSVPath) {
        this.CSVPath = CSVPath;
    }

    public String getJasperTemplatePath() {
        return jasperTemplatePath;
    }

    public void setJasperTemplatePath(String jasperTemplatePath) {
        this.jasperTemplatePath = jasperTemplatePath;
    }
}