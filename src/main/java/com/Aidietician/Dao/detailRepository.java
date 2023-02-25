package com.Aidietician.Dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Aidietician.Entities.userReq;

public interface detailRepository extends JpaRepository<userReq, Integer> {
	
}
