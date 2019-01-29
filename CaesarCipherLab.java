package HM1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class CaesarCipherLab {
    // Public

    public static void main(String[] args) {
	// System.out.println((char) ('a' + 25));
	Scanner sc = new Scanner(System.in);
	System.out.println("tell me the name of the file");
	String input = sc.next();
	sc.close();
	String line = new String();
	char cur, res;
	int dis;
	StringBuffer solution = new StringBuffer();
	try (BufferedReader br = new BufferedReader(new FileReader("/Users/YimingWang/Documents/" + input + ".txt"))) {
	    line = br.readLine();
	    for (int i = 0; i < line.length(); i++) {
		cur = line.charAt(i);
		dis = (i / 3 * 2 + 5) % 26;

		if (cur >= 'a' && cur <= 'z') {
		    res = (char) (cur - dis);
		    if (res >= 'a' && res <= 'z') {
			solution.append(res);
		    } else {
			res = (char) ('z' - ('a' - res) + 1);
			solution.append(res);
		    }
		} else if (cur >= 'A' && cur <= 'Z') {
		    res = (char) (cur - dis);
		    if (res >= 'A' && res <= 'Z') {
			solution.append(res);
		    } else {
			res = (char) ('Z' - ('A' - res) + 1);
			solution.append(res);
		    }
		} else {
		    solution.append(cur);
		}
		// if()
	    }
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	// System.out.println(line);
	System.out.println(solution);
	File writename = new File("/Users/YimingWang/Documents/solution.txt");
	try {
	    writename.createNewFile();
	    BufferedWriter out = new BufferedWriter(new FileWriter(writename));
	    out.write(solution.toString());
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
}
