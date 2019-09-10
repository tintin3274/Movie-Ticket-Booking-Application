package booking_movies_tickets;


import java.util.ArrayList;

public class Theater {
    private String name;
    private ArrayList<String> systemType = new ArrayList<>();
    private ArrayList<Round> roundsList = new ArrayList<>();

    public Theater(String name) {
        this.name = name;
    }

    public void addRound(Round round){
        roundsList.add(round);
    }

    public void addSystemType(String type){
        systemType.add(type);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSystemType() {
        return systemType;
    }

    public ArrayList<Round> getRoundsList() {
        return roundsList;
    }
}
