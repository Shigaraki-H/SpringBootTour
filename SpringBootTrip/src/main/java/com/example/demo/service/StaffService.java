package com.example.demo.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ReservationLists;
import com.example.demo.repository.ReservationListsRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor()
public class StaffService {
	
	private final ReservationListsRepository rlrep;
	
	//予約個人情報一覧表示
    public List<ReservationLists> showUserInfoAll(){
    	return rlrep.findAll();
    }
    
    
    //予約個人情報生年月日昇順一覧表示
    public List<ReservationLists> getUsersBirthdayAsc() {
    	 // データベース操作のカラムの昇順の取得用メソッド
         return rlrep.findAllByOrderByBirthDayAsc();
    }
    
    //予約個人情報生年月日昇順一覧表示
    public List<ReservationLists> getUsersBirthdayDesc() {
    	 // データベース操作のカラムの昇順の取得用メソッド
         return rlrep.findAllByOrderByBirthDayDesc();
    }
    
    //予約個人情報性別一覧表示
    public List<ReservationLists> showGetUsersGender(int gender) {
    	
    	//男性一覧表示
    	if(gender == 1) {
    		return rlrep.findBySexOrderBySex(gender);
    	}//女性一覧表示
    	else if(gender == 2) {
    		return rlrep.findBySexOrderBySex(gender);
    	}//未回答一覧表示
    	else if(gender == 3){
    		return rlrep.findBySexOrderBySex(gender);
    	}else {
    		return rlrep.findAll();
    	}
    	
    }
    
    

}
