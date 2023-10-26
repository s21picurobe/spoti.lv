package lv.spoti.services;

import lv.spoti.models.Category;

public interface ICategoryService {
	Category createCategory(String name, String descriptionEng, String descriptionLv, String colorCode);
	Category retrieveCategoryByName(String name);
	Category retrieveCategoryByIdc(Long idc);
	boolean updateCategoryByName(String name, String descriptionEng, String descriptionLv, String colorCode);
	boolean updateCategoryByIdc(Long idc, String name, String descriptionEng, String descriptionLv, String colorCode);
	boolean deleteCategoryByName(String name) throws Exception;
	boolean deleteCategoryByIdc(Long idc) throws Exception;
}
