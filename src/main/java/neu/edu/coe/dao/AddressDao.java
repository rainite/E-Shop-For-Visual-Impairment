package neu.edu.coe.dao;


import java.util.List;

import neu.edu.coe.model.Address;
import neu.edu.coe.model.User;


public class AddressDao extends DAO {

	public AddressDao() {
	}

	public void insert(Address address) {

		System.out.println("Address insert called");
		begin();
		getSession().saveOrUpdate(address);
		commit();
		close();
		System.out.println("Address updated");
		return;
	}


	public void delete(Address address) {
		System.out.println("delete address called");
		begin();
		getSession()
			.createQuery("delete Address" +
					"where addressid =:addressid")
			.setParameter("aiaddressidd", address.getAddressid())
			.executeUpdate();
		commit();
		close();
	}

	public Address findAddressById(int addressid) {
		System.out.println("findAddressById called");
		begin();
		Address result =(Address)getSession()
				.createQuery("from Address where addressid = :addressid")
				.setParameter("addressid", addressid)
				.uniqueResult();	
		commit();
		close();	
		return result;

	}
//
//	public List<Address> getAll() {
//		// TODO Auto-generated method stub
//		// jdbc.query("SELECT * FROM address", new AddressMapper());
//		return null;
//	}
//
	public List<Address> findByUser(User user) {
		System.out.println("findByUser called");
		begin();
		@SuppressWarnings("unchecked")
		List<Address> result =getSession()
				.createQuery("from Address where uid = :uid")
				.setParameter("uid", user.getUid())
				.list();	
		commit();
		close();	
		return result;
	}

}
