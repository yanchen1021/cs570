

import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack; 

public class Calculator {

    private static HashMap<String,Integer> PRI;  
      
    private String src;  
  
    public Calculator(String src) {  
        this.src = src;  
        if(PRI==null)  
        {  
        	PRI = new HashMap<String,Integer>(6);  
        	PRI.put("+",0);  
        	PRI.put("-",0);  
        	PRI.put("*",1);  
        	PRI.put("/",1);  
        	PRI.put("%",1);  
        	PRI.put(")",2);  
        }  
    }  
  
    public String TransformationTwo()  
    {  
        String[] expressionOne = TransformationOne(src);   
        Stack<String> StackOne = new Stack<String>();  
        Stack<String> StackTwo = new Stack<String>();  
  
        for (String a : expressionOne) {  
            if (Number(a)) {  
            	StackOne.push('('+a+')');  
            }else{  
                if(StackTwo.isEmpty())  
                {
                	StackTwo.push(a);  
                }else{  
  
                    if(Prioritylevels(StackTwo.peek(),a))  
                    {  
                        if(!a.equals(")"))  
                        {  
                            do{  
                            	StackOne.push(StackTwo.peek());  
                            	StackTwo.pop();  
                            }while(!StackTwo.isEmpty()&&(Prioritylevels(StackTwo.peek(),a)));  
                              
                            StackTwo.push(a);  
                        }else{  
                            
                            while(!StackTwo.isEmpty()&&!StackTwo.peek().equals("("))  
                            {  
                            	StackOne.push(StackTwo.pop());  
                            }  
                            if((!StackTwo.empty())&&(StackTwo.peek().equals("(")))  
                            { 
                            	StackTwo.pop();  
                            }  
                        }  
                    }else if(!Prioritylevels(StackTwo.peek(),a)){  
                    	StackTwo.push(a);  
                    }  
                }  
            }  
  
        }  
        while(!StackTwo.empty())  
        {
        	StackOne.push(StackTwo.pop());  
        }  
        StringBuilder str = new StringBuilder();  
        for (String b : StackOne) {  
                str.append(b);  
        }  
        StackOne.clear();
        String expressionTwo = str.toString();
        //System.out.println(expressionTwo);
        return expressionTwo;  
    }  
   
    private String[] TransformationOne(String src) {  
        StringBuilder str = new StringBuilder(src.length());  
        for(char ch:src.toCharArray())  
        {  
            if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')  
            {  
            	str.append(",");  
            	str.append(ch);  
            	str.append(",");  
            }else{  
            	str.append(ch);  
            }  
        }  
        String string = str.toString().replaceAll(",,", ","); 
        return string.split(",");  
    }  
   
    private boolean Prioritylevels(String pop, String str) {  
        if(str.equals(")"))  
            return true;  
        if(PRI.get(pop)==null||PRI.get(str)==null)  
          return false;  
        if(PRI.get(pop)>=PRI.get(str))
        return true; 
        else return false;
    }  
   
    public boolean Number(String str) {  
        for (char ch : str.toCharArray()) {  
            if(ch=='+'||ch=='-'||ch=='*'||ch=='*'||ch=='/'||ch=='('||ch==')'||ch=='%')  
                return false;  
        }  
        return true;  
    }  
  
    public double Result() {  
        String expression = TransformationTwo();  
        Stack<Double> result = new Stack<Double>();  
        StringBuilder str = new StringBuilder();  
        for(char ch:expression.toCharArray())  
        {   
            if(ch=='(')  
            {  
                continue;  
            }else if(ch>='0'&&ch<='9'||ch=='.'){  
            	str.append(ch);  
            }else if(ch==')')  
            {  
            	result.push(Double.valueOf(str.toString()));  
                str = new StringBuilder();  
            }else{  
                 if(!result.empty())  
                 {  
                     Double x = result.pop();  
                     Double y = result.pop();  
                     switch (ch) {  
                    case '+':  
                    	result.push(y+x);   
                        break;  
                    case '-':  
                    	result.push(y-x);   
                        break;  
                    case '*':  
                    	result.push(y*x);   
                        break;    
                    case '%':
                        if(x!=0)  
                        {    
                            result.push(y%x);   
                        }else{  
                             System.out.println("Denominator is zero!!");  
                             result.clear();  
                             return -1;  
                        }  
                        break;
                    case '/':     
                        if(x!=0)  
                        {  
                            result.push(y/x);   
                        }else{  
                             System.out.println("Denominator is zero!!");  
                             result.clear();  
                             return -1;  
                        }  
                        break;  
                    }  
            }  
            }  
        }  
        Double Finalresult = result.pop();  
        result.clear();  
        return Finalresult;  
    }  
  
    public static void main(String[] args) {
    	boolean flag=true;
    	do{
    	System.out.println("Please input the math problem or input quit to close the calculator");
    	Scanner sc= new Scanner(System.in);
        String src2 = sc.nextLine().toLowerCase();
        if(src2.equals("quit")){
        	flag=false;
        }else{
        Calculator calculator = new Calculator(src2);  
        System.out.println(src2+"="+calculator.Result()); 
        }
    	}while(flag);
    	System.out.println("The calculator is closed");
    }  
}
