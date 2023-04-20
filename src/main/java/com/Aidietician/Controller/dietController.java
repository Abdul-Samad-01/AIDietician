package com.Aidietician.Controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Aidietician.Dao.UserRepository;
import com.Aidietician.Dao.foodRepository;
import com.Aidietician.Dao.userDetailRepository;
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

    @RequestMapping(value = "/dietcal/{selectedOption}")
    public String dietcalculator(Model model,@PathVariable("selectedOption") String selectedOption){
        String mealtype = selectedOption;
        int totalcalories = 0;
        HashMap<String,String> map = new HashMap<>();
        HashMap<String,Integer> calories = new HashMap<>();


        process p = new process();
       

        List<userRequest> userreq = detailRepository.getDetailsByUserId(userId);
        p.processData(userreq.get(userreq.size()-1));

        double bmr = p.getCalorie();


        int caldev = 2;
        if(mealtype.equals("vegetables")){
            caldev = 3;
        }

        
        // breaking calories into meals
        int breakfast = (int)Math.round(bmr*0.25);
        int lunch = (int)Math.round(bmr*0.30);
        int dinner = (int)Math.round(bmr*0.25);
        int snack = (int)Math.round(bmr*0.20);

        System.out.println(breakfast+" "+lunch+" "+dinner+" "+snack);

        // fetching data of snack breakfast and salads from database
        List<food> f = foodRepository.getFoodbyCatogory("saladss");
        List<food> breakfastlist = foodRepository.getFoodbyCatogory("breakfastt");
        List<food> snackdata = foodRepository.getFoodbyCatogory("snacks");



        // salad for lunch
        int index = (int)(Math.random() * f.size()-1) + 1;

        map.put("salad1", f.get(index).getName());
        lunch-=f.get(index).getCalories()*2;
        totalcalories+=f.get(index).getCalories()*2;

        System.out.println("salad1 "+f.get(index).getCalories()*2);

        calories.put("lunchsalad", f.get(index).getCalories()*2);
        
        

        // salad for dinner
        index = (int)(Math.random() * f.size()-1) + 1;
        map.put("salad2", f.get(index).getName());
        dinner-=f.get(index).getCalories()*2;
        totalcalories+=f.get(index).getCalories()*2;
        System.out.println("salad2 "+f.get(index).getCalories()*2);
        calories.put("dinnersalad", f.get(index).getCalories()*2);


        // fetching data of lunch and dinner on the basis of calories from database
        List<food> lunchlist = foodRepository.getFoodbyCatogoryandcalorie(mealtype, lunch/caldev);
        List<food> dinnerlist = foodRepository.getFoodbyCatogoryandcalorie(mealtype, dinner/caldev);


        

       
        // creating food list for lunch
        if(lunchlist.size() > 1){
            index = (int)(Math.random() * lunchlist.size()-1) + 1;
            map.put("lunch1", lunchlist.get(index).getName());
            map.put("lunch1serve", lunchlist.get(index).getServingdesc());// serving size
            totalcalories+=lunchlist.get(index).getCalories();

            System.out.println("lunch1 "+lunchlist.get(index).getCalories());
            calories.put("lunch1",lunchlist.get(index).getCalories());



            index = (int)(Math.random() * lunchlist.size()-1) + 1;
            map.put("lunch2", lunchlist.get(index).getName());
            map.put("lunch2serve", lunchlist.get(index).getServingdesc());// serving size

            totalcalories+=lunchlist.get(index).getCalories();

            System.out.println("lunch2"+lunchlist.get(index).getCalories());
            calories.put("lunch2",lunchlist.get(index).getCalories());


            if(mealtype.equals("vegetables")){
                index = (int)(Math.random() * lunchlist.size()-1) + 1;
                map.put("lunch3", lunchlist.get(index).getName());
                map.put("lunch3serve", lunchlist.get(index).getServingdesc());// serving size

                totalcalories+=lunchlist.get(index).getCalories();

                System.out.println("lunch3 "+lunchlist.get(index).getCalories());
                calories.put("lunch3",lunchlist.get(index).getCalories());


            }
        }

        // creating food list for dinner
        if(dinnerlist.size() > 1){
            index = (int)(Math.random() * dinnerlist.size()-1) + 1;
            map.put("dinner1", dinnerlist.get(index).getName());
            map.put("dinner1serve", dinnerlist.get(index).getServingdesc());// serving size

            totalcalories+=dinnerlist.get(index).getCalories();

            System.out.println("dinner1 "+dinnerlist.get(index).getCalories());
            calories.put("dinner1",dinnerlist.get(index).getCalories());



            index = (int)(Math.random() * dinnerlist.size()-1) + 1;
            map.put("dinner2", dinnerlist.get(index).getName());
            map.put("dinner2serve", dinnerlist.get(index).getServingdesc());// serving size

            totalcalories+=dinnerlist.get(index).getCalories();

            System.out.println("dinner2 "+dinnerlist.get(index).getCalories());
            calories.put("dinner2",dinnerlist.get(index).getCalories());



            if(mealtype.equals("vegetables")){
                index = (int)(Math.random() * dinnerlist.size()-1) + 1;
                map.put("dinner3", dinnerlist.get(index).getName());
                map.put("dinner3serve", dinnerlist.get(index).getServingdesc());// serving size

                totalcalories+=dinnerlist.get(index).getCalories();

                System.out.println("dinner3 "+dinnerlist.get(index).getCalories());
                calories.put("dinner3",dinnerlist.get(index).getCalories());



            }
        }

        // list of breakfast
        int mindiff = Integer.MAX_VALUE;
        index = (int)(Math.random() * f.size()-1) + 1;
        int breakfast2cal=0;
        map.put("breakfast1", breakfastlist.get(index).getName());
        breakfast -= breakfastlist.get(index).getCalories();
        totalcalories+=breakfastlist.get(index).getCalories();

        System.out.println("breakfast1 "+breakfastlist.get(index).getCalories());
        calories.put("breakfast1",breakfastlist.get(index).getCalories());



        String breakfast2 = "";
        for(int i =0;i<breakfastlist.size();i++){
            int currdiff = Math.abs(breakfastlist.get(i).getCalories()-breakfast);
            if(currdiff<mindiff){
                mindiff = currdiff;
                breakfast2 = breakfastlist.get(i).getName();
                breakfast2cal = breakfastlist.get(i).getCalories();
                
            }
        }


       for(int i =0;i<snackdata.size();i++){
            int currdiff = Math.abs(snackdata.get(i).getCalories()-breakfast);
            if(currdiff<mindiff){
                mindiff = currdiff;
                breakfast2 = snackdata.get(i).getName();
                breakfast2cal = snackdata.get(i).getCalories();

                
            }
        }
        totalcalories+=breakfast2cal;
        System.out.println("breakfast2 "+ breakfast2cal);
        calories.put("breakfast2", breakfast2cal);
        map.put("breakfast2", breakfast2);



        // list of snack
        mindiff = Integer.MAX_VALUE;
        String s = "";
        HashMap<Integer,String> temp = new HashMap<>();
        for(int i =0;i<snackdata.size();i++){
            int currdiff = Math.abs(snackdata.get(i).getCalories()-snack);
            
            temp.put(currdiff,snackdata.get(i).getName());


        }
        TreeMap<Integer, String> sortedMap = new TreeMap<Integer, String>(temp);
        List<String> snacktemp = new ArrayList<>();
        for (Map.Entry<Integer,String> entry : sortedMap.entrySet()) {
            snacktemp.add(entry.getValue());
            if(snacktemp.size()>5){
                break;
            }
        }
        index =   (int)(Math.random() * snacktemp.size()-1) + 1;
        s = snacktemp.get(index);
        int bmrint = (int)bmr;
        if(bmrint>totalcalories){
            

        bmrint = bmrint - totalcalories;
        
        calories.put("snack", bmrint);
        }else{
            calories.put("snack", 0);
        }
        
        System.out.println(totalcalories);
        System.out.println(bmr);

        lunch = calories.get("lunchsalad")+calories.get("lunch1")+calories.get("lunch2")+calories.getOrDefault("lunch3", 0);
        map.put("lunchcal",""+lunch );

        dinner = calories.get("dinnersalad")+calories.get("dinner1")+calories.get("dinner2")+calories.getOrDefault("dinner3", 0);;
        map.put("dinnercal",""+dinner );

        breakfast = calories.get("breakfast1")+calories.get("breakfast2");
        map.put("breakfastcal", ""+breakfast);

        map.put("snackcal", ""+calories.get("snack"));





        map.put("snack", s);

        for (String name : map.keySet()){
            System.out.println("key: " + name + " value = "+map.get(name));
        }

        model.addAllAttributes(map);
        model.addAttribute("mealtype",selectedOption);
        

        
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
