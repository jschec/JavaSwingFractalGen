import java.io.Serializable;
import java.util.Iterator;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * This is a working version of the author's code for Array List
 *
 * @param   <E>     generic data value
 */
public class ArrayList<E> implements Serializable {
    /** list of values */
    private E[] elementData;
    /** current number of elements in the list */
    private int size;
    /** default array capacity */
    public static final int DEFAULT_CAPACITY = 50;

    /**
     * Constructor; sets up an empty array list of default capacity.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor; sets up an empty array list of a designated capacity.
     * Throws IllegalArgumentException if capacity is smaller than 0.
     *
     * @param   capacity    designated capacity of array list
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity: " + capacity);
        }
        // initialize array of objects to designated capacity
        elementData = (E[]) new Object[capacity];
        // initialize array list size to 0
        size = 0;
    }

    /**
     * Retrieves the current number of elements in the array list.
     *
     * @return      current number of array list elements
     */
    public int size() {
        return size;
    }

    /**
     * Retrieves the value of the given index in the list.
     *
     * @param   index   index value
     * @return          the value of the element in the given index
     */
    public E get(int index) {
        // checks if index is valid, otherwise throw IndexOutOfBoundsException
        checkIndex(index);
        return elementData[index];
    }

    /**
     * Retrieves a comma-separated, bracketed string representation
     * of the Array List and its values.
     *
     * @return      string representation of Array List
     */
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            String result = "[" + elementData[0];
            for (int i = 1; i < size; i++) {
                result += ", " + elementData[i];
            }
            result += "]";
            return result;
        }
    }

    /**
     * Retrieves the position of the first occurrence of the given value.
     *
     * @param   value   array list element value
     * @return          index of first occurrence of value; -1 if value has no occurrence
     */
    public int indexOf(E value) {
        // linear search for first element that equals designated value
        for (int i = 0; i < size; i++) {
            // breaks loop if value found in an element
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        // -1 returned if value not found in an element
        return -1;
    }

    /**
     * Retrieves the boolean representation of at least one value
     * being present in the array list.
     *
     * @return      true if the array list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retrieves the boolean representation of the occurrence of the
     * designated value in the array list.
     *
     * @param   value   sought value
     * @return          true if value is in array list, false otherwise
     */
    public boolean contains(E value) {
        return indexOf(value) >= 0;
    }

    /**
     * Appends the value of an array list element to the end of this
     * array list.
     *
     * @param   value   array list element value
     */
    public void add(E value) {
        // if new array list capacity is greater than array length, at least double capacity
        ensureCapacity(size + 1);
        // add new value at the end of the array
        elementData[size] = value;
        // update size of array list
        size++;
    }

    /**
     * Inserts the value of an array list element to a designated index,
     * thereby shifting the subsequent elements in the array list to the right
     * by one.
     *
     * @param   index   position in array list to insert array list element
     * @param   value   array list element value
     */
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
        // if new array list capacity is greater than array length, at least double capacity
        // of this array
        ensureCapacity(size + 1);

        // move values of each array element by one to the right of target index
        for (int i = size; i >= index + 1; i--) {
            elementData[i] = elementData[i - 1];
        }
        // map new value at designated index
        elementData[index] = value;
        // update size of array list
        size++;
    }

    /**
     * Removes the value of an array list element at a designated index,
     * thereby shifting the subsequent elements in the array list to the left
     * by one.
     *
     * @param   index   position in array list to insert array list element
     */
    public void remove(int index) {
        // checks if index is valid, otherwise throw IndexOutOfBoundsException
        checkIndex(index);
        // move values of each array element following target index to the
        // left by one
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        // update size of array list
        size--;
    }

    /**
     * Replaces the value of an array list element at a designated index in
     * this array list.
     *
     * @param   index   position in array list to insert array list element
     * @param   value   array list element value
     */
    public void set(int index, E value) {
        // checks if index is valid, otherwise throw IndexOutOfBoundsException
        checkIndex(index);
        elementData[index] = value;
    }

    /**
     * Deletes all items in the array list.
     */
    public void clear() {
        // goes through each array element in linear fashion and set to null
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        // update array list size
        size = 0;
    }

    /**
     * Appends the values of a given array list to the end of this list.
     *
     * @param   other   a given array list
     */
    public void addAll(ArrayList<E> other) {
        // if new array list capacity is greater than array length, at least double capacity
        ensureCapacity(size + other.size);
        // go through each element in new array and append to this array
        for (int i = 0; i < other.size; i++) {
            add(other.elementData[i]);
        }
    }

    /**
     * Returns an iterator for iterating through elements of the array list.
     *
     * @return  an iterator object for this array list.
     */
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Ensures that the underlying array has the given capacity; if not,
     * the size is doubled (or more if a given capacity is even larger).
     *
     * @param   capacity    new capacity of array list
     */
    public void ensureCapacity(int capacity) {
        if (capacity > elementData.length) {
            // at least double length if current array length is smaller
            // than new capacity
            int newCapacity = elementData.length * 2 + 1;
            if (capacity > newCapacity) {
                // if calculated new capacity is too small, use designated capacity
                newCapacity = capacity;
            }
            // copy array elements of this array into new array with updated capacity
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    /**
     * Throws an IndexOutOfBoundsException if the given index is
     * not a legal index of this array list.
     *
     * @param   index   designated element in array list
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index: " + index);
        }
    }

    /**
     * Provides a means to iterate through elements in an array list while
     * preserving the structure of the array list.
     */
    private class ArrayListIterator implements Iterator<E> {
        /** current position within the list */
        private int position;
        /** whether it's okay to remove now */
        private boolean removeOK;

        /**
         * Constructor; sets up an iterator for the given array list.
         */
        public ArrayListIterator() {
            position = 0;
            removeOK = false;
        }

        /**
         * Checks if there are remaining elements in the array list.
         *
         * @return      true if there are more elements left, false otherwise
         */
        public boolean hasNext() {
            return position < size();
        }

        /**
         * Retrieves the element in the next index of the array list.
         * Throws a NoSuchElement exception if not.
         *
         * @return      the next element in array list
         */
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = elementData[position];
            // update current position
            position++;
            // allow for removal of element in iterable class if this
            // method called
            removeOK = true;
            return result;
        }

        /**
         * Removes the last element returned by the iterator. Throws
         * IllegalStateException if this method is called without
         * first calling next() method.
         */
        public void remove() {
            if (!removeOK) {
                throw new IllegalStateException();
            }
            // remove specified element
            ArrayList.this.remove(position - 1);
            // update current position
            position--;
            // don't allow another removal until next() method
            // called again
            removeOK = false;
        }
    }
}
