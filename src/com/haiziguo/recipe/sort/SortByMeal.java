package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;

public class SortByMeal implements Comparator<Object> {
	 public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 return s1.getMeal().compareTo(s2.getMeal());
	 }
}
