package Class;

public class Seat {
    private String number;
    private String type;
    private double price;
    private boolean booked;
    private Account account;

    public Seat(String number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.booked = false;
        this.account = null;
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

    public void setBooked(Account account){
        booked = true;
        this.account = account;
    }
}
