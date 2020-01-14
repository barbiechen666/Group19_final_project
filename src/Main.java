
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class Main {
	public static ArrayList<String> rk = new ArrayList<String>();
	public static ArrayList<String> rkurList = new ArrayList<String>();
	public static WebTree[] getResult(String keyword) throws IOException {
		// TODO Auto-generated method stub
		
	
		GoogleQuery2 google = new GoogleQuery2(keyword);
		HashMap<String, String> query = google.query();
		rk = google.getRelativeK();
		rkurList = google.getRelativeUrl();
				
		String[][] s = new String[query.size()][2];
		int num = 0;
		int numofnull=0;
				
		for(Entry<String, String> entry : query.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			if(!value.contains("search")) {
				s[num][0] = key;
				s[num][1] = value;
				System.out.println(s[num][0] + "," + s[num][1]);
				num++;
			}else {
				 numofnull++;
			}
		}	
		ArrayList<Keyword> k = new ArrayList<Keyword>();
		
		k.add(new Keyword("限時",0.4));
		k.add(new Keyword("低消",0.3));
		k.add(new Keyword("插座",0.2));
		k.add(new Keyword("排名",0.1));
		WebTree[] trees = new WebTree[s.length-numofnull];
		for(int i=0;i<(s.length-numofnull);i++) {
			String u=s[i][1];
			if(u.contains("url?q=https://") ) {
				int from = u.indexOf("u");
				int to = u.indexOf("&sa");
				u=u.substring(from+6,to);
			}
			trees[i]=new WebTree(new WebPage("\r\n" +u,s[i][0]));
			findChildren find = new findChildren(u);
			ArrayList<String> cli = find.results();
			for(int j=0;j<cli.size();j++) {
				trees[i].root.addChild(new WebNode(new WebPage(cli.get(j),"")));
			}
			trees[i].eularPrintTree();
			trees[i].setPostOrderScore(k);
			
			
		}
		 
			
			
			
			for(int i=0;i<trees.length;i++) {
				for(int j=1; j<trees.length-1; j++) {
					if(trees[j].root.nodeScore < trees[j+1].root.nodeScore ) {
						trees[0]= trees[j+1];
						trees[j+1] = trees[j];
						trees[j]= trees[0];
					}
				}
			}
//			for(int i=1;i<=14; i++) {
//				System.out.println(trees[i].root.webPage.name+" "+ trees[i].root.nodeScore + trees[i].root.webPage.url);
//			}
			return trees;
		}
		
	

}
