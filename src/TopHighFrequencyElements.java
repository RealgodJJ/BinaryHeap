import java.util.*;

public class TopHighFrequencyElements {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i]))
                map.put(nums[i], 1);
            else
                map.put(nums[i], map.get(nums[i]) + 1);
        }

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> {
            if (map.get(o1) < map.get(o2))
                return 1;
            else if (map.get(o1) > map.get(o2))
                return -1;
            else return 0;
        });
        for (int key : map.keySet()) {
            if (priorityQueue.getSize() < k)
                priorityQueue.enqueue(key);
            else if (map.get(key) > map.get(priorityQueue.getFront())) {
                priorityQueue.dequeue();
                priorityQueue.enqueue(key);
                //可以采用replace方法来代替以上的步骤
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.dequeue());
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 1, 1, 2, 2, 3};
        int[] nums = {4, 1, -1, 2, -1, 2, 3};

        List<Integer> result = topKFrequent(nums, 2);

        for (Integer integer : result)
            System.out.println(integer);
    }
}
