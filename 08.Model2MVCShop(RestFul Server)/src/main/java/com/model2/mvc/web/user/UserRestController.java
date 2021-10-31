package com.model2.mvc.web.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {

	/// Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	// setter Method 구현 않음
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	

	public UserRestController() {
		System.out.println(this.getClass());
	}

	@RequestMapping(value = "json/getUser/{userId}", method = RequestMethod.GET)
	public User getUser(@PathVariable String userId) throws Exception {

		System.out.println("/user/json/getUser : GET");

		// Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping(value = "json/login", method = RequestMethod.POST)
	public User login(@RequestBody User user, HttpSession session) throws Exception {

		System.out.println("/user/json/login : POST");
		// Business Logic
		System.out.println("::" + user);
		User dbUser = userService.getUser(user.getUserId());

		if (user.getPassword().equals(dbUser.getPassword())) {
			session.setAttribute("user", dbUser);
		}

		return dbUser;
	}

	
	  @RequestMapping(value="json/addUser", method=RequestMethod.POST)
	  public void addUser(@RequestBody User user) throws Exception{
		  
		  System.out.println("/user/json/addUser");
	 
		  userService.addUser(user);
		  
		  System.out.println("addUser 완료");
	  }
	  
	  @RequestMapping(value="json/updateUser/{userId}", method=RequestMethod.GET)
	  public void updateUser(@PathVariable String userId, HttpSession session) throws Exception{
		  
		  System.out.println("/user/json/updateUser : GET");
		 
		  userService.getUser(userId);
		 
	  }
	  
	  @RequestMapping(value="json/updateUser", method=RequestMethod.POST)
	  public void updateUser(@RequestBody User user, HttpSession session) throws Exception{
		  
		  System.out.println("/user/json/updateUser : POST");
		 
		  //User dbUser=userService.getUser(user.getUserId());
		  //user.getUserId()를 하면 기존에 DB에 있는 정보를 그대로 가져오기 때문에 세팅한 값으로 변경이 안됨
		  
		  userService.updateUser(user);
	  }
	  
	  
	  
	  @RequestMapping(value="json/listUser")
	  public Map<String, Object> getUserList(Search search) throws Exception{
		  
		  if(search.getCurrentPage()==0) {
			  search.setCurrentPage(1);
		  }
		  search.setPageSize(pageSize);
		  
		  Map<String,Object> map = userService.getUserList(search);
		  
		  Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		  System.out.println(resultPage);
		  
		  map.put("list", map.get("list"));
		  map.put("resultPage", resultPage);
		  map.put("search", search);
			
		  return map;
	  }
	  
	  @RequestMapping(value="json/checkDuplication", method=RequestMethod.POST)
	  public String checkDuplication(@RequestBody String userId) throws Exception{
		  
		  
	  }
	 

}