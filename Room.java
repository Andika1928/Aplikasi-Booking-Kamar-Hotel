/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package booking_kamar;

/**
 *
 * @author Pongo
 */
public abstract class Room {
    protected int id;
    protected String name;
    protected double ratePerNight;
    protected int capacity;

    public Room(int id, String name, double ratePerNight, int capacity) {
        this.id = id;
        this.name = name;
        this.ratePerNight = ratePerNight;
        this.capacity = capacity;
    }

    public double calculatePrice(int nights) {
        return ratePerNight * nights;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getRatePerNight() { return ratePerNight; }
    public int getCapacity() { return capacity; }

    @Override
    public String toString() {
        return name + " - Rp" + (long)ratePerNight + "/night";
    }
}
