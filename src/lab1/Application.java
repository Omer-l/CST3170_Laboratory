package lab1;

import java.util.Arrays;

public class Application {
	public final static int ROWS = 500;
	public final static int COLUMNS = 7;
	
    public static void main(String[] args) {
        Integer[][] arr = generateArbitraryArrayOfIntegers();
        printSortEachColumn(arr);
    }
    
    /**
     * This function generates an array of arbitrary numbers.
     * @return	An array of arbitrary Integers
     */	
    public static Integer[][] generateArbitraryArrayOfIntegers() {
    	Integer[][] arr = new Integer[ROWS][COLUMNS];
    	
        for(int y = 0; y < ROWS; y++)
            for(int x = 0; x < COLUMNS; x++)
                arr[y][x] = (int)(Math.random() * 100);
        
        return arr;
    }

	/**
     * Selection sort algorithm to sort 2D array
     * @param arr       array to sort
     * @param sortIndex based on which index to sort.
     */
    public static void sort2DArr(Integer[][] arr, int sortIndex) {
        for(int y = 0; y < arr.length; y++) {
        	
        	int currentMinIndex = y;
        	
            for(int x = y + 1; x < arr.length; x++)
                if(arr[x][sortIndex] < arr[currentMinIndex][sortIndex])
                    currentMinIndex = x;

            if(currentMinIndex != y)
                swap2DArrayElement(arr, y, currentMinIndex);
        }
    }
    
    //Method overload
    public static<E extends Comparable<E>> void sort2DArr(E[][] arr, int sortIndex) {
    	for(int y = 0; y < arr.length; y++) {
            int currentMinIndex = y;

            for(int x = y + 1; x < arr.length; x++)
                if( arr[x][sortIndex].compareTo(arr[currentMinIndex][sortIndex]) == -1 )
                    currentMinIndex = x;

            if(currentMinIndex != y)
                swap2DArrayElement(arr, y, currentMinIndex);
        }
    }

    /**
     * 
     * @param <E>	type
     * @param arr	array to change
     * @param y1	row1
     * @param y2	row2
     */
    public static<E> void swap2DArrayElement(E[][] arr, int y1, int y2) {
        E[] tmp = arr[y1];
        arr[y1] = arr[y2];
        arr[y2] = tmp;
    }
    
    /**
     * 
     * @param <E>	type
     * @param arr	array to sort by each column
     */
    public static<E extends Comparable<E>> void printSortEachColumn(E[][] arr) {
    	for(int i = 0; i < COLUMNS; i++) {
    		sort2DArr(arr, i);
            System.out.println(("----------------------------\nOrder Column Number " + (i+1) + "\n----------------------------").toUpperCase());
            print2DArr(arr);
    	}
    }
    
    /**
     * 
     * @param <E> type
     * @param arr array
     */
    public static<E> void print2DArr(E[][] arr) {
        for(int i = 0; i < arr.length; i++)
            System.out.println(Arrays.toString(arr[i]));
    }
}