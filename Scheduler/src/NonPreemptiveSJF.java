import java.util.*;

public class NonPreemptiveSJF {
    private List<Triple<String, Integer, Integer>> processes;
    private int input;
    private List<Integer> arrTime, burstTime;

    public NonPreemptiveSJF(List<Triple<String, Integer, Integer>> processes, int input, int[] AT, int[] BT) {
        this.processes = processes;
        this.input = input;
        this.arrTime = new ArrayList<>();
        this.burstTime = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            this.arrTime.add(AT[i]);
            this.burstTime.add(BT[i]);
        }
    }

    public void run() {
        int[] completionTime = new int[input];
        int[] waitingTime = new int[input];
        int[] turnaroundTime = new int[input];

        for (int i = 0; i < input; i++) {
            completionTime[i] = -1;
            waitingTime[i] = -1;
            turnaroundTime[i] = -1;
        }

        int timer = 0;
        int completed = 0;

        while (completed != input) {
            int minIndex = -1;
            int minBurstTime = Integer.MAX_VALUE;

            for (int i = 0; i < input; i++) {
                if (arrTime.get(i) <= timer && burstTime.get(i) < minBurstTime && completionTime[i] == -1) {
                    minIndex = i;
                    minBurstTime = burstTime.get(i);
                }
            }

            if (minIndex != -1) {
                completionTime[minIndex] = timer + burstTime.get(minIndex);
                waitingTime[minIndex] = timer - arrTime.get(minIndex);
                turnaroundTime[minIndex] = completionTime[minIndex] - arrTime.get(minIndex);
                timer = completionTime[minIndex];
                completed++;
            } else {
                timer++;
            }
        }

        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;
        for (int i = 0; i < input; i++) {
            totalWaitingTime += waitingTime[i];
            totalTurnaroundTime += turnaroundTime[i];
        }

        double averageWaitingTime = totalWaitingTime / input;
        double averageTurnaroundTime = totalTurnaroundTime / input;

        System.out.println("Process\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < input; i++) {
            Triple<String, Integer, Integer> process = processes.get(i);
            System.out.println(process.getProcess() + "\t\t" + arrTime.get(i) + "\t\t\t" + burstTime.get(i) + "\t\t\t" +
                               completionTime[i] + "\t\t\t\t" + waitingTime[i] + "\t\t\t\t" + turnaroundTime[i]);
        }

        System.out.printf("\nAverage Waiting Time: %.2f\n", averageWaitingTime);
        System.out.printf("Average Turnaround Time: %.2f\n", averageTurnaroundTime);
    }
}
