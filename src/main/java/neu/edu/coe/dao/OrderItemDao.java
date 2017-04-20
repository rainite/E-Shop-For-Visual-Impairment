package neu.edu.coe.dao;

import java.util.List;

import neu.edu.coe.model.OrderItem;

public class OrderItemDao extends DAO{

	
	public OrderItemDao(){}

	
	public List<OrderItem> getOrderItemByOid(int oid) {
		System.out.println("getOrderItemByOid called");
		begin();
		@SuppressWarnings("unchecked")
		List<OrderItem> result =getSession()
				.createQuery("from OrderItem where oid = :oid")
				.setParameter("oid", oid)
				.list();	
		commit();
		close();	
		return result;
	}

}
