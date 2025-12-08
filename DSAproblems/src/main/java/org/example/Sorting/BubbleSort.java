package org.example.Sorting;

import java.util.Arrays;

public class BubbleSort {
    static void main() {
        int[] arr={5,4,3,2,1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void bubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1])
                    SelectionSort.swap(arr,j,j+1);
            }
        }
    }
}
