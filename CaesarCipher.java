package HM1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CaesarCipher {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Pleast input the name of the file");
		String input=sc.next();
		String filePath="src/"+input+".txt";
		StringBuffer sol = new StringBuffer();
		try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ 
                InputStreamReader read = new InputStreamReader(
                new FileInputStream(file),encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    System.out.println(lineTxt);
                    for(int i=0;i<lineTxt.length();i++){
                    	char x=lineTxt.charAt(i);
                    	int start=(i/3*2+5)%26;
                    	if(x>=65 && x<=90){
                        	x-=start;
                        	if(x<65){
                        		x=(char) (90-(65-x)+1);
                        	}
                        	sol.append(x);
                    	}
                       else if(x>=97 && x<=122){
                    	x-=start;
                    	if(x<97){
                    		x=(char) (122-(97-x)+1);
                    	}
                    	sol.append(x);
                	}else{
                		sol.append(x);
                    	}
                    	
                    }
                }
                read.close();
    }else{
        System.out.println("file do not exist");
    }
    } catch (Exception e) {
        System.out.println("error");
        e.printStackTrace();
    }
		System.out.println(sol);
		File solution = new File("src/solution.txt");
		if(!solution.exists()){
			try {
				solution.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			solution.createNewFile();
		    BufferedWriter out = new BufferedWriter(new FileWriter(solution));
		    out.write(sol.toString());
		    out.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
