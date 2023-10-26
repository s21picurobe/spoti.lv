package lv.spoti.helpers;

import java.util.Comparator;

import lv.spoti.models.Spot;

public class SpotComparatorDate implements Comparator<Spot> {

	@Override
	public int compare(Spot s1, Spot s2)
	  {
	     if (s1.getDate().isAfter(s2.getDate())) {
	    	 return -1;
	     } else {
	    	 return 1;
	     }
	  }

}