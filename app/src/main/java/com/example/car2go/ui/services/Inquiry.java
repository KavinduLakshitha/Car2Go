package com.example.car2go.ui.services;

public class Inquiry {
    String inquiryId;
    String customerName;
    String customerNIC;
    String customerEmail;
    String customerPhone;
    String customerInquiry;

    public Inquiry(){}

    public Inquiry(String inquiryId, String customerName, String customerNIC, String customerEmail, String customerPhone, String customerInquiry) {
        this.inquiryId = inquiryId;
        this.customerName = customerName;
        this.customerNIC = customerNIC;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.customerInquiry = customerInquiry;
    }

    public String getInquiryId() {
        return inquiryId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerNIC() {
        return customerNIC;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public String getCustomerInquiry() {
        return customerInquiry;
    }
}
