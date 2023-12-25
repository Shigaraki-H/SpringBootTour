package com.example.demo.service;




import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ReservationListRequest;
import com.example.demo.entity.ReservationLists;
import com.example.demo.entity.User;
import com.example.demo.repository.ReservationListsRepository;
import com.example.demo.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import java.lang.IllegalAccessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



/*
 * ユーザー情報 Service
 * 
 * UserServiceクラスの上に@Transactionalアノテーションと
 * メソッドの上に@Autowiredという記載のやり方もあるみたいだが
 * どうやら古いやり方らしい。
 * 
 * 前内容以下
 * 
 * @Service
 * @Transactional(rollbackFor = Exception.class)
 * public class UserService {
 * @Autowired
 * UserRepository userRepository;
 * public List<User> searchAll() {
 *  // ユーザーTBLの内容を全検索
 *     return userRepository.findAll();
 * }
 */

@Service
@RequiredArgsConstructor()
public class UserService {

	//代入しなくとも引数名でとってこれるっぽい
	private final UserRepository userRepository;
	private final ReservationListsRepository rlrep;

    public List<User> searchAll() {
        // ユーザーTBLの内容を全検索
        return userRepository.findAll();
    }
    
    public List<ReservationLists> reviewInfo(){
    	return rlrep.findAll();
    }
    
    @Transactional
    public ReservationLists makeReserve(ReservationListRequest rlreq,Model model) {
    	
    		
    		//Beanの(Dozer.mapper)を使うともっと短く書けるらしい
    	
		    	var reservationLists = new ReservationLists();
		    	
		    	//DateFormat型の雛形を用意(誕生日)
		    	SimpleDateFormat birthsdf = new SimpleDateFormat("yyyy/MM/dd");
		         
		        SimpleDateFormat reserveDateSdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    	
		    	
		    	 // rlreqから値を取得してreservationに設定
		    	reservationLists.setFirst_name(rlreq.getFirstName());
		    	reservationLists.setLast_name(rlreq.getLastName());
		    	
		    	String birthYear = String.valueOf(rlreq.getBirthYear());
		    	String birthMonth = String.valueOf(rlreq.getBirthMonth());
		    	String birthDay = String.valueOf(rlreq.getBirthDay());
		    	
		    	//getし誕生日を文字列結合させる
		    	String birthStrDate = birthYear + "/" + birthMonth + "/" + birthDay;
		    	
		    	String reserveYear = rlreq.getYear();
		    	
		    	String reserveMonth = rlreq.getMonth();
		    	
		    	String reserveDay = rlreq.getDay();
		    	
		    	String reserveTime = rlreq.getReserveTime();
		    	String reserveStrDate = reserveYear+"/"+reserveMonth+"/"+reserveDay+" "+reserveTime+":00";
		    	
		    	try {
		    	    Date birthDate = birthsdf.parse(birthStrDate);
		    	    
		    	    reservationLists.setBirth_day(birthDate);
		    	} catch (ParseException e) {
		    	    e.printStackTrace();
		    	}

		    	
		    	reservationLists.setSex(rlreq.getGender());
		    	
		    	reservationLists.setPhone(rlreq.getPhone());
		    	
		    	
		    	reservationLists.setNumber_of_people(rlreq.getNumberOfPeople());
		    	
		    	reservationLists.setPhone(rlreq.getPhone());
		    	
		    	
		    	
		    	
		    	try {
		    		Date reserveDate = reserveDateSdf.parse(reserveStrDate);
		    	    reservationLists.setReserve_date(reserveDate);
		    	} catch (ParseException e) {
		    	    e.printStackTrace();
		    	}
		    	
		    	
		    	reservationLists.setMail(rlreq.getMail());
		    	
		    	Date now = new Date();
		    	reservationLists.setCreateDate(now);
		        
		    	return rlrep.save(reservationLists);
    	
    	
    	
    	
    	

    }
}
           