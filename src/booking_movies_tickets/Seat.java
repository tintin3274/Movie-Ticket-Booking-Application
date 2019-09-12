package booking_movies_tickets;

public class Seat {
    private String number;
    private String type;
    private double price;
    private boolean booked;

    public Seat(String number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.booked = false;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(){
        booked = true;
    }
}
