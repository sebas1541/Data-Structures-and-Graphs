package co.edu.uptc.structures;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class MyList<T> implements List<T> {

    private Node<T> head;

    public MyList() {
        this.head = null;
    }


    public static <T> boolean isInstanceOf(Class<T> tclass, Object o){
        return tclass.isInstance(o);
    }
    @Override
    public int size() {
        Node<T> aux = head;
        int count = 0;
        while (aux != null){
            count++;
            aux = aux.getNext();
        }return count;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(Object o) throws ClassCastException, NullPointerException{

        if (o == null){
            throw new NullPointerException();
        }

        if (!isInstanceOf(o.getClass(), head.getData())){
            throw new ClassCastException("El tipo de la clase no es igual");
        }


        Node aux = head;
        while(aux != null && aux.getNext() != null){
            if (aux.getData().equals(o)){
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> lastReturned = null;
            Node<T> currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public T next() {
                lastReturned = currentNode;
                currentNode = currentNode.getNext();
                return lastReturned.getData();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[]currenArray = new Object[size()];
        int i = 0;
        for (Node<T> aux = head; aux != null; aux = aux.getNext()){
            currenArray[i++] = aux.getData();
        }
        return currenArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) throws NullPointerException, ClassCastException {

        if (!isInstanceOf(t.getClass(), head.getData())){
            throw new ClassCastException();
        }

        if (t == null){
            throw new NullPointerException();
        }

        Node<T> newNode = new Node<T>(t);

        if (isEmpty()){
            head = newNode;

        }else{
            Node<T> aux = head;
            while (aux.getNext() != null){
                aux = aux.getNext();
            }
            aux.setNext(newNode);
            newNode.setPrevious(aux);
        }return true;
    }

    @Override
    public boolean remove(Object o) throws NullPointerException{

        if (o == null){
            throw new NullPointerException();
        }

        if (head == null) {
            return false;
        }
        Node<T> current = head;

        if (head.getData().equals(o)) {
            if (head.getNext() != null) {
                head = head.getNext();
                head.setPrevious(null);
            } else {
                head = null;
            }
            return false;
        }

        while (current != null && !current.getData().equals(o)) {
            current = current.getNext();
        }
        if (current == null) {
            return false;
        }
        if (current.getNext() != null) {
            current.getNext().setPrevious(current.getPrevious());
        }
        if (current.getPrevious() != null) {
            current.getPrevious().setNext(current.getNext());
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        Node<T> aux = head;
        if (index < 0 || index >= size() ){
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++){
            aux = aux.getNext();
        }
        return aux.getData();
    }

    @Override
    public T set(int index, T element) {
        Node<T> aux = head;
        if (index < 0 || index >= size() ){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < index; i++){
            aux = aux.getNext();
        }

        T auxVal = aux.getData();
        aux.setData(element);
        return auxVal;
    }

    @Override
    public void add(int index, T element) throws NullPointerException {
        Node<T> aux = head;

        if (index < 0 || index >= size()){
            throw new NullPointerException();
        }

        if (index == 0){
            Node<T> newNode = new Node<T>(element);
            newNode.setNext(head);
            if (head != null){
                head.setPrevious(newNode);
            }
            head = newNode;
        }else {
            for (int i = 0; i <index; i++) {
                aux = aux.getNext();
            }

        }
    }

    @Override
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }


    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }

    @Override
    public void addFirst(T t) {
        List.super.addFirst(t);
    }

    @Override
    public void addLast(T t) {
        List.super.addLast(t);
    }

    @Override
    public T getFirst() {
        return List.super.getFirst();
    }

    @Override
    public T getLast() {
        return List.super.getLast();
    }

    @Override
    public T removeFirst() {
        return List.super.removeFirst();
    }

    @Override
    public T removeLast() {
        return List.super.removeLast();
    }

    @Override
    public List<T> reversed() {
        return List.super.reversed();
    }

    @Override
    public <T1> T1[] toArray(IntFunction<T1[]> generator) {
        return List.super.toArray(generator);
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return List.super.removeIf(filter);
    }

    @Override
    public Stream<T> stream() {
        return List.super.stream();
    }

    @Override
    public Stream<T> parallelStream() {
        return List.super.parallelStream();
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        List.super.forEach(action);
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
}
