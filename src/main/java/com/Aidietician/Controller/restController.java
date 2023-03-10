package com.Aidietician.Controller;



import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.Aidietician.Entities.Nutrition;

@RestController
    @RequestMapping("/callExternalApi")
    public class restController {
        
       


        @GetMapping
        @ResponseBody
        public String callExternalApi() {
            String url = "https://api.api-ninjas.com/v1/nutrition?query= fries and roti";
            HttpHeaders headers = new HttpHeaders();
            headers.add("x-api-key", "YjDytEdiDe6OYcLbQzlC3ROV5LKYeacTpvTeeP6i");
            HttpEntity<Object> entity=new HttpEntity<Object>(headers);
    
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            List<Nutrition> list = new ArrayList<>();
            JSONArray n = new JSONArray(response.getBody());
            
            for (int index = 0; index < n.length(); index++) {
                Nutrition nut = new Nutrition();
                JSONObject j = n.getJSONObject(index);
                nut.setCalories(j.getInt("calories"));
                nut.setName(j.getString("name"));
                nut.setCarbohydrates_total_g(j.getInt("carbohydrates_total_g"));
                nut.setCholesterol_mg(j.getInt("cholesterol_mg"));
                nut.setFat_total_g(j.getInt("fat_total_g"));
                nut.setFiber_g(j.getInt("fiber_g"));
                nut.setPotassium_mg(j.getInt("potassium_mg"));
                nut.setProtein_g(j.getInt("protein_g"));
                nut.setServing_size_g(j.getInt("serving_size_g"));
                nut.setSugar_g(j.getInt("sugar_g"));
                nut.setFat_saturated_g(j.getInt("fat_saturated_g"));    
                nut.setSodium_mg(j.getInt("sodium_mg"));
                list.add(nut);
                
            }
            
    
            

            
            return response.getBody();
        }


       
    
    }

