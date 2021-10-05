package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
@Controller
public class UserController {
	@Autowired
	private UserRepo userRepo;

	@RequestMapping("/")
	public String getAll(Model model) {
		 model.addAttribute("user", new User());
		model.addAttribute("list",  userRepo.findAll());
		return "home";
	}
     
    @PostMapping("/insert")
    public String insert(HttpServletRequest req) {
    	String name = req.getParameter("name");
    	String age = req.getParameter("age");
    	String address = req.getParameter("address");
    	int newAge = Integer.parseInt(age);
    	User user = new User( name, newAge, address);
    	userRepo.save(user);
    	return "redirect:/";
    }
    
    @RequestMapping("/del/{id}")
    public String delete(@PathVariable int id ) {
    	User u = userRepo.findById(id);
    	
    	 userRepo.delete(u);
    	 
    	 return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public User update(@PathVariable int id ) {
    	User u = userRepo.findById(id);
    	 userRepo.save(u);
    	 return  userRepo.save(u);
    }
     
}
