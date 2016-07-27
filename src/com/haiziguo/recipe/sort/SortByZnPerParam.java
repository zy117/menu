package com.haiziguo.recipe.sort;

import java.util.Comparator;

import com.haiziguo.recipe.balence.Food;
import com.haiziguo.recipe.util.Define;

public class SortByZnPerParam implements Comparator<Object>{
	private Boolean order;
	private Integer param;
	
	public SortByZnPerParam(Boolean order,Integer param){
		this.order = order;
		this.param = param;
	}
	
	public int compare(Object o1, Object o2) {
		 Food s1 = (Food) o1;
		 Food s2 = (Food) o2;
		 if(s1.getZn()<Define.FLOAT_ZERO || s2.getZn()<Define.FLOAT_ZERO){
			 	if(order)
			 		return s1.getZn().compareTo(s2.getZn());
			 	else
			 		return s2.getZn().compareTo(s1.getZn());
			 }
		 switch(param){
		 	case 0:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getEnergy())).compareTo(((Float)(s2.getZn()/s2.getEnergy())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getEnergy())).compareTo(((Float)(s1.getZn()/s1.getEnergy())));
		 	case 1:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getProtein())).compareTo(((Float)(s2.getZn()/s2.getProtein())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getProtein())).compareTo(((Float)(s1.getZn()/s1.getProtein())));
		 	case 2:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getFat())).compareTo(((Float)(s2.getZn()/s2.getFat())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getFat())).compareTo(((Float)(s1.getZn()/s1.getFat())));
		 	case 3:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getCarbohydrate())).compareTo(((Float)(s2.getZn()/s2.getCarbohydrate())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getCarbohydrate())).compareTo(((Float)(s1.getZn()/s1.getCarbohydrate())));
		 	case 4:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getCa())).compareTo(((Float)(s2.getZn()/s2.getCa())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getCa())).compareTo(((Float)(s1.getZn()/s1.getCa())));
		 	case 5:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getFe())).compareTo(((Float)(s2.getZn()/s2.getFe())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getFe())).compareTo(((Float)(s1.getZn()/s1.getFe())));
		 	case 7:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getVa())).compareTo(((Float)(s2.getZn()/s2.getVa())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getVa())).compareTo(((Float)(s1.getZn()/s1.getVa())));
		 	case 8:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getVb1())).compareTo(((Float)(s2.getZn()/s2.getVb1())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getVb1())).compareTo(((Float)(s1.getZn()/s1.getVb1())));
		 	case 9:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getVb2())).compareTo(((Float)(s2.getZn()/s2.getVb2())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getVb2())).compareTo(((Float)(s1.getZn()/s1.getVb2())));
		 	case 10:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getVc())).compareTo(((Float)(s2.getZn()/s2.getVc())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getVc())).compareTo(((Float)(s1.getZn()/s1.getVc())));
		 	case 11:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getVe())).compareTo(((Float)(s2.getZn()/s2.getVe())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getVe())).compareTo(((Float)(s1.getZn()/s1.getVe())));
		 	case 12:
		 		if(order)
		 			return ((Float)(s1.getZn()/s1.getNa())).compareTo(((Float)(s2.getZn()/s2.getNa())));
		 		else
		 			return ((Float)(s2.getZn()/s2.getNa())).compareTo(((Float)(s1.getZn()/s1.getNa())));
		 	case 6:
		 	default:
		 		if(order)
		 			return s1.getZn().compareTo(s2.getZn());
		 		else
		 			return s2.getZn().compareTo(s1.getZn());
		 			
		 }
	}
}

