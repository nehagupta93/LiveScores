package net.cubeservices.livescoresapp;

/**
 * Created by neha on 4/16/2016.
 */
public class FootballFixture {

    String status;
    String homeTeam;
    String awayTeam;
    int homeTeamGoals;
    int awayTeamGoals;

    public FootballFixture(String status, String homeTeam, String awayTeam, int homeTeamGoals, int awayTeamGoals){
        this.status = status;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

}
