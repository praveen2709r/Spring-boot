package org.example.Sorting;

import java.util.Arrays;

public class SelectionSort {
    public static void main() {
        int[] arr={9,8,7,6,5};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void selectionSort(int[] arr){
        int min;
        for(int i=0;i<arr.length-1;i++){
            min=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<arr[min])
                    min=j;
            }swap(arr,min,i);
        }
    }
    public static void swap(int[] arr,int a,int b){
        int temp=arr[a];
        arr[a]=arr[b];
        arr[b]=temp;
    }
}
