public class ArrayStack<T>{
    private final Object[] arr;
    private int top;

    public ArrayStack(int capacity){
        arr = new Object[capacity];
        top = -1;
    }

    public void push(T item){
        if (top == arr.length - 1){
            System.out.println("Stack is full");
            return;
        }
        arr[++top] = item;
    }

    public T pop(){
        if (isEmpty()) return null;
        @SuppressWarnings("unchecked")
        T item =  (T) arr[top--];
        return item;
    }

    public boolean isEmpty(){
        return top == -1;
    }
}