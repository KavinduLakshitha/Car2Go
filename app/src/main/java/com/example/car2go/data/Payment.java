package com.example.car2go.data;

public class Payment {
    private String paymentId;
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String cardName;


    public Payment(){

    }

    public Payment(String paymentId, String cardNumber, String expiryDate, String cvv, String cardName) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardName = cardName;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public String getCardName() {
        return cardName;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
