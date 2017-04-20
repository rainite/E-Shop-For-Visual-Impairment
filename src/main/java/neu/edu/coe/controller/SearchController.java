package neu.edu.coe.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import neu.edu.coe.dao.AddressDao;
import neu.edu.coe.dao.CartDao;
import neu.edu.coe.dao.CategoryDao;
import neu.edu.coe.dao.CommentDao;
import neu.edu.coe.dao.OrderDao;
import neu.edu.coe.dao.OrderItemDao;
import neu.edu.coe.dao.ProductDao;
import neu.edu.coe.dao.SmellDao;
import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.Page;
import neu.edu.coe.model.Product;
import neu.edu.coe.model.User;
import neu.edu.coe.service.CategoryService;

@Controller
public class SearchController {

	static String searchKey = "";
	static String append = " AND pdesc like ";

	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	SmellDao smellDao;
	
	
	// @RequestMapping(value = "/search", method = RequestMethod.POST)
	// public ModelAndView searchFunction(HttpServletRequest request){
	// String button = request.getParameter("button");
	// System.out.println(button);
	// if ("Search".equals(button)) {
	// System.out.println("***** search Cntl");
	// searchKey = "";
	// String desc = request.getParameter("desc");
	// String temp = "'%" + desc + "%'";
	// searchKey += temp;
	// System.out.println(searchKey);
	// List<Product> plist = productDao.findByDescription(temp);
	// return new ModelAndView("search","plist",plist);
	// }
	//
	// else{
	// System.out.println("***** searchagain Cntl");
	// String desc = request.getParameter("desc");
	// String temp = "'%" + desc + "%'";
	// searchKey = searchKey + append + temp;
	// System.out.println(searchKey);
	// List<Product> plist = productDao.findByDescription(searchKey);
	// return new ModelAndView("search","plist",plist);
	//
	// }
	//
	// }

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Principal principal, HttpServletRequest request, Model model) {
		searchKey = "";
		User currentUser = userDao.findByUserName(principal.getName());
		model.addAttribute("currentUser", currentUser);
//		this.showProductsByPage(request, model);
		return "search";
	}

	@RequestMapping(value = "/search/**", method = RequestMethod.GET)
	public String searchProductPage(Principal principal, HttpServletRequest request, Model model) {
		User currentUser = userDao.findByUserName(principal.getName());
		model.addAttribute("currentUser", currentUser);
//		this.showProductsByPage(request, model);
		return "search";
	}

//	public void showProductsByPage(HttpServletRequest request, Model model) {
//
//		String button = request.getParameter("button");
//
//		System.out.println("button is" + button);
//
//		String key = request.getParameter("desc");
//
//		String pageNow = request.getParameter("pageNow");
//
//		Page page = null;
//
//		List<Product> products = new ArrayList<Product>();
//
//		// List<Product> plist = productDao.getProducts();
//
//		int totalCount = (int) productDao.getProductsCount();
//		;
//
//		if (button == null) {
//			totalCount = (int) productDao.getProductsCountByKey(searchKey);
//		} else if (button.equals("Search")) {
//			if (searchKey == "") {
//				searchKey = "'%" + key + "%'";
//			} else {
//				searchKey = searchKey + append + "'%" + key + "%'";
//			}
//			totalCount = (int) productDao.getProductsCountByKey(searchKey);
//		} else if (button.equals("ClearSearch")) {
//			searchKey = "";
//		}
//
//		System.out.println("Total Count is " + totalCount);
//		if (pageNow != null) {
//			page = new Page(totalCount, Integer.parseInt(pageNow));
//			if (searchKey.equals("") || searchKey == null) {
//				products = this.productDao.getProductsByPage(page.getStartPos(), page.getPageSize());
//			} else {
//				products = this.productDao.getProductsByPageAndKey(searchKey, page.getStartPos(),
//						page.getPageSize());
//			}
//			for (Product product : products) {
//				product.setSmell(smellDao.findByPid(product.getPid()));
//			}
//		} else {
//			page = new Page(totalCount, 1);
//			if (searchKey.equals("") || searchKey == null) {
//				products = this.productDao.getProductsByPage(page.getStartPos(), page.getPageSize());
//			} else {
//				products = this.productDao.getProductsByPageAndKey(searchKey, page.getStartPos(),
//						page.getPageSize());
//			}
//			for (Product product : products) {
//				product.setSmell(smellDao.findByPid(product.getPid()));
//			}
//		}
//
//		model.addAttribute("plist", products);
//		model.addAttribute("page", page);
//	}

}
