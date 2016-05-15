import java.io.Console;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class League {
    Team chosenTeam;
    Player chosenPlayer;
    Set<Player> mPlayers;
    Set<Team> mTeams = new TreeSet<Team>();
    int mMaxNewTeams;
    Console console = System.console();

    public League(Player[] players) {
        mPlayers = new TreeSet(Arrays.asList(players));
        mMaxNewTeams = mPlayers.size() / 11;
    }

    public void organizeLeague() {
        console.printf("%nSOCCER LEAGUE ORGANIZER%n");
        console.printf("-----------------------%n");
        try {
            promptForOption();
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("That was not a valid entry");
            ioobe.printStackTrace();
        } catch (NumberFormatException nfe) {
            System.out.println("That was not a valid entry");
            nfe.printStackTrace();
        }
    }

    private void displayMenu() {
        console.printf("%n1). Add team to the league%n" +
                "2). Add player to team%n" +
                "3). Remove player from team%n" +
                "4). Height report%n" +
                "5). Experience report%n" +
                "6). Print team roster%n" +
                "7). Quit%n");
    }

    public void promptForOption() {
        String choice = "";
        while (!choice.equals("7")) {
            displayMenu();
            choice = console.readLine("%nChoose an option: ");
            switch (choice) {
                case "1":
                    if (mMaxNewTeams > 0) {
                        Team team = promptForTeam();
                        if (!mTeams.contains(team)) {
                            mTeams.add(team);
                            mMaxNewTeams--;
                            console.printf("%nNew team added to the league!%n");
                        } else {
                            console.printf("%n%s has already been added to the league%n", team.mName);
                        }
                    } else {
                        console.printf("There are not enough players for a new team.%n" +
                                "Please choose another option.%n");
                    }
                    break;
                case "2":
                    if (noTeams()) {
                        console.printf("%nCannot add player. Please add a team first.%n");
                    } else if (mPlayers.isEmpty()) {
                        console.printf("%nCannot add player. No more available players.%n");
                    } else {
                        chosenTeam = chooseTeam();
                        chosenPlayer = choosePlayer(true);
                        //Add player to team
                        chosenTeam.addPlayer(chosenPlayer);
                        //Remove player from available players
                        mPlayers.remove(chosenPlayer);
                        console.printf("%n%s added to %s%n", chosenPlayer.getFirstName(), chosenTeam.mName);
                    }
                    break;
                case "3":
                    if (noTeams()) {
                        console.printf("%nCannot remove player. All rosters are empty.%n");
                    } else {
                        chosenTeam = chooseTeam();
                        if (chosenTeam.mPlayers.isEmpty()) {
                            console.printf("%nCannot remove player. Team roster is empty.%n");
                        } else {
                            chosenPlayer = choosePlayer(false);
                            mPlayers.add(chosenPlayer);
                            chosenTeam.removePlayer(chosenPlayer);
                            console.printf("%n%s removed to %s%n", chosenPlayer.getFirstName(), chosenTeam.mName);
                        }
                    }
                    break;
                case "4":
                    if (noTeams()) {
                        console.printf("%nCannot print report. League has no teams.%n");
                    } else {
                        chosenTeam = chooseTeam();
                        displayHeightReport(chosenTeam);
                    }
                    break;
                case "5":
                    if (noTeams()) {
                        console.printf("%nCannot print report. League has no teams.%n");
                    } else {
                        displayExperienceReport();
                    }
                    break;
                case "6":
                    if (noTeams()) {
                        console.printf("%nCannot print report. League has no teams.%n");
                    } else {
                        chosenTeam = chooseTeam();
                        chosenTeam.displayRoster();
                    }
                    break;
                case "7":
                    choice = "7";
                    break;
                default:
                    console.printf("Unknown choice:  '%s'. Try again.  %n", choice);
            }
        }
    }

    public Team promptForTeam() {
        String teamName = console.readLine("Enter team name: ");
        String coachName = console.readLine("Enter coach name: ");
        return new Team(teamName, coachName);
    }

    public Team chooseTeam() throws IndexOutOfBoundsException, NumberFormatException {
        //Display list of teams to choose from
        int selection;
        int count = 1;
        console.printf("%n");
        for (Team team : mTeams) {
            console.printf("%d). %s%n", count, team.mName);
            count++;
        }
        //Choose existing team
        selection = Integer.parseInt(console.readLine("%nChoose a team (number) from the above list: "));
        List<Team> listOfTeams = new ArrayList<Team>(mTeams);
        return (Team) listOfTeams.get(selection - 1);
    }

    public Player choosePlayer(boolean toAdd) throws IndexOutOfBoundsException, NumberFormatException {
        //Display available players
        Set<Player> playerSet = new TreeSet<>();
        int selection;
        int count = 1;
        if (toAdd) {
            playerSet = mPlayers;
        } else {
            playerSet = chosenTeam.mPlayers;
        }
        for (Player player : playerSet) {
            console.printf("%d). %s %s%n    Height: %d%n    Experienced: %b%n%n",
                    count, player.getFirstName(), player.getLastName(),
                    player.getHeightInInches(), player.isPreviousExperience());
            count++;
        }
        //Choose player
        selection = Integer.parseInt(console.readLine("%nChoose a player (number) from the above list: "));
        List<Player> listOfPlayers = new ArrayList<Player>(playerSet);
        return (Player) listOfPlayers.get(selection - 1);
    }

    public void displayHeightReport(Team team) {
        int count = 0;
        Map<Integer, List<Player>> heightReport = team.byHeight();
        for (Map.Entry<Integer, List<Player>> entry : heightReport.entrySet()) {
            console.printf("%nHeight: %d%n" +
                    "----------%n", entry.getKey());
            for (Player player : entry.getValue()) {
                console.printf("%s %s%n", player.getFirstName(), player.getLastName());
                count++;
            }
            console.printf("Total of %d players at height: %d%n", count, entry.getKey());
            count = 0;
        }
    }

    public void displayExperienceReport() {
        for (Team team : mTeams) {
            Map<String, Integer> experiencedPlayers = team.teamExperience();
            console.printf("%n%s's player experience breakdown: %n" +
                    "-----------------------------------------%n", team.mName);
            for (Map.Entry<String, Integer> entry : experiencedPlayers.entrySet()) {
                console.printf("%s: %d%n", entry.getKey(), entry.getValue());
            }
            float experiencedPercentage = 100 * (experiencedPlayers.get("Experienced") / (float) team.mPlayers.size());
            console.printf("Percentage of players with experience: %.1f%n", experiencedPercentage);
        }
    }

    public boolean noTeams() {
        if (mTeams.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
