import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SJFPreemptive {
    List<Triple<String, Integer, Integer>> processes;
    int input;
    int[] arrTime,burstTime;

    public SJFPreemptive(List<Triple<String, Integer, Integer>> processes, int input, int[] AT, int[] BT) {
        this.processes = processes;
        this.input = input;
        this.arrTime = AT;
        this.burstTime = BT;
    }

    public void run(){
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
        for (int i = 0; i < input; i++) {
            totalTime += processes.get(i).getBurstTime();
        }

        int[] waitingTime = new int[input];
        int[] turnaroundTime = new int[input];
        boolean[] processCompleted = new boolean[input];

        PriorityQueue<Triple<String, Integer, Integer>> queue = new PriorityQueue<>(input, new Comparator<Triple<String, Integer, Integer>>() {
            public int compare(Triple<String, Integer, Integer> o1, Triple<String, Integer, Integer> o2) {
                return o1.getBurstTime().compareTo(o2.getBurstTime());
            }
        });

        int processIdx = 0;
        for (int timer = 0; timer < totalTime; timer++) {
            while (processIdx < processes.size() && processes.get(processIdx).getArrivalTime() == timer) {
                queue.offer(processes.get(processIdx));
                processIdx++;
            }

            if (!queue.isEmpty()) {
                Triple<String, Integer, Integer> currentProcess = queue.poll();
                int remainingBurstTime = currentProcess.getBurstTime() - 1;

                int processIndex = Integer.parseInt(currentProcess.getProcess().substring(1)) - 1;
                if (remainingBurstTime > 0) {
                    queue.offer(new Triple<>(currentProcess.getProcess(), currentProcess.getArrivalTime(), remainingBurstTime));
                } else if (!processCompleted[processIndex]) {
                    processCompleted[processIndex] = true;
                    turnaroundTime[processIndex] = timer + 1 - arrTime[processIndex];
                    waitingTime[processIndex] = turnaroundTime[processIndex] - burstTime[processIndex];
                }
            } else {
                for (int i = 0; i < input; i++) {
                    if (!processCompleted[i] && arrTime[i] <= timer) {
                        waitingTime[i]++;
                    }
                }
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

        System.out.println("\nProcess\tArrival Time\tBurst Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < input; i++) {
            System.out.println("P" + (i + 1) + "\t\t" + arrTime[i] + "\t\t\t\t" + burstTime[i] + "\t\t\t" + waitingTime[i] + "\t\t\t\t" + turnaroundTime[i]);
        }

        System.out.printf("\nAverage Waiting Time: %.2f", averageWaitingTime);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", averageTurnaroundTime);
    }
}
