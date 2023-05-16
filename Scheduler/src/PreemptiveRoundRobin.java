import java.util.*;

import javax.sound.sampled.SourceDataLine;

public class PreemptiveRoundRobin{

    List<Triple<String, Integer, Integer>> processes;
    int timeQuantum;

    public PreemptiveRoundRobin(List<Triple<String, Integer, Integer>> processes, int timeQuantum){
        this.processes = processes;
        this.timeQuantum = timeQuantum;
    }

    public void scheduleRoundRobin(){
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

        int totalTime = 0;
        for (int i = 0; i < processes.size(); i++) {
            totalTime += processes.get(i).getBurstTime();
        }
        System.out.println(totalTime);

        int[] waitingTime = new int[processes.size()];
        int[] turnaroundTime = new int[processes.size()];
        boolean[] processCompleted = new boolean[processes.size()];

        

    }

    

}