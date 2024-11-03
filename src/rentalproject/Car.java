/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalproject;


public class Car extends Vehicle{
   private int seats;
   private int doors;
   private int luggagecapacity;

    public Car(int seats, int doors, int luggagecapacity, String fuel, int horsepower, int wheelsize, int enginevolume, double price) {
        super(fuel, horsepower, wheelsize, enginevolume, price);
        this.seats = seats;
        this.doors = doors;
        this.luggagecapacity = luggagecapacity;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getLuggagecapacity() {
        return luggagecapacity;
    }

    public void setLuggagecapacity(int luggagecapacity) {
        this.luggagecapacity = luggagecapacity;
    }


    
}
