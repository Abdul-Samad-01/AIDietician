package com.Aidietician.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.foodRepository;
import com.Aidietician.Dao.userDetailRepository;
import com.Aidietician.Entities.Nutrition;
import com.Aidietician.Entities.User;
import com.Aidietician.Entities.food;
import com.Aidietician.Entities.userRequest;
import com.Aidietician.Service.process;


@Controller
@RequestMapping(value = "/user")
public class dietController {

    @Autowired
    private foodRepository foodRepository;


    @Autowired
    private userDetailRepository detailRepository;

    @Autowired
    private UserRepository UserRepository;


    String userName;
	int userId;
	// add common data
	@ModelAttribute
	public void addCommomdata(Model model, Principal principal) {
		userName = principal.getName();
		// get the user using userName(Email)
		User user = UserRepository.getUserByUserName(userName);
		userId = user.getId();
		model.addAttribute("user", user);
	}

    @RequestMapping(value = "/dietcal")
    public String dietcalculator(Model model){

        String mealtype = "vegetables";

        HashMap<String,String> map = new HashMap<>();
        food food = new food();
        process p = new process();
       

        List<userRequest> userreq = detailRepository.getDetailsByUserId(userId);
        p.processData(userreq.get(userreq.size()-3));

        double bmr = p.getCalorie();

        int caldev = 2;
        if(mealtype.equals("vegetables")){
            caldev = 3;
        }

        

        int breakfast = (int)Math.round(bmr*0.25);
        int lunch = (int)Math.round(bmr*0.30);
        int dinner = (int)Math.round(bmr*0.25);
        int snack = (int)Math.round(bmr*0.20);


        List<food> f = foodRepository.getFoodbyCatogory("saladss");
        List<food> breakfastlist = foodRepository.getFoodbyCatogory("breakfastt");
        List<food> lunchlist = foodRepository.getFoodbyCatogoryandcalorie(mealtype, lunch/caldev);
        List<food> dinnerlist = foodRepository.getFoodbyCatogoryandcalorie(mealtype, dinner/caldev);
        List<food> snackdata = foodRepository.getFoodbyCatogory("snacks");




        int index = (int)(Math.random() * f.size()-1) + 1;

        map.put("salad1", f.get(index).getName());
        lunch-=f.get(index).getCalories()*2;
        


        index = (int)(Math.random() * f.size()-1) + 1;
        map.put("salad2", f.get(index).getName());
        dinner-=f.get(index).getCalories()*2;

       
        
        if(lunchlist.size() > 1){
            index = (int)(Math.random() * lunchlist.size()-1) + 1;
            map.put("lunch1", lunchlist.get(index).getName());
            index = (int)(Math.random() * lunchlist.size()-1) + 1;
            map.put("lunch2", lunchlist.get(index).getName());
            if(mealtype.equals("vegetables")){
                index = (int)(Math.random() * lunchlist.size()-1) + 1;
                map.put("lunch3", lunchlist.get(index).getName());
            }
        }

        if(lunchlist.size() > 1){
            index = (int)(Math.random() * dinnerlist.size()-1) + 1;
            map.put("dinner1", dinnerlist.get(index).getName());
            index = (int)(Math.random() * dinnerlist.size()-1) + 1;
            map.put("dinner2", dinnerlist.get(index).getName());
            if(mealtype.equals("vegetables")){
                index = (int)(Math.random() * lunchlist.size()-1) + 1;
                map.put("dinner3", lunchlist.get(index).getName());
            }
        }


        int mindiff = Integer.MAX_VALUE;
        index = (int)(Math.random() * f.size()-1) + 1;

        map.put("breakfast1", breakfastlist.get(index).getName());
        breakfast -= breakfastlist.get(index).getCalories();
        String breakfast2 = "";
        for(int i =0;i<breakfastlist.size();i++){
            if(Math.abs(breakfastlist.get(i).getCalories()-breakfast)<mindiff){
                breakfast2 = breakfastlist.get(i).getName();
                
            }
        }
        for(int i =0;i<snackdata.size();i++){
            if(Math.abs(snackdata.get(i).getCalories()-breakfast)<mindiff){
                breakfast2 = snackdata.get(i).getName();
                
            }
        }

        map.put("breakfast2", breakfast2);


        mindiff = Integer.MAX_VALUE;
        String s = "";
        for(int i =0;i<snackdata.size();i++){
            if(Math.abs(snackdata.get(i).getCalories()-snack)<mindiff){
                s = snackdata.get(i).getName();
                
            }
        }

        map.put("snack", s);

        for (String name : map.keySet()){
            System.out.println("key: " + name + " value = "+map.get(name));
        }

        model.addAllAttributes(map);
        

        
        return "normal/dietplan";
    }

    @RequestMapping(value = "/addfood")
    @ResponseBody
    public String addfood(){
        
        // String[] food = {"Sunrise Goji Smoothie","Plum and Greek Yogurt Snack","Banana oatmeal smoothie","Microwave Poached Eggs","Banana Orange Smoothie","Cottage Cheese with Fruit and Granola","Protein Yogurt and Blueberries","Blueberry Shake","Goat Cheese on Toasted Bread with Tomato","Vegetable 3 Egg Scramble","Blueberry Oatmeal","Almonds and Blueberries Yogurt Snack","Banana Egg Pancakes"};
        // for(int i =0;i<food.length;i++){
        //     food f = new food();
        //     Nutrition nut = new Nutrition();
        //     restController r = new restController();
        //     nut =r.callExternalApi(food[i]);
        //     f.setName(food[i]);
        //     f.setFoodcatog("breakfastt");
        //     f.setProtien(nut.getProtein_g());
        //     f.setCalories(nut.getCalories());
        //     f.setCarbohydrate(nut.getCarbohydrates_total_g());
        //     f.setServingweight(100);
        //     foodRepository.save(f);

        // }


            return "dd";
        
    }
}
