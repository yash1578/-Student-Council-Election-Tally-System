
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Candidate {
    // Module 4: Encapsulation using private fields
    private String name;
    private int votes;

    // Module 4: Parameterized constructor
    public Candidate(String name) {
        this.name = name;
        this.votes = 0;
    }

    // Module 4: Accessor
    public String getName() {
        return name;
    }

    public int getVotes() {
        return votes;
    }

    // Module 4: Instance method
    public void addVote() {
        votes++;
    }

    // Module 4: Overriding toString()
    @Override
    public String toString() {
        return String.format("%-20s %5d", name, votes);
    }
}

public class StudentElectionCouncil {

    // Module 1: Constant
    static final int MAX_CANDIDATES = 10;

    // Module 3: Method with an ArrayList parameter
    static void displayCandidates(ArrayList<Candidate> candidates) {
        System.out.println("\n--- Candidate List ---");
        for (int i = 0; i < candidates.size(); i++) {
            System.out.println((i + 1) + ". " +
                               candidates.get(i).getName());
        }
    }

    // Module 3: Summation method
    static int calculateTotalVotes(ArrayList<Candidate> candidates) {
        int total = 0;
        for (Candidate candidate : candidates) {
            total += candidate.getVotes();
        }
        return total;
    }

    // Module 3: Searching using a method
    static Candidate findCandidate(ArrayList<Candidate> candidates,
                                   String name) {
        for (Candidate candidate : candidates) {
            if (candidate.getName().equalsIgnoreCase(name)) {
                return candidate;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // Module 1: Console input with Scanner
        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("    STUDENT ELECTION COUNCIL SYSTEM");
        System.out.println("======================================");

        int numberOfCandidates;

        // Module 2: do-while input validation
        do {
            System.out.print("Enter number of candidates (2-10): ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a whole number: ");
                sc.next();
            }
            numberOfCandidates = sc.nextInt();
            sc.nextLine();

            if (numberOfCandidates < 2 ||
                numberOfCandidates > MAX_CANDIDATES) {
                System.out.println("Please enter a number from 2 to 10.");
            }
        } while (numberOfCandidates < 2 ||
                 numberOfCandidates > MAX_CANDIDATES);

        // Module 6: Generic ArrayList stores Candidate objects
        ArrayList<Candidate> candidates = new ArrayList<>();

        // Module 6: HashSet tracks voter IDs to prevent repeat voting
        HashSet<String> votedVoterIds = new HashSet<>();

        // Module 6: HashMap stores vote counts by candidate name
        HashMap<String, Integer> voteSummary = new HashMap<>();

        // Module 2: for loop
        for (int i = 0; i < numberOfCandidates; i++) {
            System.out.print("Enter candidate " + (i + 1) + " name: ");
            String name = sc.nextLine().trim();

            // Module 5: String validation and comparison
            while (name.isEmpty() || findCandidate(candidates, name) != null) {
                if (name.isEmpty()) {
                    System.out.print("Name cannot be empty. Enter again: ");
                } else {
                    System.out.print("Name already exists. Enter another name: ");
                }
                name = sc.nextLine().trim();
            }

            Candidate candidate = new Candidate(name);
            candidates.add(candidate);
            voteSummary.put(name, 0);
        }

        int numberOfVoters;
        System.out.print("\nEnter number of voters: ");
        while (!sc.hasNextInt()) {
            System.out.print("Enter a whole number: ");
            sc.next();
        }
        numberOfVoters = sc.nextInt();
        sc.nextLine();

        // Module 2: if statement
        if (numberOfVoters < 0) {
            System.out.println("Number of voters cannot be negative.");
            sc.close();
            return;
        }

        int validVotes = 0;
        int invalidVotes = 0;
        int voter = 1;

        // Module 2: while loop
        while (voter <= numberOfVoters) {
            System.out.println("\nVoter " + voter);
            System.out.print("Enter voter ID: ");
            String voterId = sc.nextLine().trim();

            // Module 5: String methods
            if (voterId.isEmpty()) {
                System.out.println("Empty voter ID. Vote rejected.");
                invalidVotes++;
                voter++;
                continue; // Module 2: continue
            }

            // Module 6: HashSet lookup and add
            if (votedVoterIds.contains(voterId)) {
                System.out.println("This voter ID has already voted.");
                invalidVotes++;
                voter++;
                continue;
            }

            displayCandidates(candidates);
            System.out.print("Enter candidate number to vote: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a candidate number: ");
                sc.next();
            }
            int choice = sc.nextInt();
            sc.nextLine();

            // Module 2: if-else and logical operators
            if (choice >= 1 && choice <= candidates.size()) {
                Candidate selected = candidates.get(choice - 1);
                selected.addVote();
                votedVoterIds.add(voterId);
                validVotes++;

                // Module 6: Update vote summary in HashMap
                voteSummary.put(selected.getName(),
                                voteSummary.get(selected.getName()) + 1);

                System.out.println("Vote recorded successfully.");
            } else {
                invalidVotes++;
                System.out.println("Invalid candidate number. Vote not counted.");
            }

            voter++;
        }

        // Module 3: Method call and summation
        int totalVotes = calculateTotalVotes(candidates);

        // Module 6: Lambda expression with Comparator for descending vote order
        candidates.sort((first, second) ->
            Integer.compare(second.getVotes(), first.getVotes()));

        System.out.println("\n======================================");
        System.out.println("             ELECTION RESULTS");
        System.out.println("======================================");
        System.out.printf("%-20s %5s%n", "Candidate", "Votes");
        System.out.println("--------------------------------------");

        // Module 6: Stream, filter, collect, and Collectors.toList()
        List<Candidate> candidatesWithVotes = candidates.stream()
            .filter(candidate -> candidate.getVotes() >= 0)
            .collect(Collectors.toList());

        // Module 6: forEach terminal operation with lambda
        candidatesWithVotes.forEach(candidate ->
            System.out.println(candidate));

        System.out.println("--------------------------------------");
        System.out.println("Valid votes   : " + validVotes);
        System.out.println("Invalid votes : " + invalidVotes);
        System.out.println("Total votes   : " + totalVotes);
        System.out.println("Unique voter IDs recorded: " +
                           votedVoterIds.size());

        // Module 6: HashMap iteration using forEach and lambda
        System.out.println("\n--- Vote Summary ---");
        voteSummary.forEach((name, count) ->
            System.out.println(name + ": " + count + " votes"));

        // Module 3: Average and Module 1: typecasting
        if (!candidates.isEmpty()) {
            double average = (double) totalVotes / candidates.size();
            System.out.printf("%nAverage votes per candidate: %.2f%n", average);
        }

        System.out.println("\n--- Vote Percentages ---");
        for (Candidate candidate : candidates) {
            double percentage = 0.0;

            if (totalVotes > 0) {
                percentage = (double) candidate.getVotes()
                              / totalVotes * 100;
            }

            System.out.printf("%s: %.2f%%%n",
                              candidate.getName(), percentage);
        }

        // Module 2: Selection logic for winner and ties
        if (totalVotes == 0) {
            System.out.println("\nNo valid votes. No winner declared.");
        } else if (candidates.get(0).getVotes() ==
                   candidates.get(candidates.size() - 1).getVotes()) {
            System.out.println("\nElection result: Complete tie.");
        } else if (candidates.size() > 1 &&
                   candidates.get(0).getVotes() ==
                   candidates.get(1).getVotes()) {
            System.out.println("\nElection result: Tie between top candidates.");

            int highestVotes = candidates.get(0).getVotes();
            for (Candidate candidate : candidates) {
                if (candidate.getVotes() == highestVotes) {
                    System.out.println("Candidate: " + candidate.getName());
                }
            }
        } else {
            System.out.println("\nWinner: " + candidates.get(0).getName());
            System.out.println("Winning votes: " +
                               candidates.get(0).getVotes());
        }

        System.out.println("\nElection tally completed.");
        sc.close();
    }
}
