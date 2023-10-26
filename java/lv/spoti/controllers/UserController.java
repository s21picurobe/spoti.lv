package lv.spoti.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.spoti.helpers.HelperFunctions;
import lv.spoti.models.EmailDetails;
import lv.spoti.models.User;
import lv.spoti.models.UserRole;
import lv.spoti.repos.IUserRepo;
import lv.spoti.services.IEmailService;
import lv.spoti.services.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IEmailService emailService;
	
	@PostMapping("/createuser/create")
	public String createUser(@RequestParam String name, @RequestParam String email, RedirectAttributes redirectAttrs) {
		ArrayList<User> users = (ArrayList<User>) userRepo.findAll();
		for (User user : users) {
			if (user.getEmail().equals(email)) {
				System.out.println("test");
				redirectAttrs.addFlashAttribute("message", "Duplikāts lietotāja epasts");
				return "redirect:/createuser";
			}
		}
		String pwd = HelperFunctions.alphaNumericString(8);
		userService.createUser(name, email, pwd, UserRole.USER);
		emailService.sendSimpleMail(new EmailDetails(email,pwd,"password"));
		return "redirect:/users";
	}
	
	@GetMapping("/createuser")
	public String getCreateUser(Authentication authentication, Model model) {
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "createUser.html";
	}
	
	@GetMapping("/users")
	public String getUsers(Model model, Authentication authentication) {
		model.addAttribute("users", userRepo.findAll());
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "users.html";
	}
	
	@PostMapping("/deleteuser")
	public String deleteUser(Model model, @RequestParam Long id) {
		userService.deleteUserByIdu(id);
		return "redirect:/users";
	}
	
	@GetMapping("/edituser")
	public String getEditUser(Model model, Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		System.out.println( userService.retrieveUserByEmail(authentication.getName()));
		model.addAttribute("user", userService.retrieveUserByEmail(authentication.getName()));
		return "editUser.html";
	}
	
	@PostMapping("/edituser/edit")
	public String editUser(@RequestParam("name") String name, @RequestParam("password") String password, @RequestParam("newpassword") String newPassword, @RequestParam("confirmnewpassword") String confirmNewPassword, Authentication authentication, RedirectAttributes redirectAttrs) {
		if (!(password.equals(userService.retrieveUserByEmail(authentication.getName()).getPassword()))) {
			redirectAttrs.addFlashAttribute("message","Nepareiza parole");
			return "redirect:/edituser";
		}
		if (!(newPassword.equals(confirmNewPassword))) {
			redirectAttrs.addFlashAttribute("message","Paroles nesakrīt");
			return "redirect:/edituser";
		}
		userService.updatePassword(authentication.getName(), newPassword);
		return "redirect:/logout";
	}
}
