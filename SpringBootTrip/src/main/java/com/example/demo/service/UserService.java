package com.example.demo.service;




import java.util.List;


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
    public void makeReserve(ReservationListRequest rlreq,Model model) {
    	try {
    	
    	ReservationLists reservation = new ReservationLists();
    	rlrep.save(reservation);
    	} catch (Exception e) {
    		// 処理に失敗した場合の処理
            if (e.getMessage() != null) {
                 model.addAttribute("message", e.getMessage());
            } else {
                 model.addAttribute("message", "sql文でエラーが発生しました。");
            }
    	}

    }
}
           