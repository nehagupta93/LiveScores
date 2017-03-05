package net.cubeservices.livescoresapp;

/**
 * Created by neha on 4/17/2016.
 */
public class LeagueTable {

    int position;
    String teamName;
    int playedGames;
    int points;
    int goals;
    int goalsAgainst;
    int goalsDifference;
    int wins;
    int draws;
    int losses;

    public LeagueTable(int position, String teamName, int playedGames, int points, int goals, int goalsAgainst, int goalsDifference, int wins, int draws, int losses){
        this.position = position;
        this.teamName = teamName;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalsDifference = goalsDifference;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
    }

}
