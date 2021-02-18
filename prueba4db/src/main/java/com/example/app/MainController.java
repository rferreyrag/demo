package com.example.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path="/demo")
@RestController
public class MainController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email, @RequestParam String pass) {
		User n = new User();
		n.setName(name);
		n.setPass(pass);
		n.setEmail(email);
		userRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	@PutMapping(path="/update/{id}")
	public void updateUserById(@PathVariable String id, 
			@RequestParam String name, @RequestParam String email,
			@RequestParam String pass) {
		
		Integer userId = Integer.parseInt(id);
		Optional<User> ou = userRepository.findById(userId);
		User u = ou.get();
		u.setEmail(email);
		u.setName(name);
		u.setPass(pass);
		userRepository.save(u);
	}
	
	
	@DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {

        Integer userId = Integer.parseInt(id);
        userRepository.deleteById(userId);
    }

}
