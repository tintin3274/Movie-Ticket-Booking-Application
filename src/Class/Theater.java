package Class;

import java.util.ArrayList;
import java.util.Comparator;

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
        roundsList.sort(new Comparator<Round>() {
            @Override
            public int compare(Round o1, Round o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });
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
