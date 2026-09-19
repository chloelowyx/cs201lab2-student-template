import java.util.*;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here

    public ArrayList<E> sortedArrayList(Node<E> head) {             //array of elements sorted
        Node<E> current = head;
        ArrayList<E> sorted = new ArrayList<>();
        while (current != null) {
            sorted.add(current.getElement());
            current = current.getNext();
        }
        Collections.sort(sorted);
        return sorted;
    }

    public void swap() {
        ArrayList<E> sorted = sortedArrayList(head);

        ArrayList<Node<E>> nodes = new ArrayList<>();
        Map<E, Integer> positions = new HashMap<>();
        Node<E> curr = head;
        int index = 0;
        while (curr != null) {
            nodes.add(curr);
            positions.put(curr.getElement(), index);
            curr = curr.getNext();
            index++;
        }

        for (int i = 0; i < size / 2; i++) {
            E left = sorted.get(i);
            E right = sorted.get(size - i - 1);

            if (left.equals(right)) {
                continue;
            }

            int leftindex = positions.get(left);
            int rightindex = positions.get(right);

            Node<E> temp = nodes.get(leftindex);
            nodes.set(leftindex, nodes.get(rightindex));
            nodes.set(rightindex, temp);
        }

        for (int i = 0; i < size - 1; i++) {
            nodes.get(i).setNext(nodes.get(i+1));
        }
        head = nodes.get(0);
        tail = nodes.get(size - 1);
        nodes.get(size - 1).setNext(null);

        
    }
   
}

