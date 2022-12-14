package com.kim.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kim.shoppingcart.dto.UserDto;
import com.kim.shoppingcart.model.User;
import com.kim.shoppingcart.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuthController {
	@Autowired
	private UserService userService;
	
	public AuthController(UserService userService) {
        this.userService = userService;
    }
	

	 // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    //After login successfully
    @PostMapping("/login")
    public String loginSuccess(){
        return "shopping";
    }
    
    
	// Show the register page
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user",user);
		return "register";
	}
	
	//After register successfully
	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto, BindingResult result, Model model) {
		User existingUser = userService.findUserByEmail(userDto.getEmail());
		if(existingUser!=null && existingUser.getEmail()!=null && !existingUser.getEmail().isEmpty()) {
			result.rejectValue("email", null,  "There is already an account registered with the same email");
			
		}
		
		if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register?error";
        }
		
		userService.saveUser(userDto);
//        return "redirect:/register?success";
		return "login";
	}
	
	
	@GetMapping("/loginInfo")
	@ResponseBody
	public String getLogs() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userService.findUserByEmail(userName);
		log.info(userName);
		log.info(user.getName());
		return user.getName();
	}
	

}
