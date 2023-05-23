import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class PriorityPreemptive {
    private Collection<PriorityProcess> list;

    public PriorityPreemptive(List<Triple<String, Integer, Integer>> list) {
        List<PriorityProcess> processList = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        System.out.println("Please Input Corresponding Process Priority (Lower Value = Higher Priority)");
        for (Triple<String,Integer,Integer> t : list) {
            System.out.print("   Process " + t.getProcess() + ": ");
            processList.add(new PriorityProcess(t.getProcess(), t.getArrivalTime(), t.getBurstTime(), s.nextInt()));
        }
        Collections.sort(processList, (PriorityProcess p1, PriorityProcess p2) -> p1.arrival - p2.arrival);
        this.list = processList;
    }

    class PriorityProcess implements Comparable<PriorityProcess>{
        final String name;
        final int arrival;
        final int burst;
        final int priority;
        int remaining;
        int waitingTime = -1;
        int turnaroundTime = -1;
    
        public PriorityProcess(String name, int arrival, int burst, int priority) {
            this.name = name;
            this.arrival = arrival;
            this.burst = burst;
            this.remaining = burst;
            this.priority = priority;
        }

        public boolean process() { // returns true if process is finished
            this.remaining--;
            return this.remaining <= 0;
        }

        public void finish(int time) {
            this.turnaroundTime = time - this.arrival;
            this.waitingTime = this.turnaroundTime - this.burst;
        }

        @Override
        public int compareTo(PriorityProcess other) {
            return Integer.compare(this.priority, other.priority);
        }
        
        @Override
        public String toString() {
            return String.format("Name: %s\nArrival Time: %d\nBurst Time: %d\nPriority: %d\n", name, arrival, burst, priority);
        }
    }
    
    public void run() {
        List<PriorityProcess> processList = new ArrayList<>(this.list); // copy list
        PriorityQueue<PriorityProcess> queue = new PriorityQueue<>();
        int t = 0;
        do { // loop until finish
            for (int i = 0; i < processList.size(); i++) {
                PriorityProcess p = processList.get(i);
                if (p.arrival != t) break;
                queue.add(p);
                processList.remove(p);
                i--;
            }
            // process the process
            t++;
            PriorityProcess current = queue.peek();
            if (current != null && current.process()) // if done
                queue.poll().finish(t);
        } while (!queue.isEmpty() || !processList.isEmpty());
        
        processList = new ArrayList<>(this.list); // copy list again
        double totalWait = 0, totalTurn = 0, n = processList.size();
        System.out.println("Priority (Preemptive):\nName:\tWaiting Time:\tTurnaround Time:");
        for (PriorityProcess p : processList) {
            totalWait += p.waitingTime;
            totalTurn += p.turnaroundTime;
            System.out.println(p.name + "\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
        }
        System.out.println("Average Waiting Time: " + totalWait/n);
        System.out.println("Average Turnaround Time: " + totalTurn/n);
    }
}
