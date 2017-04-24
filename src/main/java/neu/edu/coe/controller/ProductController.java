package neu.edu.coe.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import neu.edu.coe.dao.CategoryDao;
import neu.edu.coe.dao.CommentDao;
import neu.edu.coe.dao.ProductDao;
import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.Category;
import neu.edu.coe.model.Comment;
import neu.edu.coe.model.Product;
import neu.edu.coe.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductDao productDao;
		
	@Autowired
	CommentDao commentDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	
	// show all the products
	@RequestMapping(method = RequestMethod.GET)
	public String list(HttpServletRequest request,  Model model) {

		List<Product> plist = productDao.getProducts();
		List<Category> categories = categoryDao.getCategorys();
//		List<Integer> quantity = productDao.countByCategory();
//		model.addAttribute("numbers", quantity);
//		System.out.println(quantity);
		model.addAttribute("plist", plist);
		model.addAttribute("categories", categories);
		return "products";
	}

	// jump to add product page
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(Model model) {
		Product product = new Product();
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return "addProduct";
	}

	// admin function: add product
	@RequestMapping(value = "/adding", method = RequestMethod.POST)
	public String addingProduct(@Valid @ModelAttribute("product") Product product, HttpServletRequest request,
			Model model) {
		System.out.println(product.getCategory().getCid());
		System.out.println("product is" + product);
		productDao.insert(product);
		List<Product> plist = productDao.getProducts();
		model.addAttribute("plist", plist);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "products";
	}

	// edit page and updated
	@RequestMapping(value = "/{productId}/edit", method = RequestMethod.GET)
	public String update(@PathVariable int productId, HttpServletRequest request, Model model) {
		
		System.out.println(productId);
		Product product = productDao.findById(productId);
		model.addAttribute("product", product);
		
		return "editProduct";
		
	}

	@RequestMapping(value = "/{productId}/edit", method = RequestMethod.POST)
	public String productUpdated(@PathVariable int productId, @Valid @ModelAttribute("product") Product product, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
		System.out.println("*** SUBMITTED");
		Product oldProduct = productDao.findById(productId);
		String filename = "";
		ServletContext servletContext = request.getSession().getServletContext();
		int begin = oldProduct.getImage().lastIndexOf("/");
		System.out.println("begin = " + begin);
		if(begin != -1){
			filename = oldProduct.getImage().substring(begin+1,oldProduct.getImage().length());
			System.out.println("Old Filename = " + filename);
			//getName()
		}//the new one isn't the old image
		
		String uploadFilename = product.getImageFile().getOriginalFilename();
		System.out.println("New Filename = " + uploadFilename);
		if(filename != uploadFilename && !"".equals(uploadFilename)){
			//get path
			String path = servletContext.getRealPath("/resources/1");
			
			System.out.println("Write Real Path = " + path);
			try {
				System.out.println("Start writting file");
				FileUtils.writeByteArrayToFile(new File(path,uploadFilename), product.getImageFile().getBytes());
				System.out.println("Writting done! ");
			} catch (IOException e) {
				e.printStackTrace();
			}
			//reset image path
			product.setImage("/resources/1/"+uploadFilename);		
			path = request.getSession().getServletContext().getRealPath("/resources/1"+oldProduct.getImage());
			System.out.println("Old file path: " + path);
			File file = new File(path);
			System.out.println("Start deleting old file");
			file.delete();
		}
		
		List<Product> plist = productDao.getProducts();
		model.addAttribute("plist", plist);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		productDao.update(product);
		System.out.println("*** DONE");
		return "products";
	}

	// delete product
	@RequestMapping(value = "/{productId}/delete", method = RequestMethod.GET)
	public String deleting(@PathVariable int productId, HttpServletRequest request, Model model) {
		Product product = productDao.findById(productId);
		productDao.delete(product);
		List<Product> plist = productDao.getProducts();
		model.addAttribute("plist", plist);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "products";
	}

	// find product by category ID
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.GET)
	public String fingByCategoryId(@PathVariable int categoryId, Model model) {
		List<Product> plist = productDao.findByCategory(categoryId);
		model.addAttribute("plist", plist);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "products";
	}

	// view Product
	@RequestMapping(value = "/{productId}/viewProduct", method = RequestMethod.GET)
	public String viewProduct(@PathVariable int productId, HttpServletRequest request, Model model) {
		System.out.println(productId);
		Product product = productDao.findById(productId);
		model.addAttribute("product", product);
		List<Comment> commentlist = commentDao.findByProduct(product);
		model.addAttribute("commentlist", commentlist);
		return "viewProduct";
	}

	// @RequestMapping(value = "/findByCid", method = RequestMethod.GET)
	// public ModelAndView findByCategoryCid(HttpServletRequest request) {
	// Integer cid = Integer.valueOf(request.getParameter("cid"));
	// List<Product> plist = productDao.findByCategoryCid(cid);
	// return new ModelAndView("products", "plist", plist);
	// }
	//
	// @RequestMapping(value = "/findByCsid", method = RequestMethod.GET)
	// public ModelAndView findByCategorySecondCsid(HttpServletRequest request)
	// {
	// Integer csid = Integer.valueOf(request.getParameter("csid"));
	// List<Product> plist = productDao.findByCategorySecondCsid(csid);
	// return new ModelAndView("products", "plist", plist);
	// }

	@RequestMapping(value = "showlist.htm", method = RequestMethod.GET)
	public ModelAndView LogedInPage(HttpServletRequest request) {

		List<Product> plist = productDao.getProducts();
		return new ModelAndView("products", "plist", plist);
	}

	// for comments
	@RequestMapping(value = "/{productId}/addComment", method = RequestMethod.POST)
	public String addComment(@PathVariable int productId, HttpServletRequest request, Model model) {
		Comment comment = new Comment();
		// List <Product> products= productDao.getProducts();
		Product products = productDao.findById(productId);
		model.addAttribute("product", products);
		model.addAttribute("comment", comment);
		return "addComment";
	}

	@RequestMapping(value = "/{productId}/commentadded", method = RequestMethod.POST)
	public String commentsadded(@PathVariable int productId, Principal principal, HttpServletRequest request,
			Model model) {
		Product product = productDao.findById(productId);
		User user = userDao.findByUserName(principal.getName());
		Comment comment = new Comment(user, product, Integer.parseInt(request.getParameter("pLevel")),
				Integer.parseInt(request.getParameter("sLevel")));
		commentDao.insert(comment);
		List<Comment> commentlist = commentDao.findByProduct(product);
		model.addAttribute("commentlist", commentlist);
		model.addAttribute("product", product);
		return "viewProduct";
	}
	
//image servlet
	
//	@RequestMapping(value = "/showImage", method = RequestMethod.GET ,produces = MediaType.IMAGE_JPEG_VALUE)
//	public byte[] testphoto(@PathVariable int imageName, HttpServletRequest request) throws IOException {
//		System.out.println("showImage called");
//		ServletContext servletContext = request.getSession().getServletContext();
//		String path = servletContext.getRealPath("/resources/1" + imageName);
//		System.out.println("photoServlet Path" + path);
//	    InputStream in = servletContext.getResourceAsStream(path);
//	    return IOUtils.toByteArray(in);
//	}
//	
	
	
}
