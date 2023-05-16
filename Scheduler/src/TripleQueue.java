import java.util.Iterator;
import java.util.NoSuchElementException;

public class TripleQueue implements Iterable<Triple<String, Integer, Integer>> {
    private int maxQsize;
    private int front;
    private int rear;
    private Object[] que;

    public TripleQueue(int n) {
        maxQsize = n;
        que = new Object[maxQsize];
        front = rear = -1;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return ((rear + 1) % maxQsize == front);
    }

    public int enqueue(Triple<String, Integer, Integer> triple) {
        if (isFull())
            return -999;
        if (isEmpty())
            front = 0;
        rear = (rear + 1) % maxQsize;
        que[rear] = triple;
        return 1;
    }

    public Triple<String, Integer, Integer> dequeue() {
        if (isEmpty())
            return null;
        Triple<String, Integer, Integer> el = (Triple<String, Integer, Integer>) que[front];
        if (front == rear)
            clear();
        else
            front = (front + 1) % maxQsize;
        return el;
    }

    public Triple<String, Integer, Integer> peek() {
        if (isEmpty())
            return null;
        else
            return (Triple<String, Integer, Integer>) que[front];
    }

    public void clear() {
        front = rear = -1;
    }

    @Override
    public Iterator<Triple<String, Integer, Integer>> iterator() {
        return new Iterator<Triple<String, Integer, Integer>>() {
            private int currentIndex = front;

            @Override
            public boolean hasNext() {
                return !isEmpty() && currentIndex != (rear + 1) % maxQsize;
            }

            @Override
            public Triple<String, Integer, Integer> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Triple<String, Integer, Integer> triple = (Triple<String, Integer, Integer>) que[currentIndex];
                currentIndex = (currentIndex + 1) % maxQsize;
                return triple;
            }
        };
    }
}
