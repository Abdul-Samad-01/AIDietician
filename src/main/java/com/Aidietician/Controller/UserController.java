package com.Aidietician.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Entities.User;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    //add common data
    @ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME : " + userName);
		// get the user using userName(Email)
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER : " + user);
		model.addAttribute("user", user);
	}
    
    // user dashboard
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "User Dashboard - AI Dietician");
		return "normal/user_dashboard";
	}

	@RequestMapping(value="/bmi")
	public String bmi() {
		return "normal/bmiauto";
	}
}
