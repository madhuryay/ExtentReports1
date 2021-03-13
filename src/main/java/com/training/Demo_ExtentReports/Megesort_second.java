package com.training.Demo_ExtentReports;

public class Megesort_second {
	
	void merge(int arr[],int l, int m, int r)
	{
		int n1 = m-l+1;
		int n2 = r-m;
		
		int left[] =new int[n1];
		int right[] = new int[n2];
		
		for(int i=0;i<n1;i++)
			left[i]=arr[l+i];
		for(int j=0;j<n2;j++)
			right[j]=arr[m+j+1];
		
		int i=0,j=0;
		int k=l;
		
		while(i<n1 && j<n2)
		{
			if(left[i]<=right[j])
			{
				arr[k]=left[i];
				i++;
			}
			else
			{
				arr[k]=right[j];
				j++;
			}
			k++;
		}
		if(i<n1)
		{
			arr[k]=left[i];
			i++;
			k++;
		}
		if(j<n2)
		{
			arr[k]=right[j];
			j++;
			k++;
		}
	}
	
	void sort(int arr[],int l,int r)
	{
		if(l<r)
		{
			int m = l+(r-l)/2;
			
			sort(arr,l,m);
			sort(arr,m+1,r);
			
			merge(arr,l,m,r);
		}
	}
	
	void print(int arr[])
	{
		for(int i=0;i<arr.length;i++)
		{
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = {9,3,5,2,8,4,7,6};
		
		Megesort_second ob = new Megesort_second();
		
		System.out.println("Given array: ");
		ob.print(arr);
		
		ob.sort(arr, 0, arr.length-1);
		
		System.out.println("Sorted array: ");
		ob.print(arr);
	}
}
