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

    public void swap(){
        ArrayList<E> sorted = sortedArrayList(head);                //array of elements sorted
        int arrSize = size;                                        
        Node<E> left = head;                                        //left node to swap
        Node<E> leftprev = null;                                    //previous node to left node

        while (left != null) {                                      //traverse all nodes in LL
            int leftindex = sorted.indexOf(left.getElement());      //index of left element in sorted array
            if (leftindex < 0 || leftindex >= arrSize) {            //if element not found in sorted array
                leftprev = left;                                    //means swapped already
                left = left.getNext();
                continue;                                           //continue to next node
            }
            int rightindex = arrSize - leftindex - 1;               //index of other element to be swapped
            if (leftindex == rightindex) {                          //middle element
                leftprev = left;                                    //no need swap
                left = left.getNext();
                continue;                                           //continue to next node
            }

            Node<E> right = left.getNext();                         //finding other node to swap
            Node<E> rightprev = left;
            while (!right.getElement().equals(sorted.get(rightindex))) {
                rightprev = right;
                right = right.getNext();
            }

            E leftelement = left.getElement();                      //left element to be swapped
            E rightelement = right.getElement();                    //right element to be swapped

            Node<E> leftnext = left.getNext();                      //node manipulation to swap nodes
            Node<E> rightnext = right.getNext();
            left.setNext(rightnext);
            if (leftnext.equals(right)) {                           //if nodes side by side
                right.setNext(left);
            } else {                                                //if nodes separated by at least one node
                right.setNext(leftnext);
                rightprev.setNext(left);
            }

            if (leftprev != null) {                                 //if left node not head node
                leftprev.setNext(right);
            }

            if (head.equals(left)) {                                //if any change in head node
                head = right;
            }

            if (tail.equals(right)) {                               //if any change in tail node
                tail = left;
            }

            sorted.remove(leftelement);                             //remove swapped elements from sorted array
            sorted.remove(rightelement);

            leftprev = right;                                       //next node
            left = right.getNext();
            arrSize -= 2;                                           //decrease array size
            
        }

    }
   
}

