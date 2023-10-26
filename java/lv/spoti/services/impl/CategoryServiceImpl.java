package lv.spoti.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.spoti.models.Category;
import lv.spoti.repos.ICategoryRepo;
import lv.spoti.services.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private ICategoryRepo categoryRepo;

	@Override
	public Category createCategory(String name, String descriptionEng, String descriptionLv, String colorCode) {
		ArrayList<Category> temp = (ArrayList<Category>) categoryRepo.findAll();
		for (Category category: temp) {
			if (category.getName().equals(name)) {
				return null;
			}
		}
		return categoryRepo.save(new Category(name, descriptionEng, descriptionLv, colorCode));
	}

	@Override
	public Category retrieveCategoryByName(String name) {
		return categoryRepo.findCategoryByName(name);
	}

	@Override
	public Category retrieveCategoryByIdc(Long idc) {
		return categoryRepo.findCategoryByIdc(idc);
	}

	@Override
	public boolean updateCategoryByName(String name, String descriptionEng, String descriptionLv, String colorCode) {
		Category category = categoryRepo.findCategoryByName(name);
		if (category != null) {
			category.setDescriptionEng(descriptionEng);
			category.setDescriptionLv(descriptionLv);
			category.setColorCode(colorCode);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateCategoryByIdc(Long idc,String name, String descriptionEng, String descriptionLv, String colorCode) {
		Category category = categoryRepo.findCategoryByIdc(idc);
		ArrayList<Category> temp = (ArrayList<Category>) categoryRepo.findAll();
		for (Category t : temp) {
			if (t.getName().equals(name) && t.getId() != idc) {
				return false;
			}
		}
		if (category != null) {
			category.setName(name);
			category.setDescriptionEng(descriptionEng);
			category.setDescriptionLv(descriptionLv);
			category.setColorCode(colorCode);
			categoryRepo.save(category);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCategoryByName(String name) throws Exception  {
		Category category = categoryRepo.findCategoryByName(name);
		if (!category.getSpots().isEmpty()) {
			throw new Exception("Active relation");
		}
		if (category != null) {
			categoryRepo.delete(category);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteCategoryByIdc(Long idc) throws Exception {
		Category category = categoryRepo.findCategoryByIdc(idc);
		if (!category.getSpots().isEmpty()) {
			throw new Exception("Active relation");
		}
		if (category != null) {
			categoryRepo.delete(category);
			return true;
		}
		return false;
	}
	
	
}
