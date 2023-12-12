package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/*
 * ユーザー情報 Repository
 */

/*
 * レポジトリクラスだよという明記、引数にはEntityで記述したIdの型を記述。
 * インターフェースの理由としてはDBからメソッドを呼び出すことができるが
 * メソッドから実装を作ってくれる仕組み？
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
                                                    