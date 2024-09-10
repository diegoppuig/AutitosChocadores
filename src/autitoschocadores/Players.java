package autitoschocadores;

import java.util.*;

public class Players {
    public static Scanner scanner = new Scanner(System.in); 
    private String alias; // no need to ask for player name just username?
    private String name;
    private int age;
    private int games;
    private int wins;
    private int losses;
    private int forfeits;
    private int points;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAlias() {
        return alias;
    }

    public int getGames() {
        return games;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getForfeits() {
        return forfeits;
    }

    public int getPoints() {
        return points;
    }

    public void setName(String playerName) {
        this.name = playerName;
    }

    public void setAge(int playerAge) {
        this.age = playerAge;
    }

    public void setAlias(String playerAlias) {
        this.alias = playerAlias;
    }

    public void setGames(int playerGames) {
        this.games = playerGames;
    }

    public void setWins(int playerWins) {
        this.wins = playerWins;
    }

    public void setLosses(int playerLosses) {
        this.losses = playerLosses;
    }

    public void setForfeits(int playerForfeits) {
        this.forfeits = playerForfeits;
    }

    public void setPoints(int playerPoints) {
        this.points = playerPoints;
    }
    
    public void addForfeit(){
        this.forfeits = this.forfeits + 1;
        this.points = this.points - 5;
        this.games = this.games + 1;
    }
    
    public void addWin(){
        this.wins = this.wins + 1;
        this.points = this.points + 10;
        this.games = this.games + 1;
    }
    
    public void addLoss(){
        this.losses = this.losses + 1;
        this.points = this.points - 2;
        this.games = this.games + 1;
    }
    
    public void changePoints(int gameStatus)
    {
        switch (gameStatus){
            case 1: //asked for help, subtract 1 point
                this.points = this.points - 1;
                break;
    }
    }

    public Players(String playerName, int playerAge, String playerAlias, int playerGames, int playerWins,
            int playerLosses, int playerForfeits, int playerPoints) {
        this.name = playerName;
        this.age = playerAge;
        this.alias = playerAlias;
        this.games = playerGames;
        this.wins = playerWins;
        this.losses = playerLosses;
        this.forfeits = playerForfeits;
        this.points = playerPoints;
    }

    @Override
    public String toString() {
        return " | " + this.getAlias() + " | " + this.getGames() + " | "
                + this.getWins() + " | " + this.getLosses() + " | "
                + this.getForfeits() + " | " + this.getPoints() + " | ";
    }
}

