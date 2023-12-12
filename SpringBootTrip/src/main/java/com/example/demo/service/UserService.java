package com.example.demo.service;



import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;



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

    public List<User> searchAll() {
        // ユーザーTBLの内容を全検索
        return userRepository.findAll();
    }
}
           