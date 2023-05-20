import java.util.*;

import javax.sound.sampled.SourceDataLine;

public class PreemptiveRoundRobin {

    List<Triple<String, Integer, Integer>> processes;
    int timeQuantum;

    public PreemptiveRoundRobin(List<Triple<String, Integer, Integer>> processes, int timeQuantum) {
        this.processes = processes;
        this.timeQuantum = timeQuantum;
    }

    public void scheduleRoundRobin() {
        System.out.println("\n(In Round Robin) Process List" + processes);
        System.out.println("Time Quantum: " + timeQuantum);

        Collections.sort(processes, new Comparator<Triple<String, Integer, Integer>>() {
            public int compare(Triple<String, Integer, Integer> o1, Triple<String, Integer, Integer> o2) {
                int cmp = o1.getArrivalTime().compareTo(o2.getArrivalTime());
                if (cmp == 0) {
                    cmp = o1.getBurstTime().compareTo(o2.getBurstTime());
                }
                return cmp;
            }
        });

        //Print Process Specs
        System.out.println("Sorted Processes");
        System.out.println("\nProcess\tArrival Time\tBurst Time");
        for(int i = 0; i < processes.size(); i++){
            System.out.println(processes.get(i).getProcess() + "\t" + processes.get(i).getArrivalTime() + "\t\t" + processes.get(i).getBurstTime());
        }

        // Total Time
        int totalTime = 0;
        for (int i = 0; i < processes.size(); i++) {
            totalTime += processes.get(i).getBurstTime();
        }

        System.out.println("\ntotal time: " + totalTime);

        // Remaining Processes
        int remainingProcesses = processes.size();
        

        int[] waitingTime = new int[processes.size()];
        int[] turnaroundTime = new int[processes.size()];
        int[] remainingTime = new int[processes.size()]; //remaining time per process
        boolean[] processCompleted = new boolean[processes.size()];

        for (int i = 0; i < processes.size(); i++) {
            remainingTime[i] = processes.get(i).getBurstTime();
            waitingTime[i] = 0;

            System.out.println("Remaining Time[" + processes.get(i).getProcess() + "]: " + remainingTime[i]);
        }

        // Queue<Triple<String, Integer, Integer>> queue = processes;
        // int processIdx = 0;
        System.out.println("\nProcesses Order: \nT0");

        int processNum = 0;
        int time = 0;

        while (remainingProcesses > 0) { //while there is still processes available

            if (remainingTime[processNum] > timeQuantum) {
                remainingTime[processNum] = remainingTime[processNum] - timeQuantum;
                System.out.println(" | P[" + (processes.get(processNum).getProcess()) + "] | ");
                time += timeQuantum;
                System.out.println("Time: "+time);

            } else if (remainingTime[processNum] <= timeQuantum && remainingTime[processNum] > 0) {
                time += remainingTime[processNum];
                remainingTime[processNum] = remainingTime[processNum] - remainingTime[processNum];
                System.out.println(" | P[" + (processes.get(processNum).getProcess()) + "] | ");
                remainingProcesses--;
                
                //Computation of TAT
                //Turn-Around-Time = Completion time - Arrival Time
                turnaroundTime[processNum] = (time + 1) - processes.get(processNum).getArrivalTime();

                //Computation of WT
                waitingTime[processNum] = turnaroundTime[processNum] - processes.get(processNum).getBurstTime();
                System.out.println("T"+time);
            }

            System.out.println("process num: " + (processNum + 1));
            processNum++;
            
            if(processNum == processes.size()){
                processNum = 0;
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (int i = 0; i < processes.size(); i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double averageWaitingTime = totalWaitingTime / processes.size();
        double averageTurnaroundTime = totalTurnaroundTime / processes.size();

         //Print Process Computations
         System.out.println("Processes Computations");
         System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurn-Around Time");
         for(int i = 0; i < processes.size(); i++){
             System.out.println(processes.get(i).getProcess() + "\t" + processes.get(i).getArrivalTime() + "\t\t" + processes.get(i).getBurstTime() + "\t\t" + waitingTime[i] + "\t\t" + turnaroundTime[i]);
         }

        System.out.printf("\nAverage Waiting Time: %.2f", averageWaitingTime);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", averageTurnaroundTime);

    }

}
