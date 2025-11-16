import java.util.*;

public class BookStoreSys {
    private static final ArrayQueue<Order> orderQueue = new ArrayQueue<>(50);
    private static final ArrayStack<String> adminActions = new ArrayStack<>(50);
    private static final Scanner sc = new Scanner(System.in);

    static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n====GREENWICH ONLINE BOOKSTORE SYSTEM====");
            System.out.println("1. Create new Order");
            System.out.println("2 Process next order(Sort Books)");
            System.out.println("3 Search book in current order");
            System.out.println("4 Undo least action");
            System.out.println("5 Exit");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please choose a number:");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addNewOrder();
                case 2 -> processNextOrder();
                case 3 -> searchBookInOrder();
                case 4 -> undoLastAction();
                case 5 -> System.out.println("Exiting Book System...See you later!");
                default -> System.out.println("Invalid option. Try again.");
            }
        }while(choice!=5);
    }
    private static void addNewOrder(){
        System.out.println("Enter Order ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter number of Books:");
        int count = sc.nextInt();
        sc.nextLine();

        String[]books = new String[count];

        for (int i = 0; i < count; i++){
            System.out.println("Enter Book" + (i + 1) + ":");
            books[i] = sc.nextLine();
        }
        orderQueue.enqueue(new Order(id, books));
        adminActions.push("Added Order #" + id);
        System.out.println("[Queue] Order#" + id + "Added Successfully!");
    }

    private static void processNextOrder(){
        Order current = orderQueue.dequeue();

        if (current == null) {
            System.out.println("Invalid! No orders to process.");
            return;
        }

        System.out.println("\nProcessing Order #" + current.id);
        System.out.println("Before Sorting:" + Arrays.toString(current.books));

        QuickSort.quickSort(current.books, 0, current.books.length - 1);

        System.out.println("After Sorting:" + Arrays.toString(current.books));

        adminActions.push("Processed order#" + current.id);
    }

    private static void searchBookInOrder(){
        Order current = orderQueue.peek();

        if (current == null){
            System.out.println("Invalid! No orders available for searching.");
            return;
        }

        System.out.print("Enter book title to search:");
        String key = sc.nextLine();

        String[] sortedCopy = Arrays.copyOf(current.books, current.books.length);
        QuickSort.quickSort(sortedCopy, 0, sortedCopy.length - 1);

        int result = BinarySearch.binarySearch(sortedCopy, key);
        if (result!= -1)
            System.out.println(" Found at position " +result);
        else
            System.out.println(" Not Found ");
    }

    private static void undoLastAction(){
        if(adminActions.isEmpty()){
            System.out.println("No actions to undo.");
        }else{
            System.out.println("[Undo]" + adminActions.pop());
        }
    }
}
