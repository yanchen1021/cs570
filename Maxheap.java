import java.util.Scanner;

public class Maxheap {
	public static void heapsort(int [] numberarray){
		buildheap(numberarray);
		for(int i=numberarray.length-1;i>=1;i--){
			swap(numberarray,0,i);
			makeheap(numberarray,0,i);
		}
	}
	public static void buildheap(int []numberarray){
		for(int i=numberarray.length/2-1;i>=0;i--){
			makeheap(numberarray,i,numberarray.length);
		}
	}
	public static void makeheap(int []a,int parent,int max){
		int left=2*parent+1;
		int right=2*parent+2;
		int largest=0;
		if(left<max && a[left]>a[parent]){
			largest=left;
		}
		else{
			largest=parent;
		}
		if(right<max && a[right]>a[largest]){
			largest=right;
		}
		if(largest!=parent){
			swap(a,largest,parent);
			makeheap(a,largest,max);
		}
	}
	public static void swap(int []numberarray,int i,int j){
		int temp=numberarray[i];
		numberarray[i]=numberarray[j];
		numberarray[j]=temp;
	}
	public static void main(String []args){
		int []a=new int[10];
		Scanner scanner=new Scanner(System.in);
		System.out.println("please input 10 numbers a line(eg:1 2 3 4... 10):");
		String str=scanner.nextLine();
		String[] target=str.trim().split(" ");
		for(int i=0;i<10;i++){
			a[i]=Integer.parseInt(target[i]);
		}
		System.out.println("before heap sort:");
		for(int i=0;i<a.length;i++){
			System.out.print(a[i]+" ");
		}
		System.out.println();
		heapsort(a);
		System.out.println("after heap sort:");
		for(int i=9;i>=0;i--){
			System.out.print(a[i]+" ");
		}
		System.out.println();
		scanner.close();	
	}

}
