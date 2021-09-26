package com.example.car2go.data;

public class DriverDetails {
    private String driverDetailsId;
    private String reservationCode;
    private String driverName;
    private String driverEmail;
    private String nicNumber;
    private String licenseNumber;
    private String expiryDate;


    public DriverDetails(){

    }

    public DriverDetails(String driverDetailsId, String reservationCode, String driverName, String driverEmail, String nicNumber, String licenseNumber, String expiryDate) {
        this.driverDetailsId = driverDetailsId;
        this.reservationCode = reservationCode;
        this.driverName = driverName;
        this.driverEmail = driverEmail;
        this.nicNumber = nicNumber;
        this.licenseNumber = licenseNumber;
        this.expiryDate = expiryDate;

    }


    public String getDriverDetailsId() {
        return driverDetailsId;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverEmail() {
        return driverEmail;
    }

    public String getNicNumber() {
        return nicNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setDriverDetailsId(String driverDetailsId) {
        this.driverDetailsId = driverDetailsId;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setDriverEmail(String driverEmail) {
        this.driverEmail = driverEmail;
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber = nicNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
