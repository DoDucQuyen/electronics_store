package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import dao.FileDao;
import dao.ProductRuleDao;
import entities.ProductRule;
import logic.AlgorithmLogic;
import logic.ProductRuleLogic;
import logic.SubCateRuleLogic;

/**
 * Servlet implementation class AlgorithmController
 */
@WebServlet("/AlgorithmController")
public class AlgorithmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double min_sup_product = Double.parseDouble(request.getParameter("min_sup"))/100;
		double min_conf_product = Double.parseDouble(request.getParameter("min_conf"))/100;
		
		double min_sup_sub_cate = Double.parseDouble(request.getParameter("min_sup_sub_cate"))/100;
		double min_conf_sub_cate = Double.parseDouble(request.getParameter("min_conf_sub_cate"))/100;
		
		FileDao fileDao = new FileDao();
		AlgorithmLogic algorithm = new AlgorithmLogic();
		
		fileDao.writeListCartToFile("Product", "SubCate");
		
		ProductRuleLogic productRule = new ProductRuleLogic();
		AssocRules productRules = algorithm.generateRulesByFPGrowth("Product", min_sup_product, min_conf_product);
		productRule.saveRules(productRules);
		
		SubCateRuleLogic subCateRule = new SubCateRuleLogic();
		AssocRules subCateRules = algorithm.generateRulesByFPGrowth("SubCate", min_sup_sub_cate, min_conf_sub_cate);
		subCateRule.saveRules(subCateRules);
		
		request.setAttribute("msg", "Chạy thuật toán thành công!");
	    response.sendRedirect("/ElectronicsStore/admin/algorithm.jsp");
	}

}
