import java.util.*;

public class FirstComeFirstServe {
    List<Triple<String, Integer, Integer>> processes;
    int[] arrTime, burstTime;

    public FirstComeFirstServe(List<Triple<String, Integer, Integer>> processes, int[] AT, int[] BT) {
        this.processes = processes;
        this.arrTime = AT;
        this.burstTime = BT;
    }

    public void scheduleFCFS() {
        Collections.sort(processes, Comparator.comparingInt(Triple::getArrivalTime));

        int[] completionTime = new int[processes.size()];
        int[] waitingTime = new int[processes.size()];
        int[] turnaroundTime = new int[processes.size()];

        for (int i = 0; i < processes.size(); i++) {
            Triple<String, Integer, Integer> process = processes.get(i);
            if (i == 0 || process.getArrivalTime() > completionTime[i - 1]) {
                completionTime[i] = process.getArrivalTime() + process.getBurstTime();
            } else {
                completionTime[i] = completionTime[i - 1] + process.getBurstTime();
            }
            turnaroundTime[i] = completionTime[i] - process.getArrivalTime();
            waitingTime[i] = turnaroundTime[i] - process.getBurstTime();
        }

        double totalWaitingTime = Arrays.stream(waitingTime).average().orElse(0.0);
        double totalTurnaroundTime = Arrays.stream(turnaroundTime).average().orElse(0.0);

        System.out.println("\nProcess\tArrival Time\tBurst Time\tCompletion Time\tWaiting Time\tTurnaround Time");
        for (int i = 0; i < processes.size(); i++) {
            Triple<String, Integer, Integer> process = processes.get(i);
            System.out.println(process.getProcess() + "\t\t" + process.getArrivalTime() + "\t\t\t\t" + process.getBurstTime() + "\t\t\t" + completionTime[i] + "\t\t\t\t" + waitingTime[i] + "\t\t\t\t" + turnaroundTime[i]);
        }


        System.out.printf("\nAverage Waiting Time: %.2f", totalWaitingTime);
        System.out.printf("\nAverage Turnaround Time: %.2f\n", totalTurnaroundTime);
    }
}
