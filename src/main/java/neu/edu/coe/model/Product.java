package neu.edu.coe.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Table(name="product")
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid")
	private Integer pid;
	private String pname;
	private Double price;
	private String pdesc;
	private Integer is_hot;
	private Date pdate;
	@ManyToOne
	@JoinColumn(name = "cid")
	private Category category;
	@ManyToOne
	@JoinColumn(name = "smellid")
	private Smell smell;
	@Transient
	private CommonsMultipartFile imageFile;
	private String image;
//	@OneToOne(cascade = CascadeType.ALL)
//	@PrimaryKeyJoinColumn
//	private Feeling productFeeling;
//	
	
	public Product(){
		
	}
	

	public Product(String pname, Double price, String pdesc, Integer is_hot, Date pdate) {
		// TODO Auto-generated constructor stub
		this.pname = pname;
		this.price = price;
	//	this.image = imageFile;
		this.pdesc = pdesc;
		this.is_hot = is_hot;
		this.pdate = pdate;
	}
	
	public Product(Integer pid,String pname, Double price,  String pdesc, Integer is_hot, Date pdate, Category category){
		this.pid=pid;
		this.pname = pname;
		this.price = price;
//		this.image = imageFile;
		this.pdesc = pdesc;
		this.is_hot = is_hot;
		this.pdate = pdate;
		this.category = category;
	}

	public Product(Integer pid){
		this.pid=pid;
	}
	
	
	public Smell getSmell() {
		return smell;
	}


	public void setSmell(Smell smell) {
		this.smell = smell;
	}

//
//	public Feeling getProductFeeling() {
//		return productFeeling;
//	}
//
//	public void setProductFeeling(Feeling productFeeling) {
//		this.productFeeling = productFeeling;
//	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date date) {
		this.pdate = date;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public CommonsMultipartFile getImageFile() {
		return imageFile;
	}


	public void setImageFile(CommonsMultipartFile image) {
		this.imageFile = image;
	}
	
	


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + ", pdesc=" + pdesc + ", is_hot="
				+ is_hot + ", pdate=" + pdate + ", category=" + category + ", smell=" + smell + ", imageFile=" + imageFile
				+ "]";
	}

}
