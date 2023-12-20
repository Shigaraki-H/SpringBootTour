package com.example.demo.dto;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/*
 * ユーザー情報 リクエストデータ
 */

@Data
public class ReservationListRequest implements Serializable{
 /**
   * 名前
   */
  @NotEmpty(message = "first nameを入力してください")
  @Size(max = 100, message = "first nameは100桁以内で入力してください")
  private String firstName;
  
  @NotEmpty(message = "last nameを入力してください")
  @Size(max = 100, message = "last nameは100桁以内で入力してください")
  private String lastName;
  
  /**
   * 生年月日
   */
  private int birthYear;
  private int birthMonth;
  private int birthDay;
  
  /**
   * 性別
   */
  private int gender;
  
  /**
   * 電話番号
   */
  @Pattern(regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}", message = "電話番号の形式で入力してください")
  private String phone;
  
  /**
   * 予約人数
   */
  private int numberOfPeople;
  
  /**
   * 予約日
   */
  private String reserveTime;
  
  /**
   * メール
   */
  private String mail;
}
