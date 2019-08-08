import java.util.Random;

public class Main {

    public static double testHeap(Integer[] array, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(array);
        } else {
            maxHeap = new MaxHeap<>();
            for (Integer integer : array)
                maxHeap.add(integer);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {
        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = maxHeap.extractMax();

        for (int i = 1; i < n; i++)
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");

        System.out.println("Test MaxHeap completed.");

        System.out.println("=========== next test ===========");

        Integer[] data = new Integer[n];
        for (int i = 0; i < n; i++) {
            data[i] = random.nextInt(Integer.MAX_VALUE);
        }
        double time1 = testHeap(data, false);
        System.out.println("Without heapify: " + time1 + "s");

        double time2 = testHeap(data, true);
        System.out.println("With heapify: " + time2 + "s");
    }
}
