
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestProject
 */
@WebServlet("/TestProject")
public class TestProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		if(request.getParameter("keyword")== null) {
			String requestUri = request.getRequestURI();
			request.setAttribute("requestUri", requestUri);
			request.getRequestDispatcher("Search.jsp").forward(request, response);
			return;
		}
		String keyword = request.getParameter("keyword");
		
		
		WebTree[] trees = Main.getResult(keyword);
		ArrayList<String> reK = Main.rk;
		ArrayList<String> rkUrList = Main.rkurList;
		String[] reKey = new String[reK.size()];
		String[] rkUrl = new String[rkUrList.size()];
		
		for(int i=0;i<reK.size();i++) {
			reKey[i]=reK.get(i);
	//		rkUrl[i]=rkUrList.get(i);
			System.out.println(reKey[i]);
		}
		for(int i=0;i<rkUrList.size();i++) {
			//reKey[i]=reK.get(i);
			rkUrl[i]=rkUrList.get(i);
			System.out.println(rkUrl[i]);
		}
		
		
		
		String[][] s = new String[trees.length-1][2];
		
		int num = 0;
		for(int i = 1 ; i <trees.length; i++) {
			s[num][0] =  trees[i].root.webPage.name;
		    s[num][1] = trees[i].root.webPage.url;
		    System.out.println(trees[i].root.nodeScore+s[num][0] + "," + s[num][1]);
		    num++;
		}
		request.setAttribute("relativeKeywords",reKey);
		request.setAttribute("relativeUrl",rkUrl);
		request.setAttribute("query", s);
		request.getRequestDispatcher("googleitem.jsp")
		 .forward(request, response); 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
