package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.ReservationListRequest;
import com.example.demo.entity.ReservationLists;
import com.example.demo.entity.User;
import com.example.demo.service.StaffService;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;
/**
 * ユーザー情報 Controller
 */
@Controller
@RequiredArgsConstructor
public class UserController {
  /**
   * ユーザー情報 Service
   */
  	
  private final UserService userService;
  
  private final StaffService staffService;
  
  /**
   * ユーザー情報一覧画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面のHTML
   */
  
  //予約者一覧を表示する（ログインが必要）
  @RequestMapping(value = "/user/list", method = RequestMethod.GET)
  public String displayList(Model model) {
    List<ReservationLists> userlist = staffService.showUserInfoAll();
    model.addAttribute("userlist", userlist);
    return "user/list";
  }
  
  @RequestMapping(value = "/search/asc", method = RequestMethod.GET)
  public String searchAsc(Model model) {
	  List<ReservationLists> userlist = staffService.getUsersBirthdayAsc();
	  model.addAttribute("userlist", userlist);
      return "user/list";
  }

  @RequestMapping(value = "/search/desc", method = RequestMethod.GET)
  public String searchDesc(Model model) {
	  List<ReservationLists> userlist = staffService.getUsersBirthdayDesc();
	  model.addAttribute("userlist", userlist);
      return "user/list";
  }
  
  //選択した性別一覧を表示する（ログインが必要）
  @RequestMapping(value = "/search/{gender}", method = RequestMethod.GET)
  public String searchDesc(@RequestParam int gender,Model model) {
	  List<ReservationLists> userlist = staffService.showGetUsersGender(gender);
	  model.addAttribute("userlist", userlist);
      return "user/list";
  }

  /*
   * 予約画面の空き状況を表示
   */
  
  @RequestMapping(value = "/user/tripReserve", method = RequestMethod.GET)
  public String showReserve(Model model) {
    
    return "user/trip-reserve";
  }
  
  /*
   * 予約情報入力画面を表示
   */
  
  @RequestMapping(value = "/user/inputReserveView/{year}/{month}/{day}", method = RequestMethod.GET)
  public String showReserveInfo(@PathVariable("year") int year, @PathVariable("month") int month, @PathVariable("day") int day, Model model,ReservationListRequest rlreq) {
	  
	  model.addAttribute("year", year);
	  model.addAttribute("month", month);
	  model.addAttribute("day", day);
	  
    return "user/inputReserveView";
  }
  
  
  /*
   * 確認画面ページに値を送信
   */
  @RequestMapping(value = "/user/reserveReview", method = RequestMethod.POST)
  public String postReserveInfo(Model model,
		  @ModelAttribute("firstName") String firstName, 
		  @ModelAttribute("lastName") String lastName,
		  @ModelAttribute("birthYear") Integer birthYear,
		  @ModelAttribute("birthMonth") Integer birthMonth,
		  @ModelAttribute("birthDay") Integer birthDay,
		  @ModelAttribute("gender") Integer gender,
		  @ModelAttribute("phone") String phone,
		  @ModelAttribute("numberOfPeople") Integer numberOfPeople,
		  @ModelAttribute("year") String year,
		  @ModelAttribute("month") String month,
		  @ModelAttribute("day") String day,
		  @ModelAttribute("reserveTime") String reserveTime,
		  @ModelAttribute("mail") String mail) {
	Map<String,Object> attributes = new HashMap<>();
	

	attributes.put("firstName", firstName);
    attributes.put("lastName", lastName);
    attributes.put("birthYear",birthYear);
    attributes.put("birthMonth",birthMonth);
    attributes.put("birthDay",birthDay);
    attributes.put("gender", gender);
    attributes.put("phone", phone);
    attributes.put("numberOfPeople", numberOfPeople);
    attributes.put("year", year);
    attributes.put("month", month);
    attributes.put("day", day);
    attributes.put("reserveTime", reserveTime);
    attributes.put("mail", mail);

    model.addAttribute("attributes", attributes);
    return "user/reservationReview";
  }
  
  @RequestMapping(value = "/user/reserveCompleted", method = RequestMethod.POST)
  public String makeReserve(@Validated @ModelAttribute ReservationListRequest reservationListRequest, BindingResult result, Model model) {
	  
	  if(result.hasErrors()) {
		  List<String> errorList = new ArrayList<String>();
		  for(ObjectError error : result.getAllErrors()) {
			  errorList.add(error.getDefaultMessage());
		  }
		  model.addAttribute("validationError", errorList);
		  
		  return "user/inputReserveView";
	  }
	  
	  //登録処理のメソッド
	  userService.makeReserve(reservationListRequest, model);
	  
    return "user/reserveCompleted";
  }
  
}                  