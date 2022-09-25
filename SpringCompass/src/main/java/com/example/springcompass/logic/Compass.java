package com.example.springcompass.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Compass {
    private static final Compass instance = new Compass();
    private final List<SideOfTheWorld> compass;

    public static Compass getInstance(){return instance;}

    private Compass(){
        compass = new ArrayList<>();
    }

    public void importFromMap (Map <String, String> importMap) {
        for (Map.Entry <String, String> entry: importMap.entrySet()){

            SideOfTheWorld side = new SideOfTheWorld();

            side.sideName = entry.getKey();

            String degreesStr = entry.getValue();
            int tmpCharPos = degreesStr.indexOf("-");
            side.startDegrees=Integer.parseInt(degreesStr.substring(0,tmpCharPos));
            side.endDegrees=Integer.parseInt(degreesStr.substring(tmpCharPos+1));

            compass.add(side);
        }
    }

    public String getSideByDegrees(int degrees){
        String sideName="UnknownDegrees";
        for (int i=0;i<= compass.size();i++){
            if (compass.get(i).thisDegrees(degrees)){
                sideName = compass.get(i).sideName;
                break;
            }
        }
        return sideName;
    }

}
