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
@Table(name = "reservation_lists")
public class ReservationLists {
	
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
	
	@Column(name ="first_name")
	private String firstName;
	
	@Column(name ="middle_name")
	private String middleName;
	
	@Column(name ="last_name")
	private String lastName;
	
	/*
	 * 誕生日
	 */
	
	@Column(name ="birth_day")
	private Date birthDay;
	
	/*
	 * 性別
	 */
	
	private int sex;
	
	/*
	 * 電話番号
	 */
	
	private String phone;
	
	/*
	 * 人数
	 */
	
	@Column(name ="number_of_people")
	private int numberOfPeople;
	
	/*
	 * 予約日
	 */
	
	@Column(name ="reserve_date")
	private Date reserveDate;
	
	/*
	 * メールアドレス
	 */
	
	private String mail;
	
	/*
	 * 作成日時
	 */
	
	@Column(name ="create_date")
	private Date createDate;
	
	/*
	 * 更新日時
	 */
	
	@Column(name ="update_date")
	private Date updateDate;
	
	/*
	 * 削除日時
	 */
	
	@Column(name ="delete_date")
	private Date deleteDate;


}
