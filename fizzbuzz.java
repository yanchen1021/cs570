package HM1;
//import java.util.ArrayList;
import java.util.Scanner;

public class fizzbuzz {
	 public static void fizzBuzz(int x,int y) {
		 //ArrayList<String> result = new ArrayList<String>();
	        for(int i = x;i <= y; i++){
	            if(i%3 == 0 && i%5 != 0){
	                System.out.println("Fizz");
	            }else if(i%5 == 0 && i%3 != 0){
	            	System.out.println("Buzz");
	            }else if(i%3 == 0 && i%5 == 0){
	            	System.out.println("FizzBuzz");
	            }else{
	            	System.out.println(i);
	            }
	        }
	        //return result;
	}
	public static void main(String[] args) {
		fizzBuzz(10,250);


	}

}
 