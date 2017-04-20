package neu.edu.coe.dao;

import java.util.List;


import neu.edu.coe.model.Category;


public class CategoryDao extends DAO {

	public CategoryDao(){}

	
	public void insert(Category category) {
		System.out.println("Category insert called");
		begin();
		getSession()
			.save(category);
		commit();
		close();
		System.out.println("Created category Name = " + category.getCname());
	}

	
	public Category findCategoryByName(String cname) {
		System.out.println("findCategoryByName called");
		begin();
		Category result =(Category)getSession()
				.createQuery("from Category where cname = :cname")
				.setParameter("cname", cname)
				.uniqueResult();	
		commit();
		close();	
		return result;
	}
	
	public void Update(Category category) {
		System.out.println("Category Update called");
		begin();
		getSession()
			.saveOrUpdate(category);
		commit();
		close();
		System.out.println("Created updated");
		return;
	}

	
	public void delete(Category category) {
		System.out.println("delete Category called");
		begin();
		getSession()
			.createQuery("delete Category " +
					"where cid =:cid")
			.setParameter("cid", category.getCid())
			.executeUpdate();
		commit();
		close();
	}

	
	public List<Category> getCategorys() {
		System.out.println("getCategorys called");
		begin();
		@SuppressWarnings("unchecked")
		List<Category> result =getSession()
				.createQuery( "from Category" )
				.list();	
		commit();
		close();	
		return result;
	}

	
	public Category findCategoryById(int cid) {
		System.out.println("findCategoryById called");
		begin();
		Category result =(Category)getSession()
				.createQuery("from Category where cid = :cid")
				.setParameter("cid", cid)
				.uniqueResult();	
		commit();
		close();	
		return result;
	}
	

	

}

