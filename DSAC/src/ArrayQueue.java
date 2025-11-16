public class ArrayQueue<T>{
    private final Object[] arr;
    private int front, rear, size;

    public ArrayQueue(int capacity){
        arr = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item){
        if (size == arr.length){
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = item;
        size++;
    }
    public T dequeue(){
        if (isEmpty()) return null;

        @SuppressWarnings("unchecked")
        T item = (T) arr[front];
        front = (front + 1) % arr.length;
        size--;
        return item;
    }

    public T peek(){
        if (isEmpty()) return null;
        @SuppressWarnings("unchecked")
        T item = (T) arr[front];
        return item;
    }

    private boolean isEmpty() {
        return size == 0;
    }
}