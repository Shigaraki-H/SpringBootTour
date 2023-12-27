package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ReservationLists;

public interface ReservationListsRepository extends JpaRepository<ReservationLists, Long>{
	
	List<ReservationLists> findAllByOrderByBirthDayAsc();
	List<ReservationLists> findAllByOrderByBirthDayDesc();
	
	List<ReservationLists> findBySexOrderBySex(int sex);

}
