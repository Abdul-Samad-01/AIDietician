package com.Aidietician.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Entities.User;



@Controller
public class HomeController {

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value="/", method=RequestMethod.GET)
    public String home() {
        
        return "home";
    }


    @RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Signup - AI Dietician");
		model.addAttribute("user", new User());
		return "signup";
	}
    
    
   

    @RequestMapping("/login")
	public String customLogin(Model model) {
		model.addAttribute("title", "Login - AI Dietician");
		return "login";
	}

    @PostMapping("do_register")
	public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
			HttpSession session) {
		try {
			if (!agreement) {
				System.out.println("You have not agreed terms and conditions.");
				throw new Exception("You have not agreed terms and conditions.");
			}
			if (result.hasErrors()) {
				System.out.println("ERROR - " + result.toString());
				model.addAttribute("user", user);
				return "signup";
			}
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			System.out.println("Agreement : " + agreement);
			System.out.println("User : " + user);
			User res = this.userRepository.save(user);
			model.addAttribute("user", new User());
			// session.setAttribute("message", new Messages("User registered Successfully...", "alert-success"));
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user", user);
			// session.setAttribute("message", new Messages("Something Went Wrong!!!" + e.getMessage(), "alert-danger"));
			return "signup";
		}
		return "signup";
	}

	
	
	
	
    
    
}
