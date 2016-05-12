import java.io.Console;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class League {
    Set<Player> mPlayers;
    Set<Team> mTeams = new TreeSet<Team>();
    Console console = System.console();

    public League(Player[] players) {
        mPlayers= new TreeSet(Arrays.asList(players));
    }

    public void organizeLeague() {
        console.printf("%nSOCCER LEAGUE ORGANIZER%n");
        console.printf("-----------------------%n");
        promptForOption();
    }

    private void displayMenu() {
        console.printf("%n1). Add team to the league%n" +
                "2). Add player to team%n" +
                "3). Remove player from team%n" +
                "4). Quit%n");
    }

    public void promptForOption () {
        int choice = 0;
        while (choice!=4) {
            displayMenu();
            choice = Integer.parseInt(console.readLine("%nChoose an option: "));
            switch (choice) {
                case 1:
                    if (mPlayers.size() - mTeams.size()*11 >= 11) {
                        Team team = promptForTeam();
                        mTeams.add(team);
                        console.printf("%nNew team added to the league!%n%n");
                    } else {
                        console.printf("There are not enough players for a new team.%n" +
                                "Please choose another option.%n");
                    }
                    break;
                case 2:
                    int selection;
                    Team chosenTeam;
                    Player chosenPlayer;
                    //Display list of teams to choose from
                    int count = 1;
                    console.printf("%n");
                    for (Team team : mTeams) {
                        console.printf("%d). %s%n", count, team.mName);
                        count++;
                    }
                    //Choose existing team
                    selection = Integer.parseInt(console.readLine("%nChoose a team from the above list to add player: "));
                    List listOfTeams = new ArrayList(mTeams);
                    chosenTeam = (Team)listOfTeams.get(selection-1);
                    //Display available players
                    count = 1;
                    for (Player player : mPlayers) {
                        console.printf("%d). %s %s%n    Height: %d%n    Experienced: %b%n%n",
                                count, player.getFirstName(), player.getLastName(),
                                player.getHeightInInches(), player.isPreviousExperience());
                        count++;
                    }
                    //Choose player
                    selection = Integer.parseInt(console.readLine("%nChoose a player to add from the above list: "));
                    List listOfPlayers = new ArrayList(mPlayers);
                    chosenPlayer = (Player)listOfPlayers.get(selection-1);
                    //Add player to team
                    chosenTeam.addPlayer(chosenPlayer);
                    //Remove player from available players
                    mPlayers.remove(chosenPlayer);
                    console.printf("%s added to %s%n", chosenPlayer.getFirstName(), chosenTeam.mName);
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
