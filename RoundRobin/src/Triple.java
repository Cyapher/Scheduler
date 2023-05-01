public class Triple<T, U, V> {

    private final T first;
    private final U second;
    private final V third;

    public Triple(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public T getProcess() {
        return first;
    }

    public U getArrivalTime() {
        return second;
    }

    public V getBurstTime() {
        return third;
    }

    // Remove this method if you don't need to modify the burst time after the Triple object is created.
    // If you want to keep it, change the method to return the updated value:
    // public V setBurstTime(V num) {
    //     third = num;
    //     return third;
    // }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }
}
