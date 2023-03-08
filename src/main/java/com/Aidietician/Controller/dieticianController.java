package com.Aidietician.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Aidietician.Dao.RequestRepository;
import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.userDetailRepository;
import com.Aidietician.Entities.User;
import com.Aidietician.Entities.dieticianReq;
import com.Aidietician.Entities.userRequest;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;





@Controller
@RequestMapping("/dietician")
public class dieticianController {

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private userDetailRepository detailRepository;

	@Autowired
	private UserRepository userRepository;

	@ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME : " + userName);
		// get the user using userName(Email)
		User user = userRepository.getUserByUserName(userName);
		int userId = user.getId();
		System.out.println("USER : " + user);
		model.addAttribute("user", user);
	}
	

	@RequestMapping("/index")
	public String dashboard(Model model) {
		model.addAttribute("title", "User Dashboard - AI Dietician");
		return "dietician/d_dashboard";
	}

	@RequestMapping("/request")
	public String showRequests(Model model) {

		List<dieticianReq> userIdlist = new ArrayList<>();
		userIdlist = requestRepository.findAll();
		
		List<userRequest> userdetails = new ArrayList<>();
		List<String> name = new ArrayList<>();
		for(int i =0;i<userIdlist.size();i++){
			List<userRequest> u = detailRepository.getDetailsByUserId(userIdlist.get(i).getReq());
			String str = userRepository.getUserNameByUserId(userIdlist.get(i).getReq());
			name.add(str);
			if(u.size()>=0){
			userdetails.add(u.get(u.size()-1));
			}
		}
		
		model.addAttribute("name", name);
		model.addAttribute("userdetails", userdetails);
		model.addAttribute("userIdlist", userIdlist);

		return "dietician/request";
	}

	@RequestMapping("/diet")
	public String giveDIet(Model model) {
		model.addAttribute("title", "User Dashboard - AI Dietician");
		return "dietician/diet";
	}
}
