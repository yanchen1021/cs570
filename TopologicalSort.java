import java.util.Scanner;


public class TopologicalSort {
	public static void main(String[] args) {
		Scanner scan = new Scanner("infile.dat");
		String str1=scan.nextLine();
		String[] target=str1.trim().split(" ");
		int nodes=Integer.parseInt(target[0]);
		int edges=Integer.parseInt(target[1]);
        int[][] metric = new int[edges][edges];
		for(int i=0;i<edges;i++){
			
		}
		System.out.println(metric.length);
		
	}
public static void medianSort(int[][] metric){
	int[] sum = new int[metric.length];
	
	for(int i=0;i<metric.length;i++){
		sum[i]=0;
		for(int j=0;j<metric.length;j++){
			if(i!=j){
				sum[i]+=metric[i][j];
			}
		}
	}
	int median=0;
	for(int i=0;i<metric.length;i++){
		if(sum[i]<sum[median]){
			median=i;
		}
	}
	System.out.println(median);
}

public static void centerSort(int[][] metric){
	int[] dist = new int[metric.length];
	for(int i=0;i<metric.length;i++){
		dist[i]=0;
		for(int j=0;j<metric.length;j++){
			if(i!=j && metric[i][j]>dist[i]){
				dist[i]=metric[i][j];
			}
		}
	}
	int center=0;
	for(int i=0;i<metric.length;i++){
		if(dist[i]<dist[center]){
			center=i;
		}
	}
	System.out.println(center);
}
}
