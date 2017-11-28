package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDao;
import entities.Cart;
import entities.Item;
import entities.Product;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/CartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		String command = request.getParameter("command");
		int productId = Integer.parseInt(request.getParameter("product_id"));
		switch (command) {
		case "addToCart":
			ProductDao productDao = new ProductDao();
			Product product = productDao.getProduct(productId);
			if(cart.getCartItems().containsKey(productId)) {
				cart.addToCart(productId, new Item(product, cart.getCartItems().get(productId).getQuantity()));
			} else {
				cart.addToCart(productId, new Item(product, 1));
			}
			break;
			
		case "remove":
			cart.removeToCart(productId);
			break;

		default:
			break;
		}
		session.setAttribute("cart", cart);
		response.sendRedirect("/ElectronicsStore/index.jsp");
	}

}
