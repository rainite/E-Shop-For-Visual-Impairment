package neu.edu.coe.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "orderItem")
@Entity
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "oiid")
	private int oiid;
	private Integer counts;
	
	private Double subtotal;
	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "oid")
	private Order order;
	
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public int getOiid() {
		return oiid;
	}
	public void setOiid(int oiid) {
		this.oiid = oiid;
	}
	@Override
	public String toString() {
		return "OrderItem [counts=" + counts + ", subtotal=" + subtotal + ", product=" + product
				+ ", order=" + order + "]";
	}
}
