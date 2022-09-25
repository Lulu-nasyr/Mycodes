package com.example.springcompass.logic;

public class SideOfTheWorld {

    public String sideName;
    public int startDegrees;
    public int endDegrees;

    boolean thisDegrees(int degrees){
        if (startDegrees < endDegrees) {
            return (startDegrees<=degrees & degrees<=endDegrees);
        }
        else {
            return (startDegrees<=degrees | degrees<=endDegrees);
        }
    }
}
