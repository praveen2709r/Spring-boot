package org.example.Sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    static void main() {
        int[] arr={8,7,6,5,4};
        mergeSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    public static void mergeSort(int[] arr,int low,int high){
        if(low>=high)
            return;
        int mid=(low+high)/2;
        mergeSort(arr,low,mid);
        mergeSort(arr,mid+1,high);
        merge(arr,low,mid,high);
    }
    public static void merge(int[] arr,int low,int mid,int high){
        int left=low,right=mid+1;
        List<Integer> temp=new ArrayList<>();
        while (left <= mid && right <= high) {
            if(arr[left]<arr[right])
                temp.add(arr[left++]);
            else
                temp.add(arr[right++]);
        }
        while(left<=mid){
            temp.add(arr[left++]);
        }
        while(right<=high){
            temp.add(arr[right++]);
        }
        for(int k=low;k<=high;k++){
            arr[k]=temp.get(k-low);
        }
    }
}
