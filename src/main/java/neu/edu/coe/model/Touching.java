package neu.edu.coe.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "touching")
@Entity
public class Touching {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "touchid")
	private Integer touchid;
	@ManyToOne
	@JoinColumn(name = "shapeid")
	private Shape shape;
	@ManyToOne
	@JoinColumn(name = "textureid")
	private Texture texture;
	
	
	public Integer getTid() {
		return touchid;
	}
	public void setTid(Integer tid) {
		this.touchid = tid;
	}
	public Shape getShape() {
		return shape;
	}
	public void setShape(Shape shape) {
		this.shape = shape;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	

}
