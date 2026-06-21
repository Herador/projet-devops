package com.example.rentalservice.entities;

public class Rental {
    private String bookingId;
    private String plateNumber;
    private String customerName;
    private int days;
    private double totalPrice;

    public Rental() {
    }

    public Rental(String bookingId, String plateNumber, String customerName, int days, double totalPrice) {
        this.bookingId = bookingId;
        this.plateNumber = plateNumber;
        this.customerName = customerName;
        this.days = days;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "bookingId='" + bookingId + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", days=" + days +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
