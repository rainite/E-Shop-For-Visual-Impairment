package neu.edu.coe.controller;

import java.security.Principal;
import java.sql.SQLException;
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
import neu.edu.coe.dao.ProductDao;
import neu.edu.coe.dao.SmellDao;
import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.Page;
import neu.edu.coe.model.Product;
import neu.edu.coe.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	UserDao userDao;

	@Autowired
	ProductDao productDao;

	@Autowired
	SmellDao smellDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String userPage() {
		return "userPage";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Principal principal, Model model) {
		User currentUser = userDao.findByUserName(principal.getName());
//		if (currentUser == null) {
//			User admin = new User();
//			admin.setUsername(principal.getName());
//			model.addAttribute("currentUser", admin);
//			return "adminPage";
//		}
		model.addAttribute("currentUser", currentUser);
		List<Product> plist = productDao.getProducts();
		model.addAttribute("plist", plist);
//		this.showProductsByPage(request, model);
		return "home";
	}

	@RequestMapping(value = "/home/**", method = RequestMethod.GET)
	public String homeMyProductPage(HttpServletRequest request, Principal principal, Model model) {
		User currentUser = userDao.findByUserName(principal.getName());
		if (currentUser == null) {
			User admin = new User();
			admin.setUsername(principal.getName());
			model.addAttribute("currentUser", admin);
			return "home";
		}
		model.addAttribute("currentUser", currentUser);
		List<Product> plist = productDao.getProducts();
		model.addAttribute("plist", plist);
//		this.showProductsByPage(request, model);
		return "home";
	}

	@RequestMapping(value = "/register")
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/signin")
	public String signInRedirect() {
		return "signin";
	}

	@RequestMapping(value = "loggedout")
	public String logOutRedirect() {
		return "userPage";
	}

	@RequestMapping(value = "/registering", method = RequestMethod.POST)
	public String register(HttpServletRequest request) throws ClassNotFoundException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User newUser = new User(username, password);
		userDao.insert(newUser);
		return "signin";
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public String getAllUser(Model model) {
		System.out.println("Here");
		List<User> users = userDao.getUsers();
		model.addAttribute("users", users);
		for (User user : users) {
			System.out.println(user);
		}
		return "allUsers";
	}

//	public void showProductsByPage(HttpServletRequest request, Model model) {
//
//		String pageNow = request.getParameter("pageNow");
//
//		Page page = null;
//
//		List<Product> products = new ArrayList<Product>();
//
//		int totalCount = (int) productDao.getProductsCount();
//
//		if (pageNow != null) {
//			page = new Page(totalCount, Integer.parseInt(pageNow));
//			products = productDao.getProductsByPage(page.getStartPos(), page.getPageSize());
//			for (Product product : products) {
//				product.setSmell(smellDao.findByPid(product.getPid()));
//			}
//		} else {
//			page = new Page(totalCount, 1);
//			products = this.productDao.getProductsByPage(page.getStartPos(), page.getPageSize());
//			for (Product product : products) {
//				product.setSmell(smellDao.findByPid(product.getPid()));
//			}
//		}
//
//		model.addAttribute("plist", products);
//		model.addAttribute("page", page);
//	}
	
}
