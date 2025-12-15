/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package booking_kamar;

/**
 *
 * @author Pongo
 */
import java.util.*;
import java.time.LocalDate;

public class HotelManager {
    private List<Room> rooms = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private int nextBookingId = 1;

    public HotelManager() {
        rooms.add(new SingleRoom(1));
        rooms.add(new DoubleRoom(2));
        rooms.add(new TwinRoom(3));
        rooms.add(new FamilyRoom(4));
        rooms.add(new SuiteRoom(5));
    }

    public List<Room> getRooms() { return rooms; }
    public List<Booking> getBookings() { return bookings; }

    public Booking addBooking(String custName, String phone, Room room, LocalDate checkIn,
                              int nights, boolean breakfast, boolean airport) {
        Customer c = new Customer(custName, phone);
        Booking b = new Booking(nextBookingId++, c, room, checkIn, nights, breakfast, airport);
        bookings.add(b);
        return b;
    }

    public boolean cancelBooking(int id) {
        return bookings.removeIf(b -> b.getId() == id);
    }
}
