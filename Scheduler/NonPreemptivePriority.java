import java.util.*;

public class NonPreemptivePriority {

    List<Triple<String, Integer, Integer>> processes;
    //Priority Table
            //first column : procedure number
            //second column : priority value
    int[][] priorityTable;
    
    public NonPreemptivePriority(List<Triple<String, Integer, Integer>> processes, int[][] priorityTable) {
        this.processes = processes;
        this.priorityTable = priorityTable;
    }

    public void schedulePriority() {
        // Sort priority table by 2nd Column (priority value)
        sortbyColumn(priorityTable, 1);

        //Output Table
            //first column : completion time
            //second column : waiting time
            //third column : turnaround time
        int[][] outputTable = new int[processes.size()][3];

        //compute completion time
            //iterate priorityTable
        for (int i=0, compTime=0; i<priorityTable.length;i++){
            //get procedure number (sorted by priority)
            int prNum =  priorityTable[i][0];
            //add burst time to current total completion time
            compTime += processes.get(prNum-1).getBurstTime();
            //set completion time of current procedure
            outputTable[prNum-1][0] = compTime;
        }

        //compute waiting time and turnaround time
        for (int i=0, waitTime,turnArTime; i<outputTable.length;i++){
            //turnaround time = completion time - arrival time
            //waiting time = turnaround time - burst time
            turnArTime = outputTable[i][0] - processes.get(i).getArrivalTime();
            waitTime = turnArTime - processes.get(i).getBurstTime();
            
            //put waiting time to output table
            outputTable[i][1] = waitTime;
            //put turnaround time to output table
            outputTable[i][2] = turnArTime;
        }

        //compute averages
        double aveWaitTime = 0;
        double aveTurnArTime = 0;
        for (int i=0;i<outputTable.length;i++){
            aveWaitTime += outputTable[i][1];
            aveTurnArTime += outputTable[i][2];
        }
        aveWaitTime /= outputTable.length;
        aveTurnArTime /= outputTable.length;

        // Display the output table
        System.out.println("completion time\twaiting time\tturnaround time");
        for (int i = 0; i < outputTable.length; i++) {
            System.out.print("P"+(i+1)+": " + outputTable[i][0] + "\t\tP"+(i+1)+": " + outputTable[i][1] + "\t\tP"+(i+1)+": " + outputTable[i][2]);
            System.out.println();
        }
        // Display averages
        System.out.println("Average waiting time: " + aveWaitTime);
        System.out.println("Average turnaround time: " + aveTurnArTime);
    }

    // Function to sort 2d array by column
    public static void sortbyColumn(int arr[][], int col)
    {
        // Using built-in sort function Arrays.sort
        Arrays.sort(arr, new Comparator<int[]>() {
            
        @Override              
        // Compare values according to columns
        public int compare(final int[] entry1, 
                            final int[] entry2) {

            // To sort in descending order revert 
            // the '>' Operator
            if (entry1[col] > entry2[col])
                return 1;
            else
                return -1;
        }
        });
    }

}
