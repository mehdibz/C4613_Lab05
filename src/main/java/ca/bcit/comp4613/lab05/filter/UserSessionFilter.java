
package ca.bcit.comp4613.lab05.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserSessionFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		if (req.isUserInRole("user")) {
			res.sendRedirect("guest.jsp");
		} 
		
		else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig config) {
	}

	public void destroy() {
		
		System.out.println("KABOOM!");
	}
}
