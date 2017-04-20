package neu.edu.coe.model;



import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Table(name="category")
@Entity
public class Category{
	
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Column(name = "cid")
	private Integer cid;
	private String cname;
	// the set to put second category
//	@ManyToOne
//	@MapsId
//	private Set<CategorySecond> categorySeconds = new HashSet<CategorySecond>();
	
	public Category(String cname) {
		// TODO Auto-generated constructor stub
		this.cname=cname;
	}
	
	public Category(String cname, Integer cid){
		this.cname=cname;
		this.cid= cid;
	}
	
	public Category(){
		super();
	}
	
	public Category(Integer cid) {
		// TODO Auto-generated constructor stub
		this.cid=cid;
	}

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
//	public Set<CategorySecond> getCategorySeconds() {
//		return categorySeconds;
//	}
//	public void setCategorySeconds(Set<CategorySecond> categorySeconds) {
//		this.categorySeconds = categorySeconds;
//	}
//	
//	
}
