package neu.edu.coe.dao;


import java.util.List;

import neu.edu.coe.model.Smell;

public class SmellDao extends DAO {

	public SmellDao(){}

	
	public Smell findByPid(int pid) {
		System.out.println("findByPid called");
		begin();
		Smell result =(Smell)getSession()
				.createQuery("from Smell where pid = :pid")
				.setParameter("pid", pid)
				.uniqueResult();	
		commit();
		close();	
		return result;
		
		
	}
	
	
	public List<Smell> getSmell(){
		return null;
	}

}