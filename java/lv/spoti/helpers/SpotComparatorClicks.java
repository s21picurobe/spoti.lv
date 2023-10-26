package lv.spoti.helpers;

import java.util.Comparator;

import lv.spoti.models.Spot;

public class SpotComparatorClicks implements Comparator<Spot> {

	@Override
	public int compare(Spot s1, Spot s2)
	  {
	     if (s1.getClicks() > s2.getClicks()) {
	    	 return -1;
	     } else {
	    	 return 1;
	     }
	  }

}
