package neu.edu.coe.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "uid")
	private Integer uid;
	private String username;
	private String password;
	private boolean enabled;
	private String authority;
	private String email;
	private String phone;
	
	@Lob
	private MultipartFile photo;
		
	private String photoFilename;
	
//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	private Set<Address> address;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	
	public User() {
		super();
	}


	public User(Integer uid, String username, String password, String email, String phone, boolean enabled, String authority) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
		this.phone = phone;
	}


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}



	public MultipartFile getPhoto() {
		return photo;
	}


	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}


	public String getPhotoFilename() {
		return photoFilename;
	}


	public void setPhotoFilename(String photoFilename) {
		this.photoFilename = photoFilename;
	}


//	public Set<Address> getAddress() {
//		return address;
//	}
//
//
//	public void setAddress(Set<Address> address) {
//		this.address = address;
//	}
//	
	



}
