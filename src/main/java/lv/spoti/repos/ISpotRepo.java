package lv.spoti.repos;

import org.springframework.data.repository.CrudRepository;

import lv.spoti.models.Spot;

public interface ISpotRepo extends CrudRepository<Spot, Long> {
	Spot findSpotByIds(long ids);
	Spot findSpotByName(String name);
}
