package lv.spoti.services.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lv.spoti.helpers.SpotComparatorClicks;
import lv.spoti.helpers.SpotComparatorDate;
import lv.spoti.models.Category;
import lv.spoti.models.Spot;
import lv.spoti.models.User;
import lv.spoti.repos.ISpotRepo;
import lv.spoti.services.ISpotService;

@Service
public class SpotServiceImpl implements ISpotService{
	@Autowired
	private ISpotRepo spotRepo;

	@Override
	public Spot createSpot(String name, String descriptionEng, String descriptionLv, String city, float latitude,float longitude, Category category, User user) {
		ArrayList<Spot> temp = (ArrayList<Spot>) spotRepo.findAll();
		for (Spot spot: temp) {
			if (spot.getName().equals(name)) {
				return null;
			}
		}
		return spotRepo.save(new Spot(name, descriptionEng, descriptionLv, city, latitude, longitude, category, user));
	}

	@Override
	public Spot retrieveSpotByName(String name) {
		return spotRepo.findSpotByName(name);
	}

	@Override
	public Spot retrieveSpotByIdc(Long ids) {
		return spotRepo.findSpotByIds(ids);
	}

	@Override
	public boolean updateSpotByName(String name, String descriptionEng, String descriptionLv, String city, float latitude, float longitude, Category category, User user) {
		Spot spot = spotRepo.findSpotByName(name);
		if (spot != null) {
			spot.setDescriptionEng(descriptionEng);
			spot.setDescriptionLv(descriptionLv);
			spot.setCity(city);
			spot.setLatitude(latitude);
			spot.setLongitude(longitude);
			spot.setCategory(category);
			spot.setUser(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean updateSpotByIds(Long ids, String name, String descriptionEng, String descriptionLv, String city,float latitude, float longitude, Category category, User user) {
		Spot spot = spotRepo.findSpotByIds(ids);
		ArrayList<Spot> temp = (ArrayList<Spot>) spotRepo.findAll();
		for (Spot s: temp) {
			if (s.getName().equals(name) && s.getId() != ids) {
				return false;
			}
		}
		if (spot != null) {
			spot.setName(name);
			spot.setDescriptionEng(descriptionEng);
			spot.setDescriptionLv(descriptionLv);
			spot.setCity(city);
			spot.setLatitude(latitude);
			spot.setLongitude(longitude);
			spot.setCategory(category);
			spot.setUser(user);
			spotRepo.save(spot);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSpotByName(String name) {
		Spot spot = spotRepo.findSpotByName(name);
		if (spot != null) {
			spotRepo.delete(spot);
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteSpotByIds(Long ids) {
		Spot spot = spotRepo.findSpotByIds(ids);
		if (spot != null) {
			spotRepo.delete(spot);
			return true;
		}
		return false;
	}

	@Override
	public void click(Long idc) {
		Spot spot = retrieveSpotByIdc(idc);
		spot.click();
		spotRepo.save(spot);
	}

	@Override
	public ArrayList<Spot> filterSpots(String categoryName, String sortType, String searchTerm) {
		ArrayList<Spot> temp = (ArrayList<Spot>) spotRepo.findAll();
		ArrayList<Spot> spots = new ArrayList<Spot>(); 
		
		if ((searchTerm.equals(""))) {
			if (categoryName.equals("all")) {
				spots=temp;
			} else {
				for (Spot t : temp) {
					if (t.getCategory().getName().equals(categoryName)) {
						spots.add(t);
					}
				}
			}
		} else {
			if (categoryName.equals("all")) {
				for (Spot t : temp) {
					if (containsString(t.getName(), searchTerm)) {
						spots.add(t);
					}
				}
			} else {
				for (Spot t : temp) {
					if (t.getCategory().getName().equals(categoryName) && containsString(t.getName(), searchTerm)) {
						spots.add(t);
					}
				}
			}
		}
		
		if (sortType.equals("popularity")) {
			spots.sort(new SpotComparatorClicks());
		}
		if (sortType.equals("newest")) {
			spots.sort(new SpotComparatorDate());
		}
	
		return spots;
	}
	
	private boolean containsString(String s1, String s2) {
		for (int i = 0; i < Math.min(s1.length(), s2.length()); i++) {
			System.out.println(s2.charAt(i) + " " + s1.charAt(i));
			if (!(s2.charAt(i) == s1.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
