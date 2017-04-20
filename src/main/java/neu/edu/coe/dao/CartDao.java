package neu.edu.coe.dao;

import java.util.List;

import neu.edu.coe.model.Cart;
import neu.edu.coe.model.User;

public class CartDao extends DAO{


	public CartDao(){}

	
	public void createCart(Cart cart) {
		System.out.println("createCart called");
		begin();
		getSession()
			.saveOrUpdate(cart);
		commit();
		close();
		System.out.println("Created updated");
		return;
	}

	
	public void deleteCart(Cart cart) {
		System.out.println("delete Cart called");
		begin();
		getSession()
			.createQuery("delete Cart " +
					"where pid =:pid")
			.setParameter("pid", cart.getProduct().getPid())
			.executeUpdate();
		commit();
		close();
	}

	
	public List<Cart> findByUser(User user) {

		System.out.println("findByUser called");
		begin();
		@SuppressWarnings("unchecked")
		List<Cart> result =getSession()
				.createQuery("from Cart where uid = :uid")
				.setParameter("uid", user.getUid())
				.list();	
		commit();
		close();	
		return result;
	}

	
	public double sum(User user) {
		System.out.println("Cart sum called");
		List<Cart> carts = findByUser(user);
		double sum = 0;
		for(Cart cart : carts){
			sum += cart.getProduct().getPrice() * cart.getQuantity();
		}
		return sum;
	}

	
	public void checkOut(User user) {
		System.out.println("checkOut called");
		List <Cart> carts = findByUser(user);
		for(Cart cart : carts){
			
			cart.setCheckout(true);
			begin();
			getSession()
				.saveOrUpdate(cart);
			commit();
			close();
			System.out.println("Created checkOut updated");	
		}
		return;
	}
	
	

}
