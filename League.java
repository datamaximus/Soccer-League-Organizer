import java.io.Console;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.ArrayList;
import java.util.List;

public class League {
    Player[] mPlayers;
    List<Team> mTeams = new ArrayList<Team>();
    Console console = System.console();

    public League(Player[] players) {
        mPlayers = players;
    }

    public void organizeLeague() {
        console.printf("%nSOCCER LEAGUE ORGANIZER%n");
        console.printf("-----------------------%n");
        promptForOption();
    }

    private void displayMenu() {
        console.printf("1). Add team to the league%n" +
                "2). Add player to team%n" +
                "3). Option 3%n" +
                "4). Quit%n");
    }

    public void promptForOption () {
        int choice = 0;
        while (choice!=4) {
            displayMenu();
            choice = Integer.parseInt(console.readLine("%nChoose an option: "));
            switch (choice) {
                case 1:
                    if (mPlayers.length >= 11) {
                        Team team = promptForTeam();
                        mTeams.add(team);
                    } else {
                        console.printf("There are not enough players for a new team.%n" +
                                "Please choose another option.%n");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    choice = 4;
                    break;
            }
        }
    }

    public Team promptForTeam() {
        String teamName = console.readLine("Enter team name: ");
        String coachName = console.readLine("Enter coach name: ");
        return new Team(teamName, coachName);
    }
}
