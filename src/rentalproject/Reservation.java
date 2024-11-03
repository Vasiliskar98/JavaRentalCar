/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalproject;

import java.util.Date;


public class Reservation {
    
    private String code;
    private double price;
    private double priceperhour;

    private String vehicleType;
    
    private String currentDate;

    private String pickupDate;
    private String deliveryDate;
    
    //prepei na einai idio
    private String pickupPlace;
    private String deliveryplace;

    private String idnumber;
    private String drivinglicense;
    private int age;
    private String creditcard;
    private String paymethod;

    public Reservation(String code, double price, double priceperhour, String vehicleType, String currentDate, String pickupDate, String deliveryDate, String pickupPlace, String deliveryplace, String idnumber, String drivinglicense, int age, String creditcard, String paymethod) {
        this.code = code;
        this.price = price;
        this.priceperhour = priceperhour;
        this.vehicleType = vehicleType;
        this.currentDate = currentDate;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.pickupPlace = pickupPlace;
        this.deliveryplace = deliveryplace;
        this.idnumber = idnumber;
        this.drivinglicense = drivinglicense;
        this.age = age;
        this.creditcard = creditcard;
        this.paymethod = paymethod;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPriceperhour() {
        return priceperhour;
    }

    public void setPriceperhour(double priceperhour) {
        this.priceperhour = priceperhour;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getPickupPlace() {
        return pickupPlace;
    }

    public void setPickupPlace(String pickupPlace) {
        this.pickupPlace = pickupPlace;
    }

    public String getDeliveryplace() {
        return deliveryplace;
    }

    public void setDeliveryplace(String deliveryplace) {
        this.deliveryplace = deliveryplace;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getDrivinglicense() {
        return drivinglicense;
    }

    public void setDrivinglicense(String drivinglicense) {
        this.drivinglicense = drivinglicense;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public void setCreditcard(String creditcard) {
        this.creditcard = creditcard;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    @Override
    public String toString() {
        return "Reservation: " + "code=" + code + ", price=" + price + ", priceperhour=" + priceperhour + ", vehicleType=" + vehicleType + ", currentDate=" + currentDate + ", pickupDate=" + pickupDate + ", deliveryDate=" + deliveryDate + ", pickupPlace=" + pickupPlace + ", deliveryplace=" + deliveryplace + ", idnumber=" + idnumber + ", drivinglicense=" + drivinglicense + ", age=" + age + ", creditcard=" + creditcard + ", paymethod=" + paymethod + "";
    }

    
        
}
