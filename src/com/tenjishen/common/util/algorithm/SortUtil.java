package com.tenjishen.common.util.algorithm;

/**
 * 排序算法工具类
 * 
 * Created On: 2013-12-31
 * @author TENJI
 *
 */
public class SortUtil<T> {
	
	// 冒泡排序
	public static int[] bubbleSort(int[] args) {
		for (int i = 0; i < args.length - 1; i++) {
			for (int j = i + 1; j < args.length; j++) {
				if (args[i] > args[j]) {
					int temp = args[i];
					args[i] = args[j];
					args[j] = temp;
				}
			}
		}
		return args;
	}
	
	// 排序算法
	public static int[] selectSort(int[] args) {
		for (int i = 0; i < args.length - 1; i++) {
			int min = i;
			for (int j = i + 1; j < args.length; j++) {
				if (args[min] > args[j]) {
					min = j;
				}
			}
			if (min != i) {
				int temp = args[i];
				args[i] = args[min];
				args[min] = temp;
			}
		}
		return args;
	}
	
	// 插入排序
	public static int[] insertSort(int[] args) {
		for (int i = 1; i < args.length; i++) {
			for (int j = i; j > 0; j--) {
				if (args[j] < args[j - 1]) {
					int temp = args[j - 1];
					args[j - 1] = args[j];
					args[j] = temp;
				} else
					break;
			}
		}
		return args;
	}
	
	// 希尔排序，也属于插入排序的一种
    public static int[] shellSort(int[] args){  
          
        int gap = args.length/2;
        int temp;
        
        while (gap > 0) {  
            for (int i = gap; i < args.length; i++) {  
                temp = args[i];  
                int j = i - gap;  
                while (j >= 0 && temp < args[j]) {  
                	args[j+gap] = args[j];  
                    j = j - gap;  
                }  
                args[j+gap] = temp;  
            }  
            gap = gap/2;   
        } 
        
        return args;  
    }  

}
