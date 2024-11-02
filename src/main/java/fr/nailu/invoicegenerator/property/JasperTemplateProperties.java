package fr.nailu.invoicegenerator.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "template")
public class JasperTemplateProperties {
    private String number;
    private String createdAtDate;
    private String dueDate;
    private double hourlyRate;
    private String recipientName;
    private String recipientAddressStreet;
    private String recipientAddressPostalCity;
    private String title;
    private String subTitle;
    private String addressStreet;
    private String addressPostalCity;
    private String phone;
    private String email;
    private String vat;
    private String rcs;
    private String businessLicence;
    private String bankName;
    private String iban;
    private String bic;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreatedAtDate() {
        return createdAtDate;
    }

    public void setCreatedAtDate(String createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddressStreet() {
        return recipientAddressStreet;
    }

    public void setRecipientAddressStreet(String recipientAddressStreet) {
        this.recipientAddressStreet = recipientAddressStreet;
    }

    public String getRecipientAddressPostalCity() {
        return recipientAddressPostalCity;
    }

    public void setRecipientAddressPostalCity(String recipientAddressPostalCity) {
        this.recipientAddressPostalCity = recipientAddressPostalCity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressPostalCity() {
        return addressPostalCity;
    }

    public void setAddressPostalCity(String addressPostalCity) {
        this.addressPostalCity = addressPostalCity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getRcs() {
        return rcs;
    }

    public void setRcs(String rcs) {
        this.rcs = rcs;
    }

    public String getBusinessLicence() {
        return businessLicence;
    }

    public void setBusinessLicence(String businessLicence) {
        this.businessLicence = businessLicence;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }
}
