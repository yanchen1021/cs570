

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TireTree {
    private Vertex root = new Vertex();
    protected class Vertex {
        protected int words; 
        protected int prefixes; 
        protected Vertex[] edges; 
 
        Vertex() {
            this.words = 0;
            this.prefixes = 0;
            edges = new Vertex[26];
            for (int i = 0; i < edges.length; i++) {
                edges[i] = null;
            }
        }
    }
    
    public List<String> listAllWords() {
        List<String> words = new ArrayList<String>();
        Vertex[] edges = root.edges;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String word = "" + (char) ('a' + i);
                depthFirstSearchWords(words, edges[i], word);
            }
        }
        return words;
    }
 
    /**
     * 
     * @param words
     * @param vertex
     * @param wordSegment
     */
 
    private void depthFirstSearchWords(List words, Vertex vertex,
            String wordSegment) {
        if (vertex.words != 0) {
            words.add(wordSegment);
        }
        Vertex[] edges = vertex.edges;
        for (int i = 0; i < edges.length; i++) {
            if (edges[i] != null) {
                String newWord = wordSegment + (char) ('a' + i);
                depthFirstSearchWords(words, edges[i], newWord);
            }
        }
    }
 
    /**
     * 计算指定前缀单词的个数
     * 
     * @param prefix
     * @return
     */
    public int countPrefixes(String prefix) {
        return countPrefixes(root, prefix);
    }
 
    private int countPrefixes(Vertex vertex, String prefixSegment) {
        if (prefixSegment.length() == 0) { // reach the last character of the
                                            // word
            return vertex.prefixes;
        }
 
        char c = prefixSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
 
            return countPrefixes(vertex.edges[index],
                    prefixSegment.substring(1));
 
        }
 
    }
 
    /**
     * 计算完全匹配单词的个数
     * 
     * @param word
     * @return
     */
    public int countWords(String word) {
        return countWords(root, word);
    }
 
    private int countWords(Vertex vertex, String wordSegment) {
        if (wordSegment.length() == 0) { // reach the last character of the word
            return vertex.words;
        }
 
        char c = wordSegment.charAt(0);
        int index = c - 'a';
        if (vertex.edges[index] == null) { // the word does NOT exist
            return 0;
        } else {
            return countWords(vertex.edges[index], wordSegment.substring(1));
 
        }
 
    }
 
    /**
     * 向tire树添加一个词
     * 
     * @param word
     * 
     */
 
    public void addWord(String word) {
        addWord(root, word);
    }
 
    /**
     * Add the word from the specified vertex.
     * 
     * @param vertex
     *            The specified vertex.
     * @param word
     *            The word to be added.
     */
 
    private void addWord(Vertex vertex, String word) {
        if (word.length() == 0) { // if all characters of the word has been
                                    // added
            vertex.words++;
        } else {
            vertex.prefixes++;
            char c = word.charAt(0);
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (vertex.edges[index] == null) { // if the edge does NOT exist
                vertex.edges[index] = new Vertex();
            }
 
            addWord(vertex.edges[index], word.substring(1)); // go the the next
                                                                // character
        }
    }
 
    /**
     * 返回指定字段前缀匹配最长的单词。
     * 
     * @param word
     * @return
     */
    
    
    public String getMaxMatchWord(String word) {
        String s = "";
        String temp = "";
        char[] w = word.toCharArray();
        Vertex vertex = root;
        for (int i = 0; i < w.length; i++) {
            char c = w[i];
            c = Character.toLowerCase(c);
            int index = c - 'a';
            if (vertex.edges[index] == null) {
                if (vertex.words != 0){
                    return s;
                }
                else
                    return null;
            } else {
                if (vertex.words != 0)
                    temp = s;
                s += c;
                vertex = vertex.edges[index];
            }
        }
        if (vertex.words == 0)
            return temp;
        return s;
    }
    public void match(String word,String[] company,double[] count,TireTree tire){
    	for(int i=0;i<company.length;i++){
    		String c =tire.getMaxMatchWord(word);
       		/**if(c == null){                                                      try it
			System.out.println("123");
		}*/                                                   
    		if((c!=null)&&(c.equals(company[i]))){
    			count[i]++;
    		}	
    	}
    }
    public static String[] readCompany(File file){  
        BufferedReader bfr = null;                          //定义字符读取(缓冲)流  
        try{  
            bfr = new BufferedReader(new FileReader(file)); //给该流赋值  
            String value = null;                            //定义一个临时接收文件中的字符串变量  
            List<String> list = new ArrayList<String>();
            while((value = bfr.readLine()) != null){        //开始读取文件中的字符  
            	String[] mag = value.trim().split("\t");              
                if(!list.contains(mag[0])) {
                    list.add(mag[0]);
                  }
            }  
            String[] s4 = list.toArray(new String[0]);
            return s4;
            //System.out.println(s4.length);
            //System.out.println(Arrays.toString(s4));
        }  
        catch(IOException e){  
            System.out.println("文件读取错误");  
        }  
        finally{  
            try{  
                if(bfr!=null)  
                    bfr.close();  
            }  
            catch(IOException e){  
                System.out.println("文件关闭错误");  
            }  
        }
		return null;  
    }
    public static String[] readArticle(File file){  
        BufferedReader bfr = null;                           
        try{  
            bfr = new BufferedReader(new FileReader(file));  
            String value = null;                             
            Boolean flag = false;
            List<String> list = new ArrayList<String>();
            while(((value = bfr.readLine()) != null)&&(flag == false)){         
                int result1 = value.indexOf("....."); 
                if(result1 != -1) {
                	value = value.substring(0,result1);
                	String[] mag = value.replaceAll("[\\pP‘'“”]", "").trim().split(" ");              
                    for(String s : mag) {
                           list.add(s);
                  }
                    flag = true;
                }
                else{
                	String[] mag = value.replaceAll("[\\pP‘'“”]", "").trim().split(" ");              
                    for(String s : mag) {
                           list.add(s);
                  }
                }
            }  
            String[] s4 = list.toArray(new String[0]);
            return s4;
           // System.out.println(s4.length);
           // System.out.println(Arrays.toString(s4));
        }  
        catch(IOException e){  
            System.out.println("文件读取错误");  
        }  
        finally{  
            try{  
                if(bfr!=null)  
                    bfr.close();  
            }  
            catch(IOException e){  
                System.out.println("文件关闭错误");  
            }  
        }
		return null;  
    }  
    public static void main(String args[]) // Just used for test
    {
        TireTree tire = new TireTree();
        File file1 = new File("D:/company.txt");
        String[] tirecompany=readCompany(file1);                                    // original company name
        double[] count = new double[tirecompany.length];
        String[] company = new String[tirecompany.length];                          // the company name being used in calculation
        for(int i=0;i<tirecompany.length;i++){
        	String[] trans = tirecompany[i].split(" ");
        	company[i] = trans[0].toLowerCase();
        	//System.out.println(company[i]);                                        try it, already change to single word.
        	tire.addWord(company[i]);
        	count[i]=0;
        }
        File file2 = new File("D:/article.txt");
        String[] article=readArticle(file2);
        for(int i=0;i<article.length;i++){
        	//System.out.println(article[i]);                                          try it
        	tire.match(article[i], company, count, tire);
        }
        System.out.println("Company"+"   "+"Hits  "+"revelence");
        double total=0;
        for(int i=0;i<company.length;i++){
        	//System.out.println(count[i]);                                           try it
        	if(count[i]>0){
        		total+=count[i];
        		System.out.print(tirecompany[i]+"   "+count[i]+"   "+(count[i]/article.length)*100+"%");
        		System.out.println();
        	}  
        }
        System.out.println("Total"+"  "+total+"  "+total/article.length);
        System.out.println("Total Words "+article.length);
      /*List<String> list = tire.listAllWords();
        Iterator listiterator = list.listIterator();
        while (listiterator.hasNext()) {
       String s = (String) listiterator.next();
       System.out.println(s);
       }
*/
        // int count = trie.countPrefixes("abcdef");
        // int count1 = trie.countWords("abcd");
        // System.out.println("prefixes:" + count);
        // System.out.println("countWords:" + count1);
    }
}
