package com.dm.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.bookstore.db.JDBCUtils;
import com.dm.bookstore.web.ConnectionContext;

/**
 * Servlet Filter implementation class TranactionFilter
 */
public class TranactionFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public TranactionFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Connection connection = null;

		try {
			connection = JDBCUtils.getConnection();
			ConnectionContext.getInstance().bind(connection);
			//开启事物
			connection.setAutoCommit(false);
			
			//
			chain.doFilter(request, response);
			//提交事物
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			HttpServletResponse res = (HttpServletResponse) response;
			HttpServletRequest req =(HttpServletRequest) request;
			res.sendRedirect(req.getContextPath()+"/error-1.jsp");
			
		}finally{
			ConnectionContext.getInstance().remove();
			JDBCUtils.release(connection);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
