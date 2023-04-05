package com.Aidietician.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Aidietician.Dao.RequestRepository;
import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.userDetailRepository;
import com.Aidietician.Entities.User;
import com.Aidietician.Entities.dieticianReq;
import com.Aidietician.Entities.userRequest;
import com.Aidietician.Entities.dieticianDiet;
import com.Aidietician.Dao.dieticianDietRepo;

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

	@Autowired
	private dieticianDietRepo dieticianDietRepo;

	int userId;

	@ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME : " + userName);
		// get the user using userName(Email)
		User user = userRepository.getUserByUserName(userName);
		userId = user.getId();
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

	@RequestMapping("/request/{sId}")
	public String showModal(@PathVariable("sId") int id,Model model){

		userRequest u =  detailRepository.getById(id);
		String name = userRepository.getUserNameByUserId(u.getUser().getId());
		model.addAttribute("m", u);
		model.addAttribute("name", name);
		return "dietician/userDetails";
	}

	@GetMapping("/giveDiet/{sId}")
	public String giveDIet(Model model,@PathVariable("sId") int id) {

		model.addAttribute("sId", id);

		model.addAttribute("dieticianDiet", new dieticianDiet());
		return "dietician/diet";
	}

	@PostMapping("/giveDiet/{sId}")
	public String giveDietProcessing(@ModelAttribute("dieticianDiet") dieticianDiet dieticianDiet,@PathVariable("sId") int id) {

		
		dieticianReq u =  requestRepository.getById(id);
	
		dieticianDiet.setUserId(u.getReq());
		dieticianDiet.setDieticianId(userId);
		dieticianDietRepo.save(dieticianDiet);
		requestRepository.deleteById(u.getId());


		

		return "dietician/request";
		
	}

}
