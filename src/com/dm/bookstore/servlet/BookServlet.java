package com.dm.bookstore.servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dm.bookstore.domain.Account;
import com.dm.bookstore.domain.Book;
import com.dm.bookstore.domain.ShoppingCart;
import com.dm.bookstore.domain.ShoppingCartItem;
import com.dm.bookstore.domain.User;
import com.dm.bookstore.service.AccountService;
import com.dm.bookstore.service.BookService;
import com.dm.bookstore.service.UserService;
import com.dm.bookstore.web.BookStoreWebUtils;
import com.dm.bookstore.web.CirteriaBook;
import com.dm.bookstore.web.Page;
import com.google.gson.Gson;


public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String methodName = request.getParameter("method");
		try {
			Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class );
			method.setAccessible(true);
			method.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.clearShoppingCart(sc);
		request.getRequestDispatcher("WEB-INF/pages/empty.jsp").forward(request, response);
	}
	
	protected void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要删除书的id
		String idStr = request.getParameter("id");
		int id = -1;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		//获取购物车
		ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
		bookService.removeItemFromShoppingCart(id,sc);
		if(sc.isEmpty()){
			request.getRequestDispatcher("WEB-INF/pages/empty.jsp").forward(request, response);
			return;
		}
		request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
	}
	protected void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		request.getRequestDispatcher("/WEB-INF/pages/"+ page + ".jsp").forward(request, response);
		validateStoreNumber(request);
	}
	
//	protected void toCashPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
//	}
	
	protected void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		boolean flag = false;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		if(id > 0){
			//获取购物车
			ShoppingCart sc = BookStoreWebUtils.getShoppingCart(request);
			flag =bookService.addToCart(id,sc);
		}
		if(flag){
			getBooks(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/error-1.jsp");
		}
	}
	
	protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = -1;
		Book book = null;
		try {
			id = Integer.parseInt(idStr);
		} catch (NumberFormatException e) {}
		
		if(id > 0){
			book = bookService.getBook(id);
		}
		if(book == null){
			response.sendRedirect(request.getContextPath()+"/error-1.jsp");
			return;
		}
		
		request.setAttribute("book", book);
		request.getRequestDispatcher("WEB-INF/pages/book.jsp").forward(request, response);
		
	}
	
	protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageNoStr = request.getParameter("pageNo");
		String minPriceStr = request.getParameter("minPrice");
		String maxPriceStr = request.getParameter("maxPrice");
		
		int pageNo =1 ;
		int minPrice = 0;
		int maxPrice = Integer.MAX_VALUE;
		
		try {
			pageNo = Integer.parseInt(pageNoStr);
		} catch (NumberFormatException e) {}
		
		try {
			minPrice = Integer.parseInt(minPriceStr);
		} catch (NumberFormatException e) {}
		
		try {
			maxPrice = Integer.parseInt(maxPriceStr);
		} catch (NumberFormatException e) {}
		
		CirteriaBook cirteriaBook = new CirteriaBook(minPrice, maxPrice, pageNo);
		Page<Book> page = bookService.getPage(cirteriaBook);
		
		request.setAttribute("bookPage", page);
		request.getRequestDispatcher("WEB-INF/pages/getbooks.jsp").forward(request, response);
	}
	
	protected void updateItemQuantity(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String quantityStr = request.getParameter("quantity");
		
		int id = -1;
		int quantity = -1;
		
		try {
			id = Integer.parseInt(idStr);
			quantity = Integer.parseInt(quantityStr);
			
		} catch (Exception e) {}
		
		if(id > 0 && quantity > 0){
			
			ShoppingCart sc =BookStoreWebUtils.getShoppingCart(request);
			bookService.updateItemQuantity(sc,id,quantity);
			Map<String, Object> d = new HashMap<String, Object>();
			d.put("bookNumber", sc.getBookNumber());
			d.put("totalMoney", sc.getTotalMoney());
			
			Gson gson = new Gson();
			String data = gson.toJson(d);
			
			response.setContentType("text/javascript");
			response.getOutputStream().print(data);
		}
		
	}
	
	protected void cash(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("cardname");
		String accountID = request.getParameter("cardaccount");
		
		StringBuffer errors = validateFormFile(username, accountID);
		
		if(errors.toString().equals("")){
			errors = validateUser(username, accountID);
			
			if(errors.toString().equals("")){
				errors = validateStoreNumber(request);
			}
			
				if (errors.toString().equals("")) {
					errors = validateBalance(request,accountID);
				}
		}
		
		if(!errors.toString().equals("")){
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/pages/cash.jsp").forward(request, response);
			return;
		}
		
		BookService.cash(BookStoreWebUtils.getShoppingCart(request),username,accountID);
		response.sendRedirect(request.getContextPath()+"/success.jsp");
		
	}
	
	private AccountService accountService = new AccountService();
	public StringBuffer validateBalance(HttpServletRequest request,String accountID){
		ShoppingCart sc =BookStoreWebUtils.getShoppingCart(request);
		Account account = accountService.getAccount(Integer.parseInt(accountID));
		StringBuffer errors = new StringBuffer();
		if(sc.getTotalMoney() > account.getBalance()){
			errors.append("對不起，您的餘額不足，請聯繫管理員及時充值");
		}
		return errors;
	}
	
	public StringBuffer validateStoreNumber(HttpServletRequest request){
		ShoppingCart scs =BookStoreWebUtils.getShoppingCart(request);
		StringBuffer errors = new StringBuffer();
		for(ShoppingCartItem sc:scs.getItems()){
			int quantity = sc.getQuantity();
			int quantityStoreNumber = bookService.getBook(sc.getBook().getId()).getStoreNumber();
			if(quantity > quantityStoreNumber){
				errors.append(sc.getBook().getTitle()+"的库存不足");
			}
		}
		
		return errors;
	}
	
	public StringBuffer validateUser(String username,String accountID){
		UserService userService = new UserService();
		User user = userService.getUserByUserName(username);
		
		boolean flag = false;
		if(user != null){
			int accountID2  = user.getAccountId();
			if(accountID.trim().equals(""+accountID2)){
				flag = true;
			}
		}
		
		StringBuffer errors = new StringBuffer("");
		if(!flag){
			errors.append("用户名和账户名不匹配！！");
		}
		
		return errors;
	}
	
	/**
	 * 验证表单（简单验证）
	 * @param username 用户输入的用户名
	 * @param userID 用户输入的账号
	 * @return
	 */
	public StringBuffer validateFormFile(String username,String userID){
		StringBuffer errors = new StringBuffer("");
		if(username == null || username.trim().equals("")){
			errors.append("用户名不能为空<br>");
		}
		if(userID == null || userID.trim().equals("")){
			errors.append("账户不能为空");
		}
		return errors;
	}
	

}
