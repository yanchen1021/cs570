package HM1;

import java.util.Scanner;

public class game {
public static void main(String[] args) {
	System.out.println("please input the size of the game");
	Scanner scan = new Scanner(System.in);
	int n=scan.nextInt();
	do{
	System.out.println("please input the row");
	int row=scan.nextInt();
	if(row>n){
		System.out.println("the row is out of bound");
	}else{
	System.out.println("please input the column");
	int column=scan.nextInt();
	if(column>n){
		System.out.println("the column is out of bound");
	}else{
	update(row,column,n,'A');
	display(n);
	System.out.println();
	}
	}
	}while(true);
}
final static char[] ch= new char[999*999];
public static void display(int n){
	for(int i=1;i<n+1;i++){
		if(i>=100 && i<1000){
		System.out.print("  "+i);
		}else if(i>=10 &&i<100){
			System.out.print("   "+i);	
		}else if(i<10){
			System.out.print("    "+i);
		}
	}
	System.out.println();
	for(int i=1;i<n+1;i++){
		System.out.print(i);
		if(i>=100 && i<1000){
			System.out.print("  "+ch[(i-1)*n]+" |");
			}else if(i>=10 &&i<100){
				System.out.print("  "+ch[(i-1)*n]+"  |");	
			}else if(i<10){
				System.out.print("   "+ch[(i-1)*n]+"  |");
			}
		for(int j=1;j<n;j++){	
					System.out.print(" "+ch[(i-1)*n+j]+"  |");

		}
		System.out.println();
		System.out.print("    ---+");
		for(int k=0;k<n-1;k++){
			System.out.print("----+");
		}
		System.out.println();
	}

}
 public static void update(int row,int column,int n,char sign){
	 if(ch[(row-1)*n+column-1]==0){
	 ch[(row-1)*n+column-1]=sign;
	 }else{
		 System.out.println("you can not place here");
	 }
 }
}
