import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Scanner;

public class findChildren {
	private String address;
	private ArrayList<String> urlList = new ArrayList<String>();
	public findChildren(String a) {
		this.address=a;
	}
	public ArrayList<String> results() throws IOException  {
		//URL url = new URL(address+URLEncoder.encode(keyword,"UTF-8"));
		URL url = new URL(address);
		URLConnection conn = url.openConnection();
		//conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		try {
		Scanner in = new Scanner(conn.getInputStream(),"UTF-8");
			
		while(in.hasNext()) {
			if(urlList.size()<2) {
				String line = in.next();
				if(line.contains("href=\"https") ) {
					int from = line.indexOf("=\"https");
					line=line.substring(from+2);
					int to = line.indexOf("\"");
					//System.out.println(line.substring(from+,to));
					line=line.substring(0,to);
					if(!line.contains(".jpg") && !line.contains(".dtd") && !line.contains(".php") && !line.contains("pic.") && !line.contains("css") && !line.contains("facebook") &&!line.contains("tag") && !line.contains("album") && !line.contains("pixnet") && !line.contains("javax") && !line.contains("ssl")&&!line.contains("klook")) {
						urlList.add(line);
				
					}
				}	
			}else {
				break;
			}
		}
		in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return urlList;
	}

}
