package org.example.Sorting;

public class QuickSort {
    static void main() {
        int[] arr={7,6,5,4,3};
        quickSort(arr,0,arr.length-1);
    }
    public static void partition(int[] arr,int low,int high)
    public static void quickSort(int[] arr,int low,int high){
        if(low>=high)
            return;
        int pivot=partition(arr,low,high);
        quickSort(arr,low,pivot-1);
        quickSort(arr,pivot+1,high);
    }
}

