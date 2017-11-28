package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import entities.Account;
import ultils.MD5Algorithm;

/**
 * Servlet implementation class AccountController
 */
@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		String url = "";
		if ("logout".equals(command)) {
			session.setAttribute("account", null);
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String command = request.getParameter("command");
		AccountDao accountDao = new AccountDao();
		Account account = null;
		String url = "";
		switch (command) {
		case "register":
			if (accountDao.isExist(request.getParameter("username"))) {
				request.setAttribute("error_register", "Tài khoản đã tồn tại. Xin vui lòng nhập tên tài khoản khác");
				url = "/register.jsp";
			} else {
				account = new Account();
				account.setUsername(request.getParameter("username"));
				account.setPassword(MD5Algorithm.encryption(request.getParameter("password")));
				account.setAddress(request.getParameter("address"));
				account.setPhone(request.getParameter("phone"));
				accountDao.insertAccount(account);
				account = accountDao.getAccount(request.getParameter("username"));
				session.setAttribute("account", account);
				url = "/index.jsp";
			}
			break;

		case "login":
			account = accountDao.checkAccount(request.getParameter("username"), request.getParameter("password"));
			if (account != null) {
				session.setAttribute("account", account);
				if (account.getRole() == 1) {
					response.sendRedirect("admin/index.jsp");
				} else {
					url = "/index.jsp";
				}
			} else {
				request.setAttribute("error", "Tên tài khoản hoặc mật khẩu không hợp lệ. Xin vui lòng nhập lại.");
				url = "/login.jsp";
			}
			break;

		default:
			break;
		}
		if (account.getRole() != 1) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		}
	}

}
