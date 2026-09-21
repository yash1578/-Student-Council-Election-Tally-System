import java.util.Scanner;

class Candidate {
    String name;
    int votes;

    Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    void addVote() {
        votes++;
    }
}

public class StudentCouncilElection {
    static Scanner sc = new Scanner(System.in);

    // Display candidates
    static void displayCandidates(Candidate[] candidates) {
        for (int i = 0; i < candidates.length; i++) {
            System.out.println((i + 1) + ". " + candidates[i].name);
        }
    }

    // Voting method
    static void conductVoting(String post, Candidate[] candidates) {
        System.out.println("\n======================================");
        System.out.println("Voting for: " + post);
        System.out.println("======================================");
        displayCandidates(candidates);
        System.out.print("\nEnter number of voters: ");
        int voters = sc.nextInt();
        for (int i = 1; i <= voters; i++) {
            System.out.print("Voter " + i + ", enter candidate number: ");
            int choice = sc.nextInt();
            if (choice >= 1 && choice <= candidates.length) {
                candidates[choice - 1].addVote();
                System.out.println("Vote recorded successfully.");
            } else {
                System.out.println("Invalid choice! Vote not counted.");
            }
        }
    }

    // Display results
    static void displayResults(String post, Candidate[] candidates) {
        System.out.println("\n======================================");
        System.out.println("RESULT - " + post);
        System.out.println("======================================");
        int highestVotes = -1;
        for (Candidate candidate : candidates) {
            System.out.println(
                    candidate.name + " : " + candidate.votes + " votes");
            if (candidate.votes > highestVotes) {
                highestVotes = candidate.votes;
            }
        }
        // Find winner
        Candidate winner = null;
        int winnerCount = 0;
        for (Candidate candidate : candidates) {
            if (candidate.votes == highestVotes) {
                winner = candidate;
                winnerCount++;
            }
        }
        if (winnerCount > 1) {
            System.out.println("\nResult: TIE");
            System.out.println("There is a tie between:");
            for (Candidate candidate : candidates) {
                if (candidate.votes == highestVotes) {
                    System.out.println("- " + candidate.name);
                }
            }
        } else {
            System.out.println("\nWinner: " + winner.name);
            System.out.println("Votes: " + winner.votes);
        }
    }

    // Main method
    public static void main(String[] args) {
        // President candidates
        Candidate[] president = {
                new Candidate("Rahul"),
                new Candidate("Priya"),
                new Candidate("Arjun")
        };
        // Vice President candidates
        Candidate[] vicePresident = {
                new Candidate("Sneha"),
                new Candidate("Kiran"),
                new Candidate("Ananya")
        };
        // Secretary candidates
        Candidate[] secretary = {
                new Candidate("Rohit"),
                new Candidate("Meena"),
                new Candidate("Vikram")
        };
        // Treasurer candidates
        Candidate[] treasurer = {
                new Candidate("Amit"),
                new Candidate("Pooja"),
                new Candidate("Varun")
        };
        int choice;
        do {
            System.out.println("\n");
            System.out.println("==============================================");
            System.out.println("       STUDENT COUNCIL ELECTION SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. Vote for President");
            System.out.println("2. Vote for Vice President");
            System.out.println("3. Vote for Secretary");
            System.out.println("4. Vote for Treasurer");
            System.out.println("5. Display Election Results");
            System.out.println("6. Exit");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    conductVoting(
                            "PRESIDENT",
                            president);
                    break;
                case 2:
                    conductVoting(
                            "VICE PRESIDENT",
                            vicePresident);
                    break;

                case 3:
                    conductVoting(
                            "SECRETARY",
                            secretary);
                    break;
                case 4:
                    conductVoting(
                            "TREASURER",
                            treasurer);
                    break;
                case 5:
                    System.out.println("\n");
                    System.out.println("==============================================");
                    System.out.println("             ELECTION RESULTS");
                    System.out.println("==============================================");
                    displayResults(
                            "PRESIDENT",
                            president);
                    displayResults(
                            "VICE PRESIDENT",
                            vicePresident);
                    displayResults(
                            "SECRETARY",
                            secretary);
                    displayResults(
                            "TREASURER",
                            treasurer);
                    break;
                case 6:
                    System.out.println("\n==============================================");
                    System.out.println("Thank you for using the Election System!");
                    System.out.println("==============================================");
                    break;
                default:
                    System.out.println(
                            "Invalid choice! Please try again.");
            }
        } while (choice != 6);
        sc.close();
    }
}
