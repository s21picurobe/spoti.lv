package lv.spoti.controllers;

import java.io.File;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lv.spoti.models.Category;
import lv.spoti.models.Spot;
import lv.spoti.models.User;
import lv.spoti.repos.ICategoryRepo;
import lv.spoti.repos.ISpotRepo;
import lv.spoti.services.ICategoryService;
import lv.spoti.services.ISpotService;
import lv.spoti.services.IUserService;

@Controller
public class SpotController {
	
	@Autowired
	private ISpotRepo spotRepo;
	
	@Autowired
	private ISpotService spotService;
	
	@Autowired
	private ICategoryRepo categoryRepo;
	
	@Autowired
	private ICategoryService categoryService;
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping("/allspots")
	public String getSpots(Authentication authentication, Model model){
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "spotsForUser.html";
	}
	@GetMapping("/")
	public String getIndex(Model model, Authentication authentication){
		model.addAttribute("categories", categoryRepo.findAll());
		model.addAttribute("spots", spotRepo.findAll());
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "index.html";
	}
	
	@ResponseBody
	@GetMapping("/all")
	public ArrayList<Spot> getAll(){
		return (ArrayList<Spot>)spotRepo.findAll();
	}
	
	@GetMapping("/createspot")
	public String getCreateSpot(Model model, Authentication authentication){
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "createSpot.html";
	}
	
	@PostMapping("/createspot/create")
	public String createSpot(@RequestParam("name") String name, @RequestParam("description-eng") String descriptionEng, @RequestParam("description-lv") String descriptionLv, @RequestParam("city") String city, @RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude, @RequestParam("category") String categoryName, @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttrs, Authentication authentication) throws IOException {
		Spot newSpot = spotService.createSpot(name, descriptionEng, descriptionLv, city, latitude, longitude, categoryRepo.findCategoryByName(categoryName), userService.retrieveUserByEmail(authentication.getName()));
		redirectAttrs.addFlashAttribute("message", "Kļūda: spota duplikāts");
		if (newSpot != null) {
			String uploadDirectory = System.getProperty("user.dir")+"/src"+"/main"+"/resources"+"/uploads";
			java.nio.file.Path fileNameAndPath = Paths.get(uploadDirectory, "s" + newSpot.getId() + "." + ((String)(image.getOriginalFilename())).split("\\.")[1]);
			Files.write(fileNameAndPath, image.getBytes());
			return "redirect:/";
		}
		System.out.println();
		return "redirect:/createspot";
	}
	
	@GetMapping("/deletespot")
	public String deleteSpot(@RequestParam("id") long id) throws IOException {
		spotService.deleteSpotByIds(id);
		File image = new File(System.getProperty("user.dir")+"/src"+"/main"+"/resources"+"/uploads"+"/s"+id+".jpg");
		image.delete();
		return "redirect:/allspots";
	}
	
	@GetMapping("/editspot")
	public String editSpot(@RequestParam("id") long id, Model model, Authentication authentication) throws IOException {
		model.addAttribute("spot", spotService.retrieveSpotByIdc(id));
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "editSpot.html";
	}
	
	@PostMapping("/editspot/edit")
	public String editSpot(@RequestParam("id") long id, @RequestParam("name") String name, @RequestParam("description-eng") String descriptionEng, @RequestParam("description-lv") String descriptionLv, @RequestParam("city") String city, @RequestParam("latitude") float latitude, @RequestParam("longitude") float longitude, @RequestParam("category") String categoryName, @RequestParam("image") MultipartFile image, RedirectAttributes redirectAttrs, Authentication authentication) throws IOException {
		if(!(spotService.updateSpotByIds(id, name, descriptionEng, descriptionLv, city, latitude, longitude, categoryService.retrieveCategoryByName(categoryName), userService.retrieveUserByEmail(authentication.getName())))) {
			redirectAttrs.addFlashAttribute("message", "Duplikāts spota nosaukums");
			return "redirect:/editspot?id=" + id;
		}
		if (!image.isEmpty()) {
			System.out.println("test");
			String uploadDirectory = System.getProperty("user.dir")+"/src"+"/main"+"/resources"+"/uploads";
			java.nio.file.Path fileNameAndPath = Paths.get(uploadDirectory, "s" + id + "." + ((String)(image.getOriginalFilename())).split("\\.")[1]);
			Files.write(fileNameAndPath, image.getBytes());
		}
		return "redirect:/allspots";
	}
	
	@GetMapping("/createcategory")
	public String getCreateCategory(Model model, Authentication authentication){
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "createCategory.html";
	}

	@ResponseBody
	@GetMapping("/get/categories")
	public ArrayList<Category> getCategories(){
		return (ArrayList<Category>)categoryRepo.findAll();
	}
	
	@PostMapping("/createcategory/create")
	public String createCategory(@RequestParam("name") String name, @RequestParam("description-eng") String descriptionEng, @RequestParam("description-lv") String descriptionLv, @RequestParam("color-code") String colorCode, RedirectAttributes redirectAttrs) throws IOException {
		Category newCategory = categoryService.createCategory(name, descriptionEng, descriptionLv, colorCode);
		if (newCategory != null) {
			return "redirect:/";
		}
		redirectAttrs.addFlashAttribute("message", "Duplikāts kategorijas nosaukums");
		return "redirect:/createcategory";
	}
	
	@PostMapping("/deletecategory")
	public String deleteCategory(@RequestParam("id") long id, RedirectAttributes redirectAttrs) throws IOException {
		try {
			categoryService.deleteCategoryByIdc(id);
		} catch (Exception e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
			return "redirect:/allcategories";
		}
		return "redirect:/allcategories";
	}
	
	@GetMapping("/editcategory")
	public String getEditCategory(@RequestParam("id") Long id, Model model, Authentication authentication) {
		model.addAttribute("category", categoryService.retrieveCategoryByIdc(id));
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		return "editCategory.html";
	}
	
	@PostMapping("/editcategory/edit")
	public String editCategory(@RequestParam("id") Long id, @RequestParam("name") String name, @RequestParam("descriptionlv") String descriptionLv, @RequestParam("descriptioneng") String descriptionEng, @RequestParam("colorcode") String colorCode,  Model model, RedirectAttributes redirectAttrs) {
		if (categoryService.updateCategoryByIdc(id, name, descriptionEng, descriptionLv, colorCode)) {
			return "redirect:/allcategories";
		}
		System.out.println(categoryService.updateCategoryByIdc(id, name, descriptionEng, descriptionLv, colorCode));
		redirectAttrs.addFlashAttribute("message", "Duplikāts kategorijas nosaukums");
		return "redirect:/editcategory?id=" + id;
	}
	
	@GetMapping("/allcategories")
	public String editCategory(Model model, Authentication authentication) {
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		model.addAttribute("categories", categoryRepo.findAll());
		return "categoriesForUser.html";
	}
	
	@GetMapping("/spot")
	public String getSpot(@RequestParam("id") Long id, Model model, Authentication authentication) {
		spotService.click(id);
		System.out.println(id);
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		model.addAttribute("spot",spotService.retrieveSpotByIdc(id));
		return "spot.html";
	}
	
	@GetMapping("/filterspots")
	public String filterSpots(Authentication authentication,@RequestParam("category") String categoryName, @RequestParam("sorttype") String sortType, @RequestParam("searchterm") String searchTerm, Model model) {
		model.addAttribute("categories", categoryRepo.findAll());
		if (authentication != null) {
			model.addAttribute("role", userService.retrieveUserByEmail(authentication.getName()).getRole().toString());
		} else {
			model.addAttribute("role", "visitor");
		}
		model.addAttribute("spots", spotService.filterSpots(categoryName, sortType, searchTerm));
		return "index.html";
	}
}
