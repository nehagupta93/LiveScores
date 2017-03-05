package net.cubeservices.livescoresapp;

/**
 * Created by neha on 4/15/2016.
 */
public class FootballSeason {
    int id;
    String caption;
    int currentMatchDay;
    int numberOfMatchDays;
    int numberOfTeams;
    int numberofGames;

    public FootballSeason(int id, String caption, int currentMatchDay, int numberOfMatchDays, int numberofTeams, int numberofGames){
        this.id = id;
        this.caption = caption;
        this.currentMatchDay = currentMatchDay;
        this.numberOfMatchDays = numberOfMatchDays;
        this.numberOfTeams = numberofTeams;
        this.numberofGames = numberofGames;
    }
}
