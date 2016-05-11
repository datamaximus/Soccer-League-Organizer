import java.io.Console;

import com.teamtreehouse.model.Player;
import com.teamtreehouse.model.Players;
import com.teamtreehouse.model.Team;

public class Prompter {
    Console console = System.console();

    public Prompter() {
    }

    public void promptLeague() {
        console.printf("%nSOCCER LEAGUE ORGANIZER%n");
        console.printf("-----------------------%n");
        promptForOption();
    }

    private void displayMenu() {
        console.printf("1). Add A Team%n" +
                "2). Option 2%n" +
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
                    Team team = promptForTeam();
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
