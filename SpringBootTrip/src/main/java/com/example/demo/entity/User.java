package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/*
 * ユーザー情報
 */

@Entity
@Data
@Table(name = "user")
public class User {
	
	/*
	 * ID
	 */
	
	
	/*
	 * DBテーブルのフィールド名とEntityのフィールド名が異なる場合は
	 * ＠Columnと明記して、フィールドの紐づけを行う。裏を返すと
	 * 同じであれば明記不要。
	 */
	
	//プライマリーキーですよという明記
	@Id
	@Column(name ="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/*
	 * 名前
	 */
	
	@Column(name ="name")
	private String name;
	
	/*
	 * 住所
	 */
	
	@Column(name ="address")
	private String address;
	
	/*
	 * 電話番号
	 */
	
	@Column(name ="phone")
	private String phone;
	
	/*
	 * 更新日時
	 */
	
	@Column(name ="update_date")
	private String updateDate;
	
	/*
	 * 削除日時
	 */
	
	@Column(name ="dalete_date")
	private String deleteDate;


}