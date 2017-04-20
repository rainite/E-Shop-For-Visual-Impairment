package neu.edu.coe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import neu.edu.coe.dao.UserDao;
import neu.edu.coe.model.User;


@Controller
@RequestMapping(value = "/userInfo")
public class UserController {
	
	@Autowired
	UserDao userDao;
		
	
	@RequestMapping(method = RequestMethod.GET)
	public String viewInfo(@ModelAttribute("user") User user, Principal principal, Model model){
		User currentUser = userDao.findByUserName(principal.getName());
		model.addAttribute("user", currentUser);
		return "userInfo";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateUserInfo(@Valid @ModelAttribute("user") User user, Principal principal, HttpServletRequest request,Model model){
//		User currentUser = userDao.findByUserName(principal.getName());
//		currentUser.setEmail(request.getParameter("email"));
//		currentUser.setPhone(request.getParameter("phone"));
//		currentUser.setUsername(request.getParameter("username"));
		System.out.println(user);
		userDao.update(user);
		model.addAttribute("user",user);
		return "userInfo";
	}
	
	@RequestMapping(value = "/{userId}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int userId, Model model){
		userDao.deleteUser(userId);
		List<User> users = userDao.getUsers();
		model.addAttribute("users", users);
		return "allUsers";
	}
	//show user photo
//	@RequestMapping(value="/{userId}/photo", method=RequestMethod.GET)
//	public @ResponseBody String viewPhoto(@PathVariable int userId, HttpServletResponse response) throws IOException {
//		User user = userDao.findByUserID(userId);
//		byte[] photoBytes = user.getPhotoBytes();
//		if (photoBytes != null) {
//			int photoLength = photoBytes.length;
//			try (ServletOutputStream sos = response.getOutputStream()) {
//				
//				response.setContentType(user.getPhotoContentType());
//				response.setContentLength(photoLength);
//				response.setHeader("Content-Disposition", "inline; filename=\"" + user.getPhotoFilename() + "\"");
//				
//				sos.write(photoBytes);
//				sos.flush();
//			}
//		}
//		return "";
//	}

}
