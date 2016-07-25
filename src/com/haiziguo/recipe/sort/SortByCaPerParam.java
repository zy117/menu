package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;

public class SortByCaPerParam implements Comparator<Object>{
	private Boolean order;
	private Integer param;
	
	public SortByCaPerParam(Boolean order,Integer param){
		this.order = order;
		this.param = param;
	}
	
	public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 if(s1.getCa()<0.000001f || s2.getCa()<0.000001f){
		 	if(order)
		 		return s1.getCa().compareTo(s2.getCa());
		 	else
		 		return s2.getCa().compareTo(s1.getCa());
		 }
		 switch(param){
		 	case 0:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getEnergy())).compareTo(((Float)(s2.getCa()/s2.getEnergy())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getEnergy())).compareTo(((Float)(s1.getCa()/s1.getEnergy())));
		 	case 1:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getProtein())).compareTo(((Float)(s2.getCa()/s2.getProtein())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getProtein())).compareTo(((Float)(s1.getCa()/s1.getProtein())));
		 	case 2:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getFat())).compareTo(((Float)(s2.getCa()/s2.getFat())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getFat())).compareTo(((Float)(s1.getCa()/s1.getFat())));
		 	case 3:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getCarbohydrate())).compareTo(((Float)(s2.getCa()/s2.getCarbohydrate())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getCarbohydrate())).compareTo(((Float)(s1.getCa()/s1.getCarbohydrate())));
		 	case 5:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getFe())).compareTo(((Float)(s2.getCa()/s2.getFe())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getFe())).compareTo(((Float)(s1.getCa()/s1.getFe())));
		 	case 6:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getZn())).compareTo(((Float)(s2.getCa()/s2.getZn())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getZn())).compareTo(((Float)(s1.getCa()/s1.getZn())));
		 	case 7:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getVa())).compareTo(((Float)(s2.getCa()/s2.getVa())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getVa())).compareTo(((Float)(s1.getCa()/s1.getVa())));
		 	case 8:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getVb1())).compareTo(((Float)(s2.getCa()/s2.getVb1())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getVb1())).compareTo(((Float)(s1.getCa()/s1.getVb1())));
		 	case 9:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getVb2())).compareTo(((Float)(s2.getCa()/s2.getVb2())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getVb2())).compareTo(((Float)(s1.getCa()/s1.getVb2())));
		 	case 10:
		 		if(order)
		 			return ((Float)(s1.getCa()/s1.getVc())).compareTo(((Float)(s2.getCa()/s2.getVc())));
		 		else
		 			return ((Float)(s2.getCa()/s2.getVc())).compareTo(((Float)(s1.getCa()/s1.getVc())));
		 	case 4:
		 	default:
		 		if(order)
		 			return s1.getCa().compareTo(s2.getCa());
		 		else
		 			return s2.getCa().compareTo(s1.getCa());
		 			
		 }
	}
}

