package com.example.demo.controller;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;


import com.example.demo.dto.ReservationListRequest;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MailController {
	
	private final MailSender mailSender;
	
	
	
	public void register(ReservationListRequest rLRequest) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		String gender = "";
		
		if(rLRequest.getGender() == 1) {
			gender = "Man";
		}else if(rLRequest.getGender() == 2) {
			gender = "Woman";
		}else if(rLRequest.getGender() == 3) {
			gender = "Not Answered";
		}else {
			gender = "値が挿入されていません";
		}
		
		String time = rLRequest.getReserveTime();
		String newTime = time.substring(0, 5);
		
		message.setTo(rLRequest.getMail());
		message.setSubject("予約内容");
		message.setText("ご予約ありがとうございます。\n以下の内容で予約を承りました。\n"
				+ "\nName:"+rLRequest.getFirstName()+" "+rLRequest.getLastName()+
				"\nBirthDay:"+rLRequest.getBirthYear()+"/"+rLRequest.getBirthMonth()+"/"+rLRequest.getBirthDay()+
				"\nGender:"+gender+
				"\nPhone:"+rLRequest.getPhone()+
				"\nNumber Of People:"+rLRequest.getNumberOfPeople()+"people"+
				"\nReserve Date:"+rLRequest.getYear()+"/"+rLRequest.getMonth()+"/"+rLRequest.getDay()+" "+newTime+"-"+"\n"+
				"\nキャンセルご希望の場合はこちらのメールにご返信お願いします。");

		// メール送信を実施する。
		mailSender.send(message);
		
	}

}
