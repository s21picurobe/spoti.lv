package lv.spoti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.spoti.models.Category;
import lv.spoti.models.Spot;
import lv.spoti.models.UserRole;
import lv.spoti.repos.ICategoryRepo;
import lv.spoti.repos.ISpotRepo;
import lv.spoti.repos.IUserRepo;
import lv.spoti.services.ICategoryService;
import lv.spoti.services.ISpotService;
import lv.spoti.services.IUserService;

@SpringBootApplication
public class SpotiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotiApplication.class, args);
	}
	@Bean
	@Autowired
	public CommandLineRunner testModel(ICategoryService categoryService, ICategoryRepo categoryRepo, ISpotService spotService, ISpotRepo spotRepo, IUserRepo userRepo, IUserService userService) {
		
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				
				System.out.print(System.getProperty("user.dir"));
				
				userService.createUser("Roberts Pičukans", "s21picurobe@venta.lv", "parole", UserRole.ADMIN);
				userService.createUser("Marta Kalāce", "s21kalamart@venta.lv", "parole", UserRole.ADMIN);
				
				categoryService.createCategory("Downhill", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				categoryService.createCategory("Pumptrack", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				categoryService.createCategory("Skatepark", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				categoryService.createCategory("BMX race", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				categoryService.createCategory("Trials", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				categoryService.createCategory("Dirtjump", "Lorem Ipsum", "Lorem Ipsum", "#bd89f0");
				
				/*spotService.createSpot("TMDH", "Lorem Ipsum", "Lorem Ipsum", "Tukums", 56.9f, 23.1f, categoryRepo.findCategoryByName("Downhill"), userService.retrieveUserByEmail("s21picurobe@venta.lv"));
				spotService.createSpot("Riekstukalns DH", "Lorem Ipsum", "Lorem Ipsum", "Baldone", 57.9f, 23.1f, categoryRepo.findCategoryByName("Downhill"),  userService.retrieveUserByEmail("s21picurobe@venta.lv"));
				spotService.createSpot("TMDJ", "Lorem Ipsum", "Lorem Ipsum", "Tukums", 56.9f, 23.2f, categoryRepo.findCategoryByName("Dirtjump"),  userService.retrieveUserByEmail("s21picurobe@venta.lv"));
				spotService.createSpot("Baldones Pumptrack", "Lorem Ipsum", "Lorem Ipsum", "Baldone", 57.9f, 24.1f, categoryRepo.findCategoryByName("Pumptrack"),  userService.retrieveUserByEmail("s21picurobe@venta.lv"));
				spotService.createSpot("Talsu skeitparks", "Lorem Ipsum", "Lorem Ipsum", "Talsi", 55.9f, 23.1f, categoryRepo.findCategoryByName("Skatepark"), userService.retrieveUserByEmail("s21picurobe@venta.lv"));
				*/
			}		
		};
	}

}
