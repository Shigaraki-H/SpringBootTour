package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


@Controller
public class HelloWorldController {

	/*
	 * ブラウザの上のURLに入力するルートを示している
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String helloWorld(Model model) {
		model.addAttribute("message","hello SpringBoot");
		/*
		 * 表示する画面を示している。この場合ファイルのなまえ
		 * ※拡張子はいらない
		 */
		return "index";
	}

}