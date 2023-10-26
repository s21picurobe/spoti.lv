package lv.spoti.services;

import java.util.ArrayList;

import lv.spoti.models.Category;
import lv.spoti.models.Spot;
import lv.spoti.models.User;

public interface ISpotService {
	Spot createSpot(String name, String descriptionEng, String descriptionLv, String city, float latitude, float longitude, Category category, User user);
	Spot retrieveSpotByName(String name);
	Spot retrieveSpotByIdc(Long idc);
	boolean updateSpotByName(String name, String descriptionEng, String descriptionLv, String city, float latitude, float longitude, Category category, User user);
	boolean updateSpotByIds(Long ids, String name, String descriptionEng, String descriptionLv, String city, float latitude, float longitude, Category category, User user);
	boolean deleteSpotByName(String name);
	boolean deleteSpotByIds(Long idc);
	ArrayList<Spot> filterSpots(String categoryName, String sortType, String searchTerm);
	void click(Long idc);
}
