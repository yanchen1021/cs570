package HM1;

public class BouncyBubbleSort {
public static void main(String[] args) {
	
  System.out.println();
  int[] array = new int[10];
  for(int i = 0; i < 10; i++){  //create array randomly from -100 to 100
	  array[i] =  -100 + (int)(Math.random()*201);
  }
  //int[] array ={5,6,2,1,9,0,3,7,89,34,76,89,93,57,34};
  sort(array);
}
public static int[] sort(int[] array){
	System.out.println("The original list is:");
	display(array);
	int temp=0;
	int j=1;
	do{
	boolean flag=false;
	if(j%2!=0){
	for(int i=0;i<array.length-1;i++){
		if(array[i]>array[i+1]){
			temp=array[i];
			array[i]=array[i+1];
			array[i+1]=temp;
			flag=true;
		}
	}
	}else{
		for(int i=array.length-1;i>0;i--){
			if(array[i]<array[i-1]){
				temp=array[i];
				array[i]=array[i-1];
				array[i-1]=temp;
				flag=true;
			}
		}
	}
	if(flag){
	System.out.println("step : "+j);
	display(array);
	}
	j++;
	}while(j<array.length);
	return array;
	}
public static void display(int[] array){
	for(int i=0;i<array.length;i++){
		System.out.print(array[i]+" ");
	}
	System.out.println();
}
}
