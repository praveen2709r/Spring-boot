package org.example.Sorting;

import java.util.Arrays;

public class InsertionSort {
    static void main() {
        int[] arr={5,4,3,2,1};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void insertionSort(int[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0;j--){
                if(arr[j]<arr[j-1])
                    SelectionSort.swap(arr,j,j-1);
            }
        }
    }
}
