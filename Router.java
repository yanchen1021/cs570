import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;



class Lsp{
private int originateId;
private String originateNetwork;
private int sendId;
private int number;
private int ttl;
private int linkCost;
private HashMap<Integer, Integer> directLink;
private ArrayList<Integer> passRouter;
public int getOriginateId() {
	return originateId;
}

public void setOriginateId(int i) {
	this.originateId = i;
}

public int getNumber() {
	return number;
}

public void setNumber(int number) {
	this.number = number;
}

public int getTtl() {
	return ttl;
}

public void setTtl(int ttl) {
	this.ttl = ttl;
}

public HashMap<Integer, Integer> getDirectLink() {
	return directLink;
}

public void setDirectLink(HashMap<Integer, Integer> directLink) {
	this.directLink = directLink;
}

public int getSendId() {
	return sendId;
}

public void setSendId(int sendId) {
	this.sendId = sendId;
}
public void setLinkCost(int linkCost) {
	this.linkCost = linkCost;
}
public int getLinkCost() {
	return linkCost;
}

public ArrayList<Integer> getPassRouter() {
	return passRouter;
}

public void setPassRouter(ArrayList<Integer> passRouter) {
	this.passRouter = passRouter;
}

public Lsp(){
	setTtl(10);
}

public String getOriginateNetwork() {
	return originateNetwork;
}

public void setOriginateNetwork(String originateNetwork) {
	this.originateNetwork = originateNetwork;
}
}
class RouterTable{
private String networkName;
private int cost;
private String outgoingLink;
public String getNetworkName() {
	return networkName;
}
public void setNetworkName(String networkName) {
	this.networkName = networkName;
}
public int getCost() {
	return cost;
}
public void setCost(int cost) {
	this.cost = cost;
}
public String getOutgoingLink() {
	return outgoingLink;
}
public void setOutgoingLink(String outgoingLink) {
	this.outgoingLink = outgoingLink;
}
@Override
public String toString() {
	return "networkName=" + networkName + ", cost=" + cost
			+ ", outgoingLink=" + outgoingLink;
}
}
public class Router {
private int number;
private int routerId;
private String networkName;
private int networkCost;
private HashMap<Integer, Integer> directLink;
private boolean start;
private final static int INF=Integer.MAX_VALUE;
private static int Nodes;
private static int[][] dist;
private Set<Integer> open=new HashSet<Integer>();  
private Set<Integer> close=new HashSet<Integer>(); 
private Map<Integer,Integer> path=new HashMap<Integer,Integer>(); 
private Map<Integer,String> pathInfo=new HashMap<Integer,String>();
private HashMap<Integer,Integer> lspSequence;
private HashMap<String,RouterTable> rt;
public static HashMap<Integer,Integer> trans1 = new HashMap<Integer, Integer>();
public static HashMap<Integer,Integer> trans2 = new HashMap<Integer, Integer>();
public Router(){
	this.rt = new HashMap<String, RouterTable>();
	this.directLink = new HashMap<Integer, Integer>();
	this.lspSequence = new HashMap<Integer, Integer>();
}

public int getRouterId() {
	return routerId;
}

public void setRouterId(int routerId) {
	this.routerId = routerId;
}

public String getNetworkName() {
	return networkName;
}

public void setNetworkName(String networkName) {
	this.networkName = networkName;
}

public HashMap<Integer, Integer> getDirectLink() {
	return directLink;
}

public void setDirectLink(HashMap<Integer, Integer> directLink) {
	this.directLink = directLink;
}

public boolean isStart() {
	return start;
}
public void setStart(boolean start) {
	this.start = start;
}
public int getNetworkCost() {
	return networkCost;
}
public void setNetworkCost(int networkCost) {
	this.networkCost = networkCost;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public HashMap<Integer, Integer> getLspSequence() {
	return lspSequence;
}

public void setLspSequence(HashMap<Integer, Integer> lspSequence) {
	this.lspSequence = lspSequence;
}

public HashMap<String, RouterTable> getRt() {
	return rt;
}

public void setRt(HashMap<String, RouterTable> rt) {
	this.rt = rt;
}

public static int getNodes() {
	return Nodes;
}

public static void setNodes(int nodes) {
	Nodes = nodes;
}

public static int[][] getDist() {
	return dist;
}

public static void setDist(int[][] dist) {
	Router.dist = dist;
}

public Set<Integer> getOpen() {
	return open;
}

public void setOpen(Set<Integer> open) {
	this.open = open;
}

public Set<Integer> getClose() {
	return close;
}

public void setClose(Set<Integer> close) {
	this.close = close;
}

public Map<Integer, Integer> getPath() {
	return path;
}

public void setPath(Map<Integer, Integer> path) {
	this.path = path;
}

public Map<Integer, String> getPathInfo() {
	return pathInfo;
}

public void setPathInfo(Map<Integer, String> pathInfo) {
	this.pathInfo = pathInfo;
}

public static HashMap<Integer, Integer> getTrans1() {
	return trans1;
}

public static void setTrans1(HashMap<Integer, Integer> trans1) {
	Router.trans1 = trans1;
}

public static HashMap<Integer, Integer> getTrans2() {
	return trans2;
}

public static void setTrans2(HashMap<Integer, Integer> trans2) {
	Router.trans2 = trans2;
}

@Override
public String toString() {
	return "Router [routerId=" + routerId + ", networkName=" + networkName
			+ ", networkCost=" + networkCost + ", start=" + start + "]";
}

public void getRouterT(HashMap<Integer,Router> list){
	if(isStart()){
	int t=getRouterId();
	init(t);
	Dijkstra(t);
    Iterator<Map.Entry<Integer, String>> pathInfos = pathInfo.entrySet().iterator();
    HashMap<String, RouterTable> rtlist = getRt();
	while(pathInfos.hasNext()){
		Map.Entry<Integer, String> pt = pathInfos.next();
		int routerId = pt.getKey();
		if(routerId!=getRouterId()){
    	Router r = list.get(routerId);
    	if(r.isStart()){
    	String networkName = r.getNetworkName();
    	String outgoinglink = pt.getValue();
    	RouterTable rt= new RouterTable();
    	rt.setOutgoingLink(outgoinglink);
    	rt.setNetworkName(networkName);
        rtlist.put(networkName, rt);
    	}
		}
	}
	 Iterator<Map.Entry<Integer, Integer>> paths = path.entrySet().iterator();
		while(paths.hasNext()){
			Map.Entry<Integer, Integer> pts = paths.next();
			int routerId = pts.getKey();
			if(routerId!=getRouterId()){
	    	Router r = list.get(routerId);
	    	if(r.isStart()){
	    	String networkName = r.getNetworkName();
	    	 int cost = pts.getValue();
	    	 RouterTable rt= new RouterTable();
	         rt = rtlist.get(networkName);
	         rt.setCost(cost+r.getNetworkCost());
	         rtlist.put(networkName, rt);
			}
			}
		}
	}
	}
public void init(int moujiedian){  
    //����ʼ·�� 
	int moujied = moujiedian;
	for(int ia = 0; ia< Nodes; ia++){
		path.put(ia, dist[moujied][ia]);
		//System.out.println(ia+","+path.get(moujiedian));
		pathInfo.put(ia, trans2.get(moujiedian)+"->"+trans2.get(ia)); 
		//System.out.println(ia+","+pathInfo.get(moujiedian));
		if(ia == moujied){
			close.add(ia);
		}else{
			open.add(ia);
		}
	}
}   
public void Dijkstra(int a){ 
    int nearest=getShortestPath(a);
    if(nearest==-1){  
        return;  
    }  
    close.add(nearest);  
    open.remove(nearest); 
    int z01 =  nearest;             
    for(int ii = 0; ii<Nodes; ii++){
    	if( (dist[z01][ii] != 0)&&(dist[z01][ii] != INF)){
    		if(open.contains(ii)){
                   int newpath = path.get(nearest)+dist[z01][ii];
                   if(path.get(ii)>newpath){
                       path.put(ii, newpath);  
                       pathInfo.put(ii, pathInfo.get(nearest)+"->"+trans2.get(ii));  
    			
    		}
    	}
    }    
  }
    Dijkstra(a);
    Dijkstra(nearest);	
}
private int getShortestPath(int a){  
    int res=-1;  
    int minDis= INF;  
    int k =  a;
    for(int i = 0; i<Nodes;i++){
    	if( (dist[k][i] != 0)&&(dist[k][i] != INF)){
    		if(open.contains(i)){
                int distance = dist[k][i];
                if(distance<minDis){  
                    minDis=distance;  
                    res=i;         			
    		}
    	}
    }  

}
	return res; 
}
public void receivePackage(Lsp lsp,HashMap<Integer,Router> routerlist,int[][] dist){
	if(!isStart()){
		return;
	}
	if(lsp.getSendId()!=getRouterId() || lsp.getTtl()<=0){
		return;
	}else{
	lsp.setTtl(lsp.getTtl()-1);
	HashMap<Integer, Integer> directLink = getDirectLink();
	HashMap<Integer, Integer> lspSequence = getLspSequence();
	int getId=lsp.getOriginateId();
	if(lspSequence.containsKey(getId) && (lspSequence.get(getId)!=null && lspSequence.get(getId)>=lsp.getNumber())){
			return;
		}else{
			lspSequence.put(getId, lsp.getNumber());
			ArrayList<Integer> pass = lsp.getPassRouter();
			pass.add(getRouterId());
			lsp.setPassRouter(pass);
			for(Map.Entry<Integer, Integer> entry:directLink.entrySet()){
				int routerId = entry.getKey();
				int linkCost = entry.getValue();
				Router r = routerlist.get(routerId);
				if(r.isStart()){
				dist[getRouterId()][routerId] = linkCost;
				lsp.setSendId(routerId);
				lsp.setLinkCost(lsp.getLinkCost()+linkCost);
				Router router = routerlist.get(routerId);
				router.receivePackage(lsp, routerlist,dist);
				}
			}		
		}
	}
}
public Lsp originatePacket(){
	Lsp lsp = new Lsp();
	lsp.setOriginateId(getRouterId());
	lsp.setDirectLink(getDirectLink());
	lsp.setOriginateNetwork(getNetworkName());
	lsp.setNumber(getNumber());
	lsp.setLinkCost(0);
	ArrayList<Integer> pass= new ArrayList<Integer>();
	pass.add(getRouterId());
	lsp.setPassRouter(pass);
	setNumber(getNumber()+1);
	return lsp;
}
public void shutdown(Router router){
	router.setStart(false);
}
public void print(){
	System.out.println("-----------------------------------------------");
	HashMap<String, RouterTable> rtlist = this.getRt();
	Iterator<Map.Entry<String, RouterTable>> en = rtlist.entrySet().iterator();
	while(en.hasNext()){
		Map.Entry<String, RouterTable> ent = en.next();
		RouterTable rt = ent.getValue();
		System.out.println(rt);
	}
	System.out.println("-----------------------------------------------");
}
public static void continued(HashMap<Integer,Router> list,int[][] dist){
	for(int i=0;i<dist.length;i++){
		for(int j=0;j<dist.length;j++){
			if(i==j){
				dist[i][j]=0;
			}else{
			dist[i][j]=INF;
			}
		}
	}
	for(int i = 0;i<list.size();i++){
		Router r = list.get(i);
	if(r.isStart()){
		Lsp lsp=r.originatePacket();
		HashMap<Integer, Integer> map = r.getDirectLink();
		for(Map.Entry<Integer, Integer> m:map.entrySet()){
			int routerId = m.getKey();
			int linkCost = m.getValue();
			Router router = list.get(routerId);
			if(router.isStart()){
			dist[i][routerId] = linkCost;
			lsp.setSendId(routerId);
			lsp.setLinkCost(lsp.getLinkCost()+linkCost);
			router.receivePackage(lsp, list,dist);
			}
		}
	}
	}
	Iterator<Map.Entry<Integer, Router>> entries = list.entrySet().iterator();
	while(entries.hasNext()){
		Map.Entry<Integer, Router> en = entries.next();
		int routerId = en.getKey();
		Router router = en.getValue();
		router.setRt(new HashMap<String, RouterTable>());
		router.setDist(dist);;
		router.setNodes(dist.length);
		router.getRouterT(list);
		list.put(routerId, router);
		}
}
public static HashMap<Integer,Router> readFile(File file) throws  IOException{
	HashMap<Integer, Router> routerlist = new HashMap<Integer, Router>();
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	String str=null;
	int routerId=0;
	while((str=br.readLine())!=null){
		if(!str.startsWith(" ")){
			String[] line = str.trim().split(" ");
			Router router = new Router();
			routerId = Integer.parseInt(line[0].trim());
			String networkName = line[1].trim();
			router.setRouterId(routerId);
			router.setNetworkName(networkName);
			router.setNetworkCost(1);
			if(line.length == 3)
			router.setNetworkCost(Integer.parseInt(line[2].trim()));		
			router.setStart(true);
			routerlist.put(routerId, router);
		}else{
			String[] line = str.trim().split(" ");
			Router router = routerlist.get(routerId);
			if(router!=null){
			HashMap<Integer, Integer> map = router.getDirectLink();
			int toID = Integer.parseInt(line[0]);
			int linkCost = 1;
			if(line.length==2)
			linkCost = Integer.parseInt(line[1]);
			map.put(toID, linkCost);
			}
		}
	}
	setNodes(routerlist.size());
	return routerlist;
}
public static HashMap<Integer,Router> transform(HashMap<Integer,Router> list){
	int i=0;
	HashMap<Integer, Integer> t1 = new HashMap<Integer, Integer>();
	HashMap<Integer, Integer> t2 = new HashMap<Integer, Integer>();
	HashMap<Integer, Router> rlist = new HashMap<Integer, Router>(list);
	Iterator<Map.Entry<Integer, Router>> entries = list.entrySet().iterator();
	while(entries.hasNext()){
		Map.Entry<Integer, Router> en = entries.next();
		int routerId = en.getKey();
		Router router = en.getValue();
		t1.put(routerId, i);
		t2.put(i, routerId);
		router.setRouterId(i);
		rlist.remove(routerId);
		rlist.put(i, router);
		i++;
		}
	trans1=t1;
	trans2=t2;
	for(int j = 0;j<Nodes;j++){
		Router router = rlist.get(j);
		int routerId = router.getRouterId();
		HashMap<Integer,Integer> faketable = new HashMap<Integer, Integer>(router.getDirectLink());
		HashMap<Integer, Integer> table = router.getDirectLink();
		Iterator<Map.Entry<Integer, Integer>> ens = table.entrySet().iterator();
		while(ens.hasNext()){
			Map.Entry<Integer, Integer> e = ens.next();
			int toId = e.getKey();
			int cost = e.getValue();
			int k = trans1.get(toId);
			faketable.remove(toId);
			faketable.put(k, cost);
		}
		router.setDirectLink(faketable);
		rlist.put(j, router);
		}
	return rlist;
}
public static void main(String[] args) throws IOException {
	File file = new File("src/infile");
	HashMap<Integer, Router> routerlist = readFile(file);
	routerlist = transform(routerlist);
	int[][] dist = new int[routerlist.size()][routerlist.size()];           //matrix
	Scanner scan = new Scanner(System.in);
	boolean flag = true;
	do{
	System.out.println("Please input C to continue");
	System.out.println("Input Q to quit");
	System.out.println("Input P followed by routerID to print the routing table of a router(eg:P 1)");
	System.out.println("Input S followed by routerId to shut down a router(eg:S 2)");
	System.out.println("Input T followed by routerId to start a router(eg:T 3)");
	String str = scan.nextLine().toUpperCase();
	if(str.contains("C")){
		continued(routerlist,dist);
	}else if(str.contains("Q")){
		flag = false;
	}else if(str.contains("P")){
		String[] target = str.trim().split(" ");
		int routerId = Integer.parseInt(target[1].trim());
		if(trans1.containsKey(routerId)){
			routerId = trans1.get(routerId);
			Router router = routerlist.get(routerId);
			router.print();
		}else{
			System.out.println("The routerid doesn't exist");
		}
	}else if(str.contains("S")){
		String[] target = str.trim().split(" ");
		int routerId = Integer.parseInt(target[1].trim());
		if(trans1.containsKey(routerId)){
			routerId = trans1.get(routerId);
			Router router = routerlist.get(routerId);
			router.setStart(false);
			routerlist.put(routerId, router);
		}else{
			System.out.println("The routerid doesn't exist");
		}
	}else if(str.contains("T")){
		String[] target = str.trim().split(" ");
		int routerId = Integer.parseInt(target[1].trim());
		if(trans1.containsKey(routerId)){
			routerId = trans1.get(routerId);
			Router router = routerlist.get(routerId);
			router.setStart(true);
			routerlist.put(routerId, router);
		}else{
			System.out.println("The routerid doesn't exist");
		}
	}
	}while(flag);
	System.out.println("The program is closed");
}
}
