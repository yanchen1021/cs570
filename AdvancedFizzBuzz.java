
package HM1;
public class AdvancedFizzBuzz {
	public static void FizzBuzzer(int arr[]){
		for(int i=0;i<arr.length;i++){
			if(arr[i]%3==0 && arr[i]%5==0){
				System.out.println("BuzzFizz");
			}else if(arr[i]%3==0){
				System.out.println("Buzz");
			}else if(arr[i]%5==0){
				System.out.println("Fizz");
			}else{
				System.out.println(arr[i]);
			}
			
		}
	}
	
public static void main(String[] args) {
	int[] arr= new int[241];
	int start=10;
	for(int i=0;i<241;i++){
		arr[i]=start;	
		start++;
	}
	FizzBuzzer(arr);
}
}
