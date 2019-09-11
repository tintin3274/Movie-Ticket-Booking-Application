package booking_movies_tickets;


import java.util.ArrayList;

public class Theater {
    private String name;
    private String systemType;
    private ArrayList<Round> roundsList = new ArrayList<>();

    public Theater(String name, String systemType) {
        this.name = name;
        this.systemType = systemType;
    }

    public void addRound(Round round){
        roundsList.add(round);
    }


    public String getName() {
        return name;
    }

    public String getSystemType() {
        return systemType;
    }

    public ArrayList<Round> getRoundsList() {
        return roundsList;
    }
}
