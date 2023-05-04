package com.Aidietician.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.MapDataSourceLookup;
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
import com.Aidietician.helper.Messages;

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
	String name;
	// add common data
	@ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		userName = principal.getName();
		// get the user using userName(Email)
		User user = userRepository.getUserByUserName(userName);
		userId = user.getId();
		name = user.getName();
		model.addAttribute("user", user);
	}

	// user dashboard
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "User Dashboard - AI Dietician");
		return "normal/user_dashboard";
	}

	@RequestMapping("/profile")
	public String profile(Model model, Principal principal) {
		model.addAttribute("title", "profile - AI Dietician");

		Queue<Integer> q = new LinkedList<>();
      
		// Add 7 zeros to the queue
		for (int i = 0; i < 7; i++) {
		   q.add(0);
		}

		List<userRequest> userdetails = new ArrayList<>();
		
		List<Integer> bmilist = new ArrayList<>();
		userdetails = userDetailRepository.getDetailsByUserId(userId);
		
		for(int i =userdetails.size()-1;i>=0;i--){
			if((userdetails.size()-1-i)>=7){
				break;
			}
			process.processData(userdetails.get(i));
			q.remove();
			q.add((int)process.getbmi());
		
			
			
		}
		for (int i = 0; i < 7; i++) {
			bmilist.add(0,q.peek());
			q.remove();
		}

		
		
		model.addAttribute("userdetails",userdetails.size()>0?userdetails.get(userdetails.size()-1):new userRequest());
		model.addAttribute("Name", name);
		model.addAttribute("bmi", bmilist);
		for (int index = 0; index < bmilist.size(); index++) {
			System.out.println(bmilist.get(index));

		}



		return "normal/profile";
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
		System.out.println(process.getCalorie());
		int calories = (int)process.getCalorie();
		HashMap<String,Integer> map = new HashMap<>();
		map.put("maintain", calories);
		map.put("mildLoss", calories-200);
		map.put("loss", calories-500);
		map.put("extreeme", calories-1000);	


		model.addAllAttributes(map);

		return "normal/currRes";
	}

	@RequestMapping(value = "/showDiet")
	public String showDiet() {


		

		return "normal/showDiet";
	}

	@RequestMapping(value = "/reqdiet")
	public String reqDiet(Principal principal,Model model,HttpSession session) {

		try {
			if(userDetailRepository.getDetailsByUserId(userId).size()==0){
				throw new Exception("Enter your details from add details module");
			}
		
		if(requestRepository.getReqByUserId(userId)==null){
		dieticianReq req = new dieticianReq();
		req.setReq(userId);
		req.setDescription("");
		requestRepository.save(req);
		}
	} catch (Exception e) {
		session.setAttribute("message", new Messages("Enter your details from add details module and then send the request", "alert-danger"));
		return "redirect:showDiet";
	
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

		

		
		return "normal/dieticianDietpage";
	}

	@GetMapping(value = "/dieticianDiet/{id}")
	public String deleteDiet(@PathVariable("id") int id ){
		if(id !=0){
		dieticianDietRepo.deleteById(id);
		}
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
			int cal = nut.getCalories();
			
			map.put("jog",(int)Math.ceil(cal/((8*65)/60)));
			map.put("yoga",(int)Math.ceil(cal/((4*65)/60)));
			map.put("walk",(int)Math.ceil(cal/((5.5*65)/60)));
			map.put("gym",(int)Math.ceil(cal/((6*65)/60)));
			model.addAllAttributes(map);
			

            model.addAttribute("food", nut);


            
            return "normal/checkFoodDetails";
        }

		@RequestMapping(value="/contact", method=RequestMethod.GET)
		public String nearbyDietician() {
			return "normal/nearbydietician";
		}
		

		
	
}
