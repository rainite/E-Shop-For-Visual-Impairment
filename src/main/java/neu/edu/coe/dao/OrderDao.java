package neu.edu.coe.dao;

import java.util.List;

import neu.edu.coe.model.Order;


public class OrderDao extends DAO{

	
	public OrderDao(){}
	
	public void insert(Order order) {
//		String sql = "INSERT INTO orders" + "(addressid, uid, total, ordertime) VALUES ( ?, ?, ?, ?)";
//		jdbc.update(sql,order.getAddress().getAddressid(), order.getUser().getUid(), order.getTotal(), order.getOrdertime());
		
		System.out.println("Order insert called");
		begin();
		getSession()
			.save(order);
		System.out.println("Created product Name = " + order.getUser());
		commit();
		close();
	}

	public List<Order> getOrdersByUid(int uid) {
		System.out.println("getOrdersByUid called");
		begin();
		@SuppressWarnings("unchecked")
		List<Order> result =getSession()
				.createQuery("from Order where uid = :uid")
				.setParameter("uid", uid)
				.list();	
		commit();
		close();
		return result;
	}

}
