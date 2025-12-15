/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking_kamar;

/**
 *
 * @author Pongo
 */
import java.time.LocalDate;

public class Booking implements Discountable {
    private int id;
    private Customer customer;
    private Room room;
    private LocalDate checkIn;
    private int nights;
    private boolean breakfast;   // biaya per orang per malam
    private boolean airportPickup; // flat fee
    private double total;

    public static final double BREAKFAST_PER_PERSON = 40_000;
    public static final double AIRPORT_PICKUP_FEE = 120_000;

    public Booking(int id, Customer customer, Room room, LocalDate checkIn, int nights,
                   boolean breakfast, boolean airportPickup) {
        this.id = id;
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.nights = nights;
        this.breakfast = breakfast;
        this.airportPickup = airportPickup;
        this.total = calculateTotal();
    }

    public double calculateTotal() {
        double roomCost = room.calculatePrice(nights);
        double service = 0;
        if (breakfast) {
            service += BREAKFAST_PER_PERSON * room.getCapacity() * nights;
        }
        if (airportPickup) {
            service += AIRPORT_PICKUP_FEE;
        }
        double subtotal = roomCost + service;
        double after = applyDiscount(subtotal);
        this.total = after;
        return total;
    }

    @Override
    public double applyDiscount(double amount) {
        // sederhana: diskon 10% untuk >=7 malam, 5% untuk Suite
        if (nights >= 7) return amount * 0.90;
        if (room instanceof SuiteRoom) return amount * 0.95;
        return amount;
    }

    // getters
    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public int getNights() { return nights; }
    public boolean isBreakfast() { return breakfast; }
    public boolean isAirportPickup() { return airportPickup; }
    public double getTotal() { return total; }
}
