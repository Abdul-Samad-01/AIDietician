package com.Aidietician.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Aidietician.Entities.food;

public interface foodRepository extends JpaRepository<food, Integer>{
    @Query("select f from food f where f.foodcatog = :foodcatog and f.servingweight !=null")
	public List<food> getFoodbyCatogory(@Param("foodcatog") String foodcatog);

    @Query("select f from food f where f.protien !=null and f.servingweight !=null and f.foodcatog = :foodcatog and f.calories between :calories and :calories+50 ")
	public List<food> getFoodbyCatogoryandcalorie(@Param("foodcatog") String foodcatog,@Param("calories") int calories);
}

