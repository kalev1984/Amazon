package ee.uptime.controller;

import ee.uptime.model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UptimeAmazon
 */
@WebServlet("/UptimeAmazon")
public class UptimeAmazon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UptimeAmazon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchKey = request.getParameter("amazonQuery");
		String page = request.getParameter("selectPage");
		int pageNumber = 1;
		pageNumber = Integer.parseInt(page);
		AmazonUrl amazonUrl = new AmazonUrl();
		List<Amazon> content = new ArrayList<Amazon>();
		content = amazonUrl.amazonUrl(searchKey, pageNumber);
		
		request.setAttribute("content_list", content);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Amazon-search.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
