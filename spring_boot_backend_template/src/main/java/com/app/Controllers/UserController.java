package com.app.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Services.UserService;
import com.app.dto.UserRegisterDTO;
import com.app.dto.UserLoginDTO;


@RestController
@RequestMapping
@Validated
public class UserController {
	
	@Autowired
	UserService serv;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody @Valid UserRegisterDTO u){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(serv.register(u));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
		}
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid UserLoginDTO u){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(serv.login(u));
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(e.getMessage());
		}
	}
}