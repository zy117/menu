package com.haiziguo.recipe.balence;

import java.util.Comparator;

public class Nutrition {
	private Integer index;  //Nutrition index
	private Float percent;	//standard %
	private Float percent2max; // percent to target_max  percent2max = (target+target_max)*100-percent
	
	public Nutrition(Integer index,Float percent){
		this.index = index;
		this.percent = percent;
	}
	public Float getPercent() {
		return percent;
	}
	public void setPercent(Float percent) {
		this.percent = percent;
	}
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	public Float getPercent2max() {
		return percent2max;
	}
	public void setPercent2max(Float percent2max) {
		this.percent2max = percent2max;
	}
}

class SortByPercent2MaxAsc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s1.getPercent2max().compareTo(s2.getPercent2max());
	 }
}

class SortByPercent2MaxDesc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s2.getPercent2max().compareTo(s1.getPercent2max());
	 }
}

class SortByIndexAsc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s1.getIndex().compareTo(s2.getIndex());
	 }
}

class SortByIndexDesc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s2.getIndex().compareTo(s1.getIndex());
	 }
}
class SortByPercentAsc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s1.getPercent().compareTo(s2.getPercent());
	 }
}

class SortByPercentDesc implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Nutrition s1 = (Nutrition) o1;
		 Nutrition s2 = (Nutrition) o2;
		 return s2.getPercent().compareTo(s1.getPercent());
	 }
}
