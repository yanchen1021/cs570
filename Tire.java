import java.util.ArrayList;
import java.util.List;


public class Tire {
private vertex root = new vertex();
protected class vertex{
	protected int words;
	protected int prefixs;
	protected vertex[] edges;
	vertex(){
		this.words = 0;
		this.prefixs = 0;
		this.edges = new vertex[26];
		for ( int i = 0;i < 26;i++ ){
			edges[i] = null;
		}
	}
}

}
