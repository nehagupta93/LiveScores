package net.cubeservices.livescoresapp;

/**
 * Created by neha on 4/20/2016.
 */
public class CricketScore {

    String line1 = "";
    String line2 = "";
    String line3 = "";
    String line4 = "";

    public CricketScore(String score){

        if (score.equals("")){
            this.line4 = "Error";
        }else if (score.contains(" v ")){
            divide1(score);
        }else
            divide2(score);

    }

    public void divide1(String score){
        int colonIndex = score.indexOf(":");
        this.line1 = score.substring(0, colonIndex);
        int atIndex = score.indexOf(" at ");
        this.line3 = score.substring(colonIndex+2, atIndex);
        int commaIndex = score.indexOf(",");
        this.line2 = score.substring(atIndex+4, commaIndex);
        this.line4 = score.substring(commaIndex+2, score.length());
    }

    public void divide2(String score){

        int closeIndex = score.lastIndexOf(")");
        if (closeIndex+4<score.length())
            this.line4 = score.substring(closeIndex+4, score.length());
        int openIndex = score.lastIndexOf("(");
        int commaIndex = score.indexOf(",");
        this.line3 = score.substring(commaIndex+2, closeIndex);
        this.line2 = score.substring(openIndex+1, commaIndex);
        this.line1 = score.substring(0, openIndex);


    }

}
