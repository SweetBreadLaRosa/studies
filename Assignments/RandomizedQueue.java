import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * Created by john.larosa on 10/1/15.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] randomQue;
    private int N;

    public RandomizedQueue()
    {
        randomQue = (Item[]) new Object[2];
        N = 0;
    }                                                                       // construct an empty randomized queue
    public boolean isEmpty()
    {
        return N == 0;
    }                                                                       // is the queue empty?
    public int size()
    {
        return N;
    }

    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for(int i = 0; i < N; i++) {
            temp[i] = randomQue[i];
        }
        randomQue = temp;
    }

    public void enqueue(Item item)
    {
        if(item == null) throw new java.lang.NullPointerException();
        if(N == randomQue.length) resize(randomQue.length * 2);
        randomQue[N++] = item;
    }
                                                                        // add the item
    public Item dequeue()
    {
        if(N == 0)
            throw new java.util.NoSuchElementException();
        int index = (int)(StdRandom.uniform(N));
        Item temp = randomQue[index];
        if(index != N-1)
            randomQue[index] = randomQue[N-1];
        randomQue[N-1] = null;
        N--;
        if(N >=0 && N == randomQue.length/4)
            resize(randomQue.length/2);
        return temp;
    }                                                                   // remove and return a random item

    public Item sample()
    {
        if(isEmpty())
            throw new java.util.NoSuchElementException();
        int random = (int)(StdRandom.uniform(N));
        Item item = randomQue[random];
        return item;
    }
                                                                        // return (but do not remove) a random item
    public Iterator<Item> iterator()
    {
        return new RandomQI();
    }

    private class RandomQI implements Iterator<Item> {
        private int index = 0;
        private Item[] random;

        public RandomQI()
        {
            random = (Item[]) new Object[N];
            for(int i = 0; i < N; i++)
                random[i] = randomQue[i];
            StdRandom.shuffle(random);
        }

        public boolean hasNext() {
            return index < N;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item item = random[index++];
            return item;
        }
    }
    //unit testing
/*    public static void main(String[] args){
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();

        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(5);
        randomQueue.dequeue();
        randomQueue.dequeue();

        StdOut.println("Output: ");
        for (Integer x : randomQueue) {
            StdOut.print(x + " ");
        }
    }*/                                                                         
}
