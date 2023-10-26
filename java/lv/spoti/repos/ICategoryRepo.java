package lv.spoti.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import lv.spoti.models.Category;

public interface ICategoryRepo extends CrudRepository<Category, Long> {
	Category findCategoryByIdc(long idc);
	Category findCategoryByName(String name);
	List<Category> findAll();
}
