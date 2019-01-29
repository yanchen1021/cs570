import java.util.TreeMap;

public class RedBlackTree {
	static class SortedDictionary{
		private TreeMap<String, String> redBlack = new TreeMap<String, String>();
		
		public void insert(String key, String value){
			redBlack.put(key, value);
		}
		
		public String retrieve(String key){
			return redBlack.get(key);
		}
		
		public void delete(String key){
			redBlack.remove(key);
		}
		
		public boolean isExist(String key){
			return redBlack.containsKey(key);
		}
				
		///////////////some other methods///////////////////////////////////		
		//get the smallest key
		public String getSmallestKey(){
			return redBlack.firstKey();
		}
		//get the largest key
		public String getLargestKey(){
			return redBlack.lastKey();
		}
		// get the size of the dictionary
		public int size(){
			return redBlack.size();
		}
		
	}
	
	public static void main(String[] args){
		SortedDictionary dict = new SortedDictionary();
		dict.insert("hello", "world");
		dict.insert("goodbye", "everyone");
		dict.insert("name", "student");
		dict.insert("occupation", "student");
		dict.insert("year", "2016");
		dict.insert("gpa", "4.0");
		dict.insert("lab", "yes");
		dict.insert("assignment", "no");
		dict.insert("department", "cs");
		
		String gpa = dict.retrieve("gpa");
		String department = dict.retrieve("department");
		System.out.println(gpa);
		System.out.println(department);		
		
	}

}
