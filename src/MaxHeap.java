public class MaxHeap<T extends Comparable<T>> {
    private Array<T> array;

    public MaxHeap() {
        array = new Array<>();
    }

    public MaxHeap(int capacity) {
        array = new Array<>(capacity);
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.isEmpty();
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
        array.addLast(e);

        siftUp(array.getSize() - 1);
    }

    private void siftUp(int index) {
        while (index > 0 && array.getElement(index).compareTo(array.getElement(getParent(index))) > 0) {
            array.swap(index, getParent(index));
            index = getParent(index);
        }
    }

    public T extractMax() {
        T ret = findMax();

        //将最大堆的首尾元素进行交换，然后删除最大的元素
        array.swap(0, getSize() - 1);
        array.removeLast();
        siftDown(0);

        return ret;
    }

    private void siftDown(int index) {
        while (getLeftChild(index) < getSize()) {
            int j = getLeftChild(index);

            if (j + 1 < getSize() && array.getElement(j + 1).compareTo(array.getElement(j)) > 0)
                j = getRightChild(index);

            if (array.getElement(index).compareTo(array.getElement(j)) >= 0)
                break;

            array.swap(index, j);
            index = j;
        }
    }

    private T findMax() {
        if (getSize() == 0)
            throw new IllegalArgumentException("The maxHeap is null.");

        return array.getElement(0);
    }
}
