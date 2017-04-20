package neu.edu.coe.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.coe.dao.AddressDao;
import neu.edu.coe.dao.CartDao;
import neu.edu.coe.dao.ProductDao;
import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.Address;
import neu.edu.coe.model.Cart;
import neu.edu.coe.model.Product;
import neu.edu.coe.model.User;


@Controller
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	ProductDao productDao;
	
	@Autowired
	AddressDao addressDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String myCart(Principal principal, Model model){
		User user = userDao.findByUserName(principal.getName());
		List<Cart> cartItems = cartDao.findByUser(user);
		model.addAttribute("cartItems", cartItems);
		return "myCart";	
	}
	
	@RequestMapping(value="/{productId}/addCart", method = RequestMethod.POST)
	public String addToCart(@PathVariable int productId, Principal principal, HttpServletRequest request, Model model){
		Product product = productDao.findById(productId);
		User user = userDao.findByUserName(principal.getName());
		Cart cart = new Cart(user, Integer.parseInt(request.getParameter("quantity")),product);
		cartDao.createCart(cart);
		List<Cart> cartItems = cartDao.findByUser(user);
		model.addAttribute("cartItems", cartItems);
		return "myCart";	
	}
	@RequestMapping(value="/{productId}/delete", method = RequestMethod.GET)
	public String deleteCartItem(@PathVariable int productId, Principal principal, Model model){
		Product product = productDao.findById(productId);
		Cart cart = new Cart();
		cart.setProduct(product);
		cartDao.deleteCart(cart);
		User user = userDao.findByUserName(principal.getName());
		List<Cart> cartItems = cartDao.findByUser(user);
		model.addAttribute("cartItems", cartItems);	
		return "myCart";
	}
	@RequestMapping(value="/checkout", method = RequestMethod.GET)
	public String checkOut(Principal principal, Model model){
		User currentUser = userDao.findByUserName(principal.getName());
		List<Address> addresslist = addressDao.findByUser(currentUser);
		model.addAttribute("addresslist", addresslist);
		return "checkOut";	
	}
}
