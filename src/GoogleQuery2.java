import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStream;

import java.io.InputStreamReader;

import java.net.URL;

import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;



import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;

import org.jsoup.nodes.Element;

import org.jsoup.select.Elements;



public class GoogleQuery2 

{

	public String searchKeyword;

	public String url;

	public String content;
	private ArrayList<String> web=new ArrayList<String>();
	private ArrayList<String> webName=new ArrayList<String>();
	private ArrayList<String> rKeyword = new ArrayList<String>();
	private ArrayList<String> rKurl = new ArrayList<String>();
	public GoogleQuery2(String searchKeyword)

	{
		if(searchKeyword.contains(" ")) {
			
			searchKeyword=searchKeyword.replaceAll(" ","+");
			//System.out.println(searchKeyword);
		}
		
		this.searchKeyword = "©@°ØÆU+"+searchKeyword;

		this.url = "http://www.google.com.tw/search?q="+this.searchKeyword+"&oe=utf8&num=10";

	}

	

	private String fetchContent() throws IOException

	{
		String retVal = "";

		URL u = new URL(url);

		URLConnection conn = u.openConnection();

		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");

		InputStream in = conn.getInputStream();

		InputStreamReader inReader = new InputStreamReader(in,"utf-8");

		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line=bufReader.readLine())!=null)
		{
			retVal += line;

		}
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException{

		if(content==null){
			content= fetchContent();
		}

		HashMap<String, String> retVal = new HashMap<String, String>();
		Document doc = Jsoup.parse(content);
		System.out.println(doc.text());
		Elements lis = doc.select("div");
		Elements lis_rk = lis.select(".X7NTVe");
		lis = lis.select(".ZINbbc");
		
		System.out.println(lis.size());
		
		for(Element li : lis){
			try {
				String title = li.select(".BNeawe").get(0).text();
				String citeUrl = li.select("a").get(0).attr("href");
				
				if(citeUrl.contains("/url?"))
					citeUrl = "http://google.com"+citeUrl;
				retVal.put(title, citeUrl);
				System.out.println(retVal.get(title));		

			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}		
		}
		for(Element rk:lis_rk) {
			String word = rk.select(".BNeawe").get(0).text();
			rKeyword.add(word);
			String citeUrl2 = rk.select("a").get(0).attr("href");
			try {
				citeUrl2 = "http://google.com/search?"+citeUrl2;
				rKurl.add(citeUrl2);
			} 
			catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}	
		}
		
		return retVal;
	}
	
	public ArrayList<String> getRelativeK(){
				
		return rKeyword;
	}
	
	public ArrayList<String> getRelativeUrl(){
		
		return rKurl;
	}
	
	public ArrayList<String> getUrl() throws IOException

	 {

	  if(content==null)

	  {

	   content= fetchContent();

	  }

	  HashMap<String, String> retVal = new HashMap<String, String>();
	  //HashMap retVal = new HashMap();
	  Document doc = Jsoup.parse(content);
	  System.out.println(doc.text());
	  Elements lis = doc.select("div");
	  lis = lis.select(".ZINbbc");
	  System.out.println(lis.size());
	  
	  
	  for(Element li : lis)
	  {
	   try 

	   {
	    String title = li.select(".BNeawe").get(0).text();
	    String citeUrl = li.select("a").get(0).attr("href");
	    if(citeUrl.contains("/url?"))
	     citeUrl = "http://google.com"+citeUrl;
	    retVal.put(title, citeUrl);
	    web.add(retVal.get(title));
	    //System.out.println(retVal.get(title));

	   } catch (IndexOutOfBoundsException e) {

	    e.printStackTrace();

	   }
	   

	  }  

	  return web;

	 }
	
	
	public ArrayList<String> getName() throws IOException

	 {

	  if(content==null)

	  {

	   content= fetchContent();

	  }

	  HashMap<String, String> retVal = new HashMap<String, String>();
	  //HashMap retVal = new HashMap();
	  Document doc = Jsoup.parse(content);
	  System.out.println(doc.text());
	  Elements lis = doc.select("div");
	  lis = lis.select(".ZINbbc");
	  System.out.println(lis.size());
	  
	  
	  for(Element li : lis)
	  {
	   try 

	   {
	    String title = li.select(".BNeawe").get(0).text();
	    String citeUrl = li.select("a").get(0).attr("href");
	    if(citeUrl.contains("/url?"))
	     citeUrl = "http://google.com"+citeUrl;


	    retVal.put(title, citeUrl);
	    webName.add(retVal.get(citeUrl));
	    //System.out.println(retVal.get(title));
	    

	    

	   } catch (IndexOutOfBoundsException e) {

	    e.printStackTrace();

	   }

	   

	  }
	  

	  

	  return webName;

	 }
	

	

}