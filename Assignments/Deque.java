import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by john.larosa on 9/27/15.
 */

//deque remove from que
public class Deque<Item> implements Iterable<Item> {

    //N equals size
    private int N;                                               //number of elements on que
    private Node head;                                           //start of que
    private Node tail;                                           //end of que



    //helper linked list
    private class Node {
        private Item value;
        private Node next = null;
        private Node prev = null;
    }

    public Deque()
    {
        head = null;
        tail = null;
        N = 0;

    }                                                                   // construct an empty deque

    public boolean isEmpty()
    {
        return N == 0;
    }                                                                   // is the deque empty?

    public int size()
    {
        return N;
    }                                                                   // return the number of items on the deque

    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new NullPointerException();
        }

        Node currentNode = new Node();
        currentNode.value = item;
        currentNode.next = head;

        if(head!=null)

            head.prev = currentNode;
            head = currentNode;

        N++;

        if(tail==null)
            tail = head;
    }


    public void addLast(Item item)
    {
        if (item == null)
            throw new java.lang.NullPointerException();

        Node currentNode = new Node();
        currentNode.value = item;
        currentNode.prev = tail;

        if(tail!=null)

            tail.next = currentNode;
            tail = currentNode;

        N++;

        if(head==null)
            head = tail;
    }

    public Item removeFirst()
    {
        if(isEmpty())
            throw new NoSuchElementException();

        Item result = head.value;

        if(N==1)
            head = tail = null;
        else {
            head = head.next;
            head.prev = null;
        }
        N--;

        return result;

    }                                                                   // remove and return the item from the front

    public Item removeLast()
    {
        if(isEmpty())
            throw new java.util.NoSuchElementException();

        Item result = tail.value;

        if(N==1)
            head = tail = null;

        else {
            tail = tail.prev;
            tail.next = null;
        }
        N--;

        return result;
    }                                                                  // remove and return the item from the end

    public Iterator<Item> iterator()
    {
        return new DequeueIterator();
    }

    private class DequeueIterator implements Iterator<Item> {

         private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.value;
            current = current.next;
            return item;
        }


        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

/*    public static void main(String[] args){
        Deque<String> temp = new Deque<String>();

        temp.addLast("1");
        temp.addLast("3");
        temp.addFirst("42");
        temp.addLast("6");
        temp.addFirst("12");
        temp.addLast("35");



        for (String x: temp) {
            StdOut.println(x);
        }
    }*/
}
