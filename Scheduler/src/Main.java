import java.util.*;
import java.util.Scanner;

public class Main {
    static boolean preempt = false;

    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);
        String scheduler = "";
        String choice;
        boolean again = true;

        while (again == true) {

            System.out.println("=========== OS CPU Scheduler Project ===========\n");

            System.out.print("Enter no. of processes: ");
            int input = scan.nextInt();

            int[] arrTime = new int[input];
            System.out.println("\nEnter individual arrival time: ");
            for (int i = 0; i < input; i++) {
                System.out.println("AT" + (i + 1) + ": ");
                arrTime[i] = scan.nextInt();
            }

            int[] burstTime = new int[input];
            System.out.println("\nEnter individual burst time: ");
            for (int i = 0; i < input; i++) {
                System.out.println("BT" + (i + 1) + ": ");
                burstTime[i] = scan.nextInt();
            }

            List<Triple<String, Integer, Integer>> processes = new ArrayList<>();
            for (int i = 0; i < input; i++) {
                processes.add(new Triple<>("P" + (i + 1), arrTime[i], burstTime[i]));
            }

            System.out.println("\n List of processes:");
            System.out.println(processes);

            System.out.println("\nEnter the letter of CPU Scheduler you want\n");
            System.out.println("A: First-Come-First-Serve \n");
            System.out.println("B: Shortest-Job-First \n");
            System.out.println("C: Priority Scheduling \n");
            System.out.println("D: Round Robin \n");

            scheduler = scan.next();

            switch (scheduler.toUpperCase()) {
                case "A":
                    
                    break;
                case "B":
                    
                    break;
                case "C":
                    
                    break;
                case "D":
                    preempt = checkPreempt();
                    if(preempt){
                        //With preemption
                        System.out.println("Enter the Scheduler's Time Quantum");
                        int timeQuantum = scan.nextInt();
                        PreemptiveRoundRobin prr = new PreemptiveRoundRobin(processes, timeQuantum);
                        prr.scheduleRoundRobin();
                    } else {
                        //Without preemption

                    }
                    break;
                default:
                    
            }
            System.out.println("Do you want to continue (Y/N)?");
            choice = scan.next();
            if (choice.toUpperCase().equals("N")) {
                again = false;
                System.out.println("thank you");
            }
        }

    }

    private static boolean checkPreempt() { // T = P and F = NP
        Scanner scan = new Scanner(System.in);
        System.out.println("Without Preemption or With Preemption?");
        System.out.println("A: Without Preemption \n");
        System.out.println("B: With Preemption \n");

        String choice = scan.next();
        if (choice.toUpperCase().equals("A")) {
            preempt = false;
        } else if (choice.toUpperCase().equals("B")) {
            preempt = true;
        } else {
            System.err.println("Incorrect Choice");
            System.out.println(choice);
        }
        System.out.println("Preempt: " + preempt);
        return preempt;
    }

}
