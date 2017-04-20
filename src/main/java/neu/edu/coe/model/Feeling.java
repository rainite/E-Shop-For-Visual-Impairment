package neu.edu.coe.model;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;


@Table(name = "feeling")
@Entity
public class Feeling {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "feelingid")
	private int feelingId;
	@ManyToOne
	@JoinColumn(name = "pid")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "smellid")
	private Smell smell;
	
	public Feeling(){
		
	}
	
	public int getFeelingId() {
		return feelingId;
	}
	public void setFeelingId(int feelingId) {
		this.feelingId = feelingId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Smell getSmell() {
		return smell;
	}
	public void setSmell(Smell smell) {
		this.smell = smell;
	}
	
	
}