package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BillDao;
import entities.Account;
import entities.Bill;
import entities.Cart;

/**
 * Servlet implementation class CheckoutController
 */
@WebServlet("/CheckoutController")
public class CheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BillDao billDao = new BillDao();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Account account = (Account) session.getAttribute("account");
		Bill bill = new Bill();
		bill.setCart(cart);
		bill.setAddress(address);
		bill.setPhone(phone);
		bill.setAccountId(account.getId());
		billDao.insertBill(bill);
		cart = new Cart();
		session.setAttribute("cart", cart);
		response.sendRedirect("/ElectronicsStore/index.jsp");
	}

}
