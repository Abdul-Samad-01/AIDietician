package com.Aidietician.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.detailRepository;
import com.Aidietician.Dao.faqRepository;
import com.Aidietician.Entities.Faq;
import com.Aidietician.Entities.User;
import com.Aidietician.Entities.userReq;
import com.Aidietician.Service.process;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

	@Autowired
	private detailRepository detailRepository;

	@Autowired
	private process process;

	@Autowired
	private faqRepository faqRepository;

	

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
		System.out.println("bmiopened");
		return "normal/bmiauto";
	}

	@GetMapping(value="/addDetails")
	public String Details(Model model) {
		model.addAttribute("details", new userReq());
		
		return "normal/addDetails";
	}

	@PostMapping(value = "/addDetails")
    public String greetingSubmit(@ModelAttribute("details") userReq userReq, Model model,Principal p) {

	// adding to list 
	String name = p.getName();
	User user = userRepository.getUserByUserName(name);
	userReq.setUser(user);
	user.getList().add(userReq);

	detailRepository.save(userReq);
	process.processData(userReq);
	System.out.println(userReq.toString());
	model.addAttribute("message", process.toString());
	
    return "normal/currRes";
  }



	@RequestMapping(value="/faq")
	public String faq(Model model) {

		
		List<Faq> list = faqRepository.findAll();
		
		model.addAttribute("faq", list);
		
		
		
		
		
		

		return "normal/faq";
	}
}
