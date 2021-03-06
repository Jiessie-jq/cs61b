//import java.util.List;
public class LinkedListDeque<T> {
    private int size;
    private Node sentinel;
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T x, Node p, Node n) {
            item = x; prev = p; next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node dummy = sentinel.next;
        while (dummy != sentinel) {
            System.out.print(dummy.item + " ");
            dummy = dummy.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return res;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return res;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        Node dummy = sentinel.next;
        while (index > 0) {
            dummy = dummy.next;
            index--;
        }
        return dummy.item;
    }

    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        return recursivehelp(sentinel.next, index);
    }

    private T recursivehelp(Node dummy, int index) {
        if (index == 0) {
            return dummy.item;
        }
        dummy = dummy.next;
        index--;
        return recursivehelp(dummy, index);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<Integer> x = new LinkedListDeque<Integer>();
//        x.addLast(0);
//        x.printDeque();
//        int a = x.removeLast();
//        System.out.println(a);
//        x.addFirst(3);
//        System.out.println(x.getRecursive(0));
//        x.removeFirst();
//        x.printDeque();
//        x.addFirst(6);
//        x.addLast(7);
//        a = x.getRecursive(1);
//        System.out.println(a);
//        x.printDeque();
//    }

}
