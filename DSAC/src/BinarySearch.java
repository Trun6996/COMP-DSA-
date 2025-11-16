public class BinarySearch{
    public static int binarySearch(String[]arr, String key){
        int low = 0, high = arr.length -1;
        while(low <= high){
            int mid = (low + high)/2;
            int cmp = arr[mid].compareToIgnoreCase(key);
            if(cmp == 0) return mid;
            if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}