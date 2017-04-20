package neu.edu.coe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

@Table(name = "cart")
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartid")
	private int cartid;
	@ManyToOne
	@JoinColumn(name = "uid")
	private User user;
	private int quantity;
	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;
	boolean checkout;
	
	
	
	public Cart() {
		super();
	}


	

	public Cart(User user, int quantity, Product product) {
		super();
		this.user = user;
		this.quantity = quantity;
		this.product = product;
		this.checkout = false;
	}




	public int getCartid() {
		return cartid;
	}



	public void setCartid(int cartid) {
		this.cartid = cartid;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public boolean isCheckout() {
		return checkout;
	}



	public void setCheckout(boolean checkout) {
		this.checkout = checkout;
	}



	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", user=" + user + ", quantity=" + quantity + ", product=" + product
				+ ", checkout=" + checkout + "]";
	}
	
	
}