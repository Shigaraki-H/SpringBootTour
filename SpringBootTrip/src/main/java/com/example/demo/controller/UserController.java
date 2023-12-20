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

import com.example.demo.dto.ReservationListRequest;
import com.example.demo.entity.ReservationLists;
import com.example.demo.entity.User;
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
  /**
   * ユーザー情報一覧画面を表示
   * @param model Model
   * @return ユーザー情報一覧画面のHTML
   */
  @RequestMapping(value = "/user/list", method = RequestMethod.GET)
  public String displayList(Model model) {
    List<User> userlist = userService.searchAll();
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
		  @ModelAttribute("reserveTime") String reserveTime,
		  @ModelAttribute("mail") String mail) {
	Map<String,Object> attributes = new HashMap<>();
	Date birth_day = new Date(); // birth_dayを適切な値で初期化
	attributes.put(birthYear + "" + birthMonth + "" + birthDay, birth_day);

	attributes.put("firstName", firstName);
    attributes.put("lastName", lastName);
    attributes.put(String.valueOf(birthYear) +"/"+ String.valueOf(birthMonth) +"/"+ String.valueOf(birthDay), birth_day);
    attributes.put("gender", gender);
    attributes.put("phone", phone);
    attributes.put("numberOfPeople", numberOfPeople);
    attributes.put("reserveTime", reserveTime);
    attributes.put("mail", mail);

    model.addAttribute("attributes", attributes);
    return "user/reservationReview";
  }
  
  @RequestMapping(value = "/user/reservationReview", method = RequestMethod.POST)
  public String makeReserve(@Validated @ModelAttribute ReservationListRequest reservationListRequest, BindingResult result, Model model) {
	  
	  if(result.hasErrors()) {
		  List<String> errorList = new ArrayList<String>();
		  for(ObjectError error : result.getAllErrors()) {
			  errorList.add(error.getDefaultMessage());
		  }
		  model.addAttribute("validationError", errorList);
		  
		  return "user/inputReserveView";
	  }
	  userService.makeReserve(reservationListRequest, model);
    return "user/reservationReview";
  }
  
}                  