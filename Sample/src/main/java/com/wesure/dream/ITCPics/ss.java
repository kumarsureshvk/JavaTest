package com.wesure.dream.ITCPics;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ss {
    @RequestMapping("/s")
	public String name() {
		return "sssws";
	}
}