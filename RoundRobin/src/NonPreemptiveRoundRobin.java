import java.util.*;

public class NonPreemptiveRoundRobin{

    List<Triple<String, Integer, Integer>> processes;
    int timeQuantum;

    public NonPreemptiveRoundRobin(List<Triple<String, Integer, Integer>> processes, int timeQuantum){
        this.processes = processes;
        this.timeQuantum = timeQuantum;
    }

}