package com.Aidietician.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Aidietician.Dao.RequestRepository;
import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.dieticianDietRepo;
import com.Aidietician.Dao.faqRepository;
import com.Aidietician.Dao.foodRepository;
import com.Aidietician.Dao.userDetailRepository;
import com.Aidietician.Entities.Faq;
import com.Aidietician.Entities.Nutrition;
import com.Aidietician.Entities.User;
import com.Aidietician.Entities.dieticianReq;
import com.Aidietician.Entities.food;
import com.Aidietician.Entities.userRequest;
import com.Aidietician.Entities.dieticianDiet;
import com.Aidietician.Service.process;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private process process;

	@Autowired
	private faqRepository faqRepository;

	@Autowired
	private RequestRepository requestRepository;

	@Autowired
	private userDetailRepository userDetailRepository;

	@Autowired
	private dieticianDietRepo dieticianDietRepo;

	@Autowired
	private foodRepository foodRepository;

	

	String userName;
	int userId;
	// add common data
	@ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		userName = principal.getName();
		// get the user using userName(Email)
		User user = userRepository.getUserByUserName(userName);
		userId = user.getId();
		model.addAttribute("user", user);
	}

	// user dashboard
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "User Dashboard - AI Dietician");
		return "normal/user_dashboard";
	}

	@RequestMapping(value = "/bmi")
	public String bmi() {
		return "normal/bmiauto";
	}

	@GetMapping(value = "/addDetails")
	public String Details(Model model) {
		model.addAttribute("details", new userRequest());

		return "normal/addDetails";
	}

	

	@PostMapping(value = "/addDetails")
	public String greetingSubmit(@ModelAttribute("details") userRequest userRequest, Model model, Principal p) {

		// adding to list
		String name = p.getName();
		User user = userRepository.getUserByUserName(name);
		userRequest.setUser(user);
		user.getUserdetailsList().add(userRequest);

		userDetailRepository.save(userRequest);
		process.processData(userRequest);
		System.out.println(userRequest.toString());
		model.addAttribute("message", process.toString());

		return "normal/currRes";
	}

	@RequestMapping(value = "/showDiet")
	public String showDiet() {


		

		return "normal/showDiet";
	}

	@RequestMapping(value = "/reqdiet")
	public String reqDiet(Principal principal) {


		if(requestRepository.getReqByUserId(userId)==null){
		dieticianReq req = new dieticianReq();
		req.setReq(userId);
		req.setDescription("");
		requestRepository.save(req);
		}
		
		

		return "redirect:showDiet";
	}

	@GetMapping(value = "/dieticianDiet")
	public String dieticianDiet(Model m){

		List<dieticianDiet> diet = dieticianDietRepo.getDietByUserId(userId);
		if (diet.size()>0) {
			m.addAttribute("diet", diet.get(0));
		} else {
			m.addAttribute("diet", new dieticianDiet());
		}
		
		return "/normal/dieticianDiet";
	}

	@GetMapping(value = "/dieticianDiet/{id}")
	public String deleteDiet(@PathVariable("id") int id ){
		dieticianDietRepo.deleteById(id);;
		return "redirect:/user/dieticianDiet";
	}
	


	@RequestMapping(value = "/faq")
	public String faq(Model model) {

		List<Faq> list = faqRepository.findAll();

		model.addAttribute("faq", list);
		


		return "normal/faq";
	}

	@RequestMapping(value="/foodDetails", method=RequestMethod.GET)
	public String FoodDetails(Model model) {
		Nutrition n = new Nutrition();
		model.addAttribute("food", n);
		model.addAttribute("m", 0);
		return "normal/checkFoodDetails";
	}

	@RequestMapping(value="/food", method=RequestMethod.POST)
        public String foodnutrition(@RequestParam String food,Model model) {

			restController r = new restController();
            Nutrition nut =r.callExternalApi(food);
			
			model.addAttribute("m", 1);
			Map<String,Integer> map = new HashMap<>();
			map.put("jog",Integer.valueOf((nut.getCalories()/229)*60));
			map.put("yoga",Integer.valueOf((nut.getCalories()/223)*60));
			map.put("walk",Integer.valueOf((nut.getCalories()/294)*60));
			map.put("gym",Integer.valueOf((nut.getCalories()/483)*60));
			model.addAllAttributes(map);
			

            model.addAttribute("food", nut);


            
            return "normal/checkFoodDetails";
        }

		
	
}
