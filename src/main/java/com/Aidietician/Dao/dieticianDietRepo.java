package com.Aidietician.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Aidietician.Entities.dieticianDiet;

public interface dieticianDietRepo extends JpaRepository<dieticianDiet, Integer>{
    @Query("select d from dieticianDiet d where d.userId = :userId")
	public List<dieticianDiet> getDietByUserId(@Param("userId") int userId);
}