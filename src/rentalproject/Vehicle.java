/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalproject;


public class Vehicle {
    private String fuel;
    private int horsepower;
    private int wheelsize;
    private int enginevolume;
    private double price;

    public Vehicle(String fuel, int horsepower, int wheelsize, int enginevolume, double price) {
        this.fuel = fuel;
        this.horsepower = horsepower;
        this.wheelsize = wheelsize;
        this.enginevolume = enginevolume;
        this.price = price;
    }
    
    
    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getWheelsize() {
        return wheelsize;
    }

    public void setWheelsize(int wheelsize) {
        this.wheelsize = wheelsize;
    }

    public int getEnginevolume() {
        return enginevolume;
    }

    public void setEnginevolume(int enginevolume) {
        this.enginevolume = enginevolume;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
