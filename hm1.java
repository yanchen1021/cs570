package HM1;
/*
 * the program is write by JAVA
 */
public class hm1 {
public static void main(String[] args) {
	for(int i=74;i<=291;i++){
		if(i%3==0 && i%5==0){
			System.out.println("BuzzFizz");
		}else if(i%3==0){
			System.out.println("Buzz");
		}else if(i%5==0){
			System.out.println("Fizz");
		}else{
			System.out.println(i);
		}
		
	}
}
}
