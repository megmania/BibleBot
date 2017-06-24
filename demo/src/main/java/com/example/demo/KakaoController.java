package com.example.demo;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Keyboard;
import com.example.demo.domain.Message;
import com.example.demo.domain.RedisPooler;
import com.example.demo.domain.ResultMessage;
import com.example.demo.domain.UserMessage;

@RestController
@EnableAutoConfiguration
public class KakaoController {
	
	private final Log log = LogFactory.getLog(this.getClass());
	
	//@Autowired
	//RedisPooler redisPooler;
	
	@RequestMapping("keyboard")
	public Keyboard keyboard(){
		
		String [] buttons = {"구약","신약"};
		
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		keyboard.setButtons(buttons);
		
		return keyboard;
	}
	
	@RequestMapping(value="/message", produces={"application/json;charset=utf-8"})
	public ResultMessage message(@RequestBody UserMessage userMessage){
		ResultMessage resultMessage = new ResultMessage();
		
		String content = userMessage.getContent();
		Message message = new Message();
		
		Keyboard keyboard = new Keyboard();
		keyboard.setType("buttons");
		
		
		if("구약".equals(content)){
			String [] buttons = {"창세기","출애굽기", "레위기" };
			keyboard.setButtons(buttons);
		}else if("신약".equals(content)){
			String [] buttons = {"마태","마가", "누가" };
			keyboard.setButtons(buttons);
		}
		message.setText(content+"을 선택하셨습니다.");
		resultMessage.setMessage(message);
		resultMessage.setKeyboard(keyboard);
		
		if("창세기".equals(content)){
			
			Document doc = null;
			try {
				doc = Jsoup.connect("http://www.biblestudytools.com/kjv/genesis/1.html").get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements returnStrContent = doc.select("#v-1");
			log.info(returnStrContent.text());
			
			message.setText(returnStrContent.text());
			resultMessage.setMessage(message);
		}
		
		/*
		try (Jedis jedis =  redisPooler.getRedisPooler()){
			
			//Jedis jedis = new Jedis("localhost");
			jedis.set("foo", "bar");
			String value = jedis.get("foo");
			System.out.println(value);
		}
		*/
		
		
		
		return resultMessage;
	}

}
