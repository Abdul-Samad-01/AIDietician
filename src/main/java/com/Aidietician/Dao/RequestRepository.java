package com.Aidietician.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Aidietician.Entities.dieticianReq;

public interface RequestRepository extends JpaRepository<dieticianReq, Integer>{
    @Query("select d from dieticianReq d where d.req = :req")
	public dieticianReq getReqByUserId(@Param("req") int req);
}