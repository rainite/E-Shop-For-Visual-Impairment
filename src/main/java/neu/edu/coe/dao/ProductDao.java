package neu.edu.coe.dao;


import java.util.List;

import neu.edu.coe.model.Product;

public class ProductDao extends DAO {


	public ProductDao(){}

	
	public void insert(Product product) {
		System.out.println("product update called");
		begin();
		getSession()
			.saveOrUpdate(product);
		commit();
		close();
		System.out.println("Updated product Name = " + product.getPname());
	}

	
	public List<Product> findByDescription(String desc) {
		System.out.println("findByProductDescription called");
		begin();
		@SuppressWarnings("unchecked")
		List<Product> result =getSession()
				.createQuery("from Product where pdesc = :pdesc")
				.setParameter("pdesc", desc)
				.list();	
		commit();
		close();	
		return result;
	}

	
	
	public void update(Product product) {
		System.out.println("update called");
		insert(product);
	}

	
	public void delete(Product product) {
		System.out.println("deleteProduct called");
		begin();
		getSession()
			.createQuery("delete Product " +
					"where pid =:pid")
			.setParameter("pid", product.getPid())
			.executeUpdate();
		commit();
		close();
	}

	
	public List<Product> findByCategory(int cid) {
		System.out.println("findByCategory called");
		begin();
		@SuppressWarnings("unchecked")
		List<Product> result =getSession()
				.createQuery("from Product where cid = :cid")
				.setParameter("cid", cid)
				.list();	
		commit();
		close();	
		return result;
	}
	
//	public List<Integer> countByCategory(){
//		List<Integer> quantity = new ArrayList<Integer>();
//		String sql = "SELECT * FROM product JOIN category ON product.cid=category.cid WHERE category.cid = ?";
//		String sql2 = "SELECT * FROM category";
//		List<Category> categories = jdbc.query(sql2, new CategoryRowMapper());
//		for(Category category: categories){
//			List<Product> products = jdbc.query(sql, new Object[]{category.getCid()},new ProductMapper());
//			quantity.add(products.size());
//		}
//		return quantity;
//	}

	
	public List<Product> getProducts() {

		System.out.println("getProducts called");
		begin();
		@SuppressWarnings("unchecked")
		List<Product> result =getSession()
				.createQuery( "from Product" )
				.list();	
		commit();
		close();	
		return result;
	}


//	public void addImage(byte[] bytes, Integer pid) {
//		// TODO Auto-generated method stub
//		String sql = "UPDATE product set image = ? WHERE pid =?";
//		jdbc.update(sql,bytes,pid);
//	}

	
	public Product findById(int pid) {
		System.out.println("findByPid called");
		begin();
		Product result =(Product)getSession()
				.createQuery("from Product where pid = :pid")
				.setParameter("pid", pid)
				.uniqueResult();	
		commit();
		close();
		return result;
	}
	//get products by page
	
//	public List<Product> getProductsByPage(int startPos, int pageSize) {
//		// TODO Auto-generated method stub
//		List<Product> result = getProducts();	
//		return result;
//	}
//	
//	public List<Product> getProductsByPageAndKey(String key, int startPos, int pageSize) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT * FROM product WHERE pdesc like " + key + " LIMIT " + startPos + "," + pageSize;
//		System.out.println(sql);
//		List<Product> products = jdbc.query(sql, new ProductMapper());
//		return products;
//	}
//	
	//count products
	
	public long getProductsCount() {

		System.out.println("getProductsCount called");
		List<Product> products = getProducts();
		long counts = products.size();
		return counts;
	}
	
//	public long getProductsCountByKey(String key) {
//		// TODO Auto-generated method stub
//		String sql = "SELECT * FROM product WHERE pdesc LIKE " + key;
//		List<Product> products = jdbc.query(sql, new ProductMapper());
//		long counts = products.size();
//		return counts;
//	}

}
