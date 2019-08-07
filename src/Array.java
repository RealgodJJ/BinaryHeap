public class Array<T> {
    private T[] data;
    //数组当前大小
    private int size;

    /**
     * @param capacity 数组最大容量
     */
    public Array(int capacity) {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public Array(T[] num) {
        data = num;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addLast(T e) {
        addMiddle(e, size);
//        if (size == data.length)
//            throw new IllegalArgumentException("The array is full");
//
//        data[size++] = e;
    }

    public void addFirst(T e) {
        addMiddle(e, 0);
    }

    public void addMiddle(T e, int index) {
        if (index > size || index < 0)
            throw new IllegalArgumentException("The index is illegal");

        if (size == data.length)
            resize(data.length * 2);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }

    public T getElement(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("The index is illegal");

        return data[index];
    }

    public void setElement(T e, int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("The index is illegal");

        data[index] = e;
    }

    public boolean contains(T e) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(e))
                return true;

        return false;
    }

    public int search(T e) {
        for (int i = 0; i < size; i++)
            if (data[i].equals(e))
                return i;

        return -1;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLast() {
        return remove(size - 1);
    }

    public T remove(int index) {
        if (index >= size || index < 0)
            throw new IllegalArgumentException("The index is illegal");

        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        T removeNum = data[index];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        data[size] = null;

        return removeNum;
    }

    public boolean removeElement(T e) {
        int index = search(e);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal");

        T e = data[i];
        data[i] = data[j];
        data[j] = e;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        result.append("[");
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i != size - 1)
                result.append(", ");
        }
        result.append("]");
        return result.toString();
    }

    private void resize(int newCapacity) {
        T[] newData = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}
