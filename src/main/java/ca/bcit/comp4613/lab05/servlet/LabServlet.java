package ca.bcit.comp4613.lab05.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import a00123456.lab.data.DBBean;


public class LabServlet extends HttpServlet {

		private static final long serialVersionUID = 1L;
		
		private DBBean db;
		
		//@Resource(mappedName = "java:jboss/datasources/com/microsoft/sqlserver")
		@Resource(mappedName = "java:/BCIT_MSSQLDS")
		private DataSource dataSource;

		public void init(ServletConfig config) throws ServletException {
			super.init(config);
			db = new DBBean(dataSource );
		}

		@Override
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			System.out.println("QUERY STRING: " + request.getParameter("queryInput"));
			db.setQueryString(request.getParameter("queryInput"));
			try {
				db.generateResultSet();
				request.setAttribute("columns", db.getColumnNames());
				request.setAttribute("results", db.getResults());
			} catch (SQLException e) {
				throw new ServletException(e);
			}
			HttpSession session = request.getSession();
			session.setAttribute("db", db);
			

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/header.jsp");
			dispatcher.include(request, response);
			dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/output.jsp");
			dispatcher.include(request, response);
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/footer.jsp"); 
			dispatcher.include(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			doGet(request, response);
		}
		
		@Override
		public void destroy() {
			db.cleanUp();
		}
}
