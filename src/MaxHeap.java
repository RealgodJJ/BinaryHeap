public class MaxHeap<T extends Comparable<T>> {
    private Array<T> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap(T[] array) {
        data = new Array<>(array);
        for (int i = getParent(data.getSize() - 1); i > 0; i--)
            siftDown(i);
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int getParent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("Index-0 doesn't have parent.");

        return (index - 1) / 2;
    }

    private int getLeftChild(int index) {
        return index * 2 + 1;
    }

    private int getRightChild(int index) {
        return index * 2 + 2;
    }

    public void add(T e) {
        data.addLast(e);

        siftUp(data.getSize() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && data.getElement(index).compareTo(data.getElement(getParent(index))) > 0) {
            data.swap(index, getParent(index));
            index = getParent(index);
        }
    }

    public T extractMax() {
        T ret = findMax();

        //将最大堆的首尾元素进行交换，然后删除最大的元素
        data.swap(0, getSize() - 1);
        data.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int index) {
        while (getLeftChild(index) < getSize()) {
            int j = getLeftChild(index);

            if (j + 1 < getSize() && data.getElement(j + 1).compareTo(data.getElement(j)) > 0)
                j = getRightChild(index);

            if (data.getElement(index).compareTo(data.getElement(j)) >= 0)
                break;

            data.swap(index, j);
            index = j;
        }
    }

    public T replace(T e) {
        //找到待删除的元素
        T ret = findMax();
        //将最大堆中的最大元素设置为e
        data.setElement(e, 0);
        siftDown(0);
        return ret;
    }

    private T findMax() {
        if (getSize() == 0)
            throw new IllegalArgumentException("The maxHeap is null.");

        return data.getElement(0);
    }
}
