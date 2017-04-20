package neu.edu.coe.dao;



import java.util.List;

import neu.edu.coe.model.Comment;
import neu.edu.coe.model.Product;

public class CommentDao extends DAO {


	public CommentDao(){}

	
	public void insert(Comment comment) {
		// TODO Auto-generated method stub
//		String sql = "INSERT INTO comments " + "(product_pid,user_uid,pLevel,sLevel) VALUES (?, ?, ?, ?)";
//	    jdbc.update(sql, comment.getProduct().getPid(), comment.getUser().getUid(), comment.getpLevel(), comment.getsLevel());
//	    return;
		
		System.out.println("Comment insert called");
		begin();
		getSession()
			.save(comment);
		System.out.println("Created comment = " + comment.getComid());
		commit();
		close();
	}


	
	public List<Comment> getCommnets() {
		System.out.println("getComments called");
		begin();
		@SuppressWarnings("unchecked")
		List<Comment> result =getSession()
				.createQuery( "from Comment" )
				.list();	
		commit();
		close();	
		return result;
	}


	
	public List<Comment> findByProduct(Product product) {
		System.out.println("findByPid called");
		begin();
		@SuppressWarnings("unchecked")
		List<Comment> result =getSession()
				.createQuery("from Comment where pid = :pid")
				.setParameter("pid", product.getPid())
				.list();	
		commit();
		close();
		return result;
	}

}
