package neu.edu.coe.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import neu.edu.coe.dao.CategoryDao;
import neu.edu.coe.model.Category;


@Controller
@RequestMapping(value = "/category")
public class CategoryController {
	
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value ="/showcategory", method = RequestMethod.GET)
	public String showCategory(Model model) {
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "category";
	}
	//jump to category adding page
	@RequestMapping(value="/categoryAdding", method = RequestMethod.GET)
	public String createCategory(){
		return "categoryadding";
	}
	//add new category
	@RequestMapping(value="/categorycreated", method = RequestMethod.POST)
	public String categorycreated(HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException{
		String cname=request.getParameter("cname");
		Category newCategory = new Category(cname);
		categoryDao.insert(newCategory);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "category";
	}
	
	@RequestMapping(value="/{categoryId}/editing", method = RequestMethod.GET)
	public String updateCategory(@PathVariable int categoryId, Model model){
		Category category = categoryDao.findCategoryById(categoryId);
		model.addAttribute("category", category);
		return "categoryediting";
	}
	
	@RequestMapping(value="/categoryupdated", method = RequestMethod.POST)
	public String categoryupdated(@Valid @ModelAttribute("category") Category category, HttpServletRequest request, Model model) throws ClassNotFoundException, SQLException{
		System.out.println(category);
		categoryDao.Update(category);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "category";
	}
	
	
	@RequestMapping(value="/{categoryId}/delete", method = RequestMethod.GET)
	public String categorydeleted(@PathVariable int categoryId, HttpServletRequest request, Model model){
		Category deleteCategory = categoryDao.findCategoryById(categoryId);
		categoryDao.delete(deleteCategory);
		List<Category> categories = categoryDao.getCategorys();
		model.addAttribute("categories", categories);
		return "category";
	}
	
	
	public void getAllCategory(){
		List<Category> category = categoryDao.getCategorys();
		for(Category category1: category)
			System.out.println(category1);
	}

	
}
