package tsg.hopperbusjson;

/**
 * Created by terrelsimeongordon on 08/02/2017.
 */

public class HopperBus904Folder {
    private String busCode;
    private String busLocation;
    private String busTime;
    private int udateColour;

    public void setBusCode(String busCode){
        this.busCode=busCode;
    }

    public String getBusCode( ){
        return  busCode;
    }

    public void setBusLocation(String busLocation){
        this.busLocation=busLocation;
    }

    public String getBusLocation( ){
        return  busLocation;
    }

    public void setBusTime(String busTime){
        this.busTime=busTime;

    }

    public String getBusTime( ){
        return  busTime;
    }

    public void setUpdateColour(int udateColour){
        this.udateColour=udateColour;
    }

    public int getUpdateColour( ){
        return  udateColour;
    }

}
