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
                System.out.print("AT" + (i + 1) + ": ");
                arrTime[i] = scan.nextInt();
            }

            int[] burstTime = new int[input];
            System.out.println("\nEnter individual burst time: ");
            for (int i = 0; i < input; i++) {
                System.out.print("BT" + (i + 1) + ": ");
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
            System.out.println("Input(A, B, C, D): ");
            scheduler = scan.next();

            switch (scheduler.toUpperCase()) {
                case "A":
                    preempt = checkPreempt();
                    if(preempt){
                        //With preemption

                    } else {
                        //Without preemption

                    }
                    break;
                case "B":
                    preempt = checkPreempt();
                    if(preempt){
                        //With preemption

                    } else {
                        //Without preemption

                    }
                    break;
                case "C":
                    preempt = checkPreempt();
                    if(preempt){
                        //With preemption

                    } else {
                        //Without preemption
                        int[][] priorityTable = new int[input][2];
                        //Priority Table
                            //first column : procedure number
                            //second column : priority value
                        
                        System.out.println("Enter individual priority number:"); 
                        for(int i = 0; i<priorityTable.length; i++){
                            System.out.print("Prio" + (i+1) +": ");  
                            priorityTable[i][0] = i+1;
                            priorityTable[i][1] = scan.nextInt(); 
                        }
                        NonPreemptivePriority npp = new NonPreemptivePriority(processes, priorityTable);
                        npp.schedulePriority();
                    }
                    break;
                case "D": //Round Robin
                        System.out.println("Enter the Scheduler's Time Quantum");
                        int timeQuantum = scan.nextInt();
                        PreemptiveRoundRobin prr = new PreemptiveRoundRobin(processes, timeQuantum);
                        prr.scheduleRoundRobin();

                    break;
                default: System.out.println("Incorrect Input, try again!");
                    
            }
            System.out.println("Do you want to continue (Y/N)?");
            choice = scan.next();
            if (choice.toUpperCase().equals("N")) {
                again = false;
                System.out.println("Thank You for Using CPU Scheduler");
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
