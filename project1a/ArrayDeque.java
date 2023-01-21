package project1a;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int capacity;
    private int nextFirst = 0;
    private int nextLast = 1;
    private int rFactor = 2;
    private double usageRatio = 0.25;
    private int capacityBase = 16;

    public ArrayDeque() {
        this(8);
    }
    public ArrayDeque(ArrayDeque other) {
        this(other.size() * 2);
        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
    private ArrayDeque(int capacity) {
        items = (T[]) new Object[capacity];
        size = 0;
        this.capacity = capacity;
    }

    public void addFirst(T item) {
        if (isFull()) {
            expandArray(rFactor);
        }
        items[nextFirst] = item;
        size++;
        nextFirst = addIndex(nextFirst, -1);
    }

    public void addLast(T item) {
        if (isFull()) {
            expandArray(rFactor);
        }
        items[nextLast] = item;
        size++;
        nextLast = addIndex(nextLast, 1);
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(get(i) + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = addIndex(nextFirst, 1);
        T first = items[nextFirst];
        items[nextFirst] = null;
        size--;
        if (isSparse()) {
            shrinkArray();
        }
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = addIndex(nextLast, -1);
        T last = items[nextLast];
        items[nextLast] = null;
        size--;
        if (isSparse()) {
            shrinkArray();
        }
        return last;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            return items[addIndex(nextFirst, 1+index)];
        }
    }

    public boolean isFull() {
        return (size == capacity);
    }

    public boolean isSparse() {
        return (size > capacityBase && size < capacity * usageRatio);
    }

    private void expandArray(int factor) {
        reshapeItems(capacity * factor);
        capacity *= factor;
    }

    private void shrinkArray() {
        reshapeItems(capacity / 2);
        capacity /= 2;
    }

    private void reshapeItems(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[addIndex(nextFirst, 1+i)];
        }
        items = newItems;
        nextFirst = newCapacity - 1;
        nextLast = size;
    }

    private int addIndex(int index, int num) {
        index += num;
        if (index < 0) {
            index += capacity;
        }
        if (index >= capacity) {
            index -= capacity;
        }
        return index;
    }
}
