package edu.sdsu.cs.datastructures;

import java.util.AbstractList;
import java.util.Collection;

/**
 * Created by nicholaslograsso on 2/5/17.
 * Test using provided Assign1 TestArrayList file from instructor
 */
public class ArrayList <E> extends AbstractList <E> {

    //set fields and make reference to an array of generics
    private E[] arrayList;
    private int arraySize;
    private int arrayCapacity;


    //default constructor
    public ArrayList() {

        this.arraySize = 0;
        this.arrayCapacity = 20;
        this.arrayList = (E[]) new Object[arrayCapacity];
    }

    //overloaded constructor
    public ArrayList(Collection <E> collection) {

        this.arrayCapacity = collection.size() + 20;
        this.arrayList = (E[]) new Object[arrayCapacity];
        this.addAll(collection);
    }

    public E get(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size()) {

            throw new IndexOutOfBoundsException();
        }

        return arrayList[index];
    } //get

    public int size() {

        return arraySize;
    } //size

    public E set(int index, E element) {

            //make sure index is in range
            if (index < 0 || index >= size()) {

                throw new IndexOutOfBoundsException();
            }
            //save element to be overwritten
            E previousValue = arrayList[index];

            //set index to the element passed in
            arrayList[index] = element;
            return previousValue;
    } //set

    public void add(int index, E element) throws IndexOutOfBoundsException {

        //make sure index is in range
        if (index < 0 || index > size()) {

            throw new IndexOutOfBoundsException();
        }

        //make sure there is enough room to add the item
        if (arraySize + 1 > arrayCapacity){
            arrayList = resizeArray(arrayCapacity * 2);
            arrayCapacity = arrayCapacity * 2;
        }

        //shift necessary array elements
        System.arraycopy(arrayList, index, arrayList,
                index + 1, arraySize-index);

        //insert the new element and increment size of array
        arrayList[index] = element;
        arraySize++;
    } //add

    public E remove(int index) throws IndexOutOfBoundsException {

        if (index < 0 || index >= size()) {

            throw new IndexOutOfBoundsException();
        }

        //grab the element for removal to be returned at the end
        E removedElement = arrayList[index];

        //determine number of elements to move
        int elementsToMove = arraySize - index - 1;

        //make sure there are elements to move
        if (elementsToMove > 0){
            //shift necessary array elements
            System.arraycopy(arrayList, index+1,
                    arrayList, index, elementsToMove);
        }

        //decrement array size and clear last element since it is no longer used
        arraySize--;
        arrayList[arraySize] = null;

        return removedElement;
    } //remove

        //calls method within add
        private E[] resizeArray(int newCapacity) throws OutOfMemoryError,
                IndexOutOfBoundsException, NullPointerException {

            //create new array of specified capacity
            E[] newArray = (E[]) new Object[newCapacity];

            //copy the elements from arraylist into new array
            System.arraycopy(arrayList, 0, newArray, 0, arraySize);

            //return reference to new array
            return newArray;
        } //resizeArray

} //class
