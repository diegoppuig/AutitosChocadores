package autitoschocadores;

import java.util.*;

public class Ranking {
    private ArrayList<Players> players;

    public Ranking(ArrayList<Players> players) {
        this.players = players;
    }

    public void addPlayer(Players player) {
        players.add(player);
    }

    // Sort players by points
public void sortByPoints() {
    Collections.sort(players, (p1, p2) -> {
        // Compare by points in descending order
        int pointsComparison = Integer.compare(p2.getPoints(), p1.getPoints());
        
        // If points are the same, compare by alias in ascending order
        if (pointsComparison == 0) {
            return p1.getAlias().compareTo(p2.getAlias());
        }
        
        return pointsComparison;
    });
}


    public void displayRanking() {
        System.out.println("+------------+----------+--------+---------+----------+---------+");
        System.out.println("| Alias      | Games    | Wins   | Losses  | Forfeits | Points  |");
        System.out.println("+------------+----------+--------+---------+----------+---------+");
    
        for (int i = 0; i < players.size(); i++) {
            Players player = players.get(i);
            System.out.printf("| %-10s | %-8d | %-6d | %-7d | %-8d | %-7d |%n", 
                              player.getAlias(), player.getGames(), player.getWins(), 
                              player.getLosses(), player.getForfeits(), player.getPoints());
            System.out.println("+------------+----------+--------+---------+----------+---------+");
        }
    }
    
}