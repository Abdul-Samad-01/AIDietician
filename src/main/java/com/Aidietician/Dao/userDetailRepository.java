package com.Aidietician.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Aidietician.Entities.userRequest;



public interface userDetailRepository extends JpaRepository<userRequest, Integer>{
    @Query("from userRequest as u where u.user.id = :userId")
	public List<userRequest> getDetailsByUserId(@Param("userId") int userId);
}
