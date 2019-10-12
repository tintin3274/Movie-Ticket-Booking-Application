package Class;

import java.util.ArrayList;

public class Theater {
    private String name;
    private String systemType;
    private String seatType;
    private ArrayList<Round> roundsList = new ArrayList<>();


    public Theater(String name, String systemType, String seatType) {
        this.name = name;
        this.systemType = systemType;
        this.seatType = seatType;
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

    public String getSeatType() {
        return seatType;
    }

    public ArrayList<Round> getRoundsList() {
        return roundsList;
    }
}
