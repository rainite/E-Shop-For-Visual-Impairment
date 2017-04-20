package neu.edu.coe.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import neu.edu.coe.dao.AddressDao;
import neu.edu.coe.dao.CartDao;
import neu.edu.coe.dao.OrderDao;
import neu.edu.coe.dao.OrderItemDao;
import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.Address;
import neu.edu.coe.model.Order;
import neu.edu.coe.model.OrderItem;
import neu.edu.coe.model.User;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
	
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	AddressDao addressDao;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderItemDao orderItemDao;

	
	@RequestMapping(method = RequestMethod.GET)
	public String order(Principal principal, Model model) {
		Order order = new Order();
		User currentUser = userDao.findByUserName(principal.getName());
		List<Order> orders = orderDao.getOrdersByUid(currentUser.getUid());
		model.addAttribute("order", order);
		model.addAttribute("orders", orders);
		model.addAttribute("user", currentUser);
		return "orderList";
	}
	@RequestMapping(value="/{addressId}/create", method = RequestMethod.GET)
	public String createOrder(@PathVariable int addressId, Principal principal, Model model){
		Address address = addressDao.findAddressById(addressId);
		User currentUser = userDao.findByUserName(principal.getName());
		double total = cartDao.sum(currentUser);
		Order order = new Order(address, currentUser,new java.sql.Date(new java.util.Date().getTime()), total);
		orderDao.insert(order);
		cartDao.checkOut(currentUser);
		List<Order> orders = orderDao.getOrdersByUid(currentUser.getUid());
		model.addAttribute("orders", orders);
		return "orderList";
	}
	@RequestMapping(value="/{orderId}/viewItems", method = RequestMethod.GET)
	public String viewItems(@PathVariable int orderId, Model model){
		List<OrderItem> orderItems =  orderItemDao.getOrderItemByOid(orderId);
		for(OrderItem oi: orderItems){
			System.out.println(oi);
		}
		model.addAttribute("orderItems", orderItems);
		return "orderItems";
	}
	
}
