package net.cubeservices.livescoresapp;

/**
 * Created by neha on 4/18/2016.
 */
public class CricketMatch {

    int unique_id;
    String description;
    String line1;
    String line2;

    public CricketMatch(int unique_id, String description){
        this.unique_id = unique_id;
        this.description = description;

        int pos = description.indexOf(" v ");
        this.line1 = description.substring(0,pos);
        this.line2 = description.substring(pos+3);
    }
}
