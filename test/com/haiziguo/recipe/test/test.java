package com.haiziguo.recipe.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.haiziguo.recipe.balence.Algorithm;
import com.haiziguo.recipe.balence.Food;

public class test {
	
	private final static Float [] STANDARD ={652.5f, 23.06f, 21.75f, 81.56f,337.5f, 5.4f, 5.06f, 258.75f, 0.3f, 0.3f, 30.38f  };

	public static void main(String[] args){
		
		Algorithm algorithm = new Algorithm();
		
		List<Food> food = new ArrayList<Food>();
		Food f1= new Food(2,"饼干",		91,		"饼干",		1500,		12,		435f,		9f,		12.7f,		71.7f,73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f);
		food.add(f1);
		Food f2= new Food(2,"牛奶",		2,		"牛乳",		1225,		120,	54f,		3f,		3.2f,		3.4f,104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f);
		food.add(f2);
		Food f3= new Food(4,"",		-1,		"白砂糖",		1355,		4,		400f,		0f,		0f,			99.9f,20f,	0.6f,	0.06f,	0f,	0f,	0f,	0f);
		food.add(f3);
		Food f4= new Food(4,"",		-1,		"色拉油",		1264,		7,		898f,		0f,		99.8f,		0f,18f,	1.7f,	0.23f,	0f,	0f,	0f,	0f);
		food.add(f4);
		Food f5= new Food(4,"",		-1,		"稻米",		84,			60,		347f,		7.4f,	0.8f,		77.9f,13f,	2.3f,	1.7f,	0f,	0.11f,	0.05f,	0f);
		food.add(f5);
		Food f6= new Food(4,"菠菜油面筋汤",1285,	"油面筋",		103,		5,		493f,		26.9f,	25.1f,		40.4f,29f,	2.5f,	2.29f,	0f,	0.03f,	0.05f,	0f);
		food.add(f6);
		Food f7= new Food(4,"菠菜油面筋汤",1285,	"菠菜(脱水)",	368,		25,		308f,		6.4f,	0.6f,		75.7f,411f,	25.9f,	3.91f,	598f,	0.2f,	0.18f,	82f);
		food.add(f7);
		Food f8= new Food(4,"红烧膳筒",	1276,	"大蒜[蒜头]",	1619,		5,		126f,		4.5f,	0.2f,		27.6f,39f,	1.2f,	0.88f,	5f,	0.04f,	0.06f,	7f);
		food.add(f8);
		Food f9= new Food(4,"红烧膳筒",	1276,	"黄鳝[鳝鱼]",	1076,		75,		89f,		18f,	1.4f,		1.2f,42f,	2.5f,	1.97f,	50f,	0.06f,	0.98f,	0f);
		food.add(f9);
		Food f10= new Food(4,"清炒丝瓜",	1273,	"丝瓜",		552,		110,	21f,		1f,		0.2f,		4.2f,14f,	0.4f,	0.21f,	15f,	0.02f,	0.04f,	5f);
		food.add(f10);
		Food f11= new Food(8,"南瓜粥",	1090,	"优糯米",		111,		10,		345f,		9f,		1f,			75.3f,8f,	0.8f,	1.2f,	0f,	0.1f,	0.03f,	0f);
		food.add(f11);
		Food f12= new Food(8,"南瓜粥",	1090,	"南瓜[倭瓜，番瓜]",551,		50,		23f,		0.7f,	0.1f,		5.3f,16f,	0.4f,	0.14f,	148f,	0.03f,	0.04f,	8f);
		food.add(f12);
		Food f13= new Food(8,"西瓜",	15,		"西瓜",		738,		60,		25f,		0.6f,	0.1f,		5.8f,8f,	0.3f,	0.1f, 75f, 0.02f,	0.03f,	6f);
		food.add(f13);
		
		Date t1 = new Date();
		System.out.println("Init menu:start time="+t1.toString());
		algorithm.InitFoodList(food);
		algorithm.printMenu();
		algorithm.setStandard(STANDARD);
		Float Target [] = {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
		algorithm.setBalanceTarget(Target);
		food = algorithm.doBalance();
		System.out.println("Final balence menu:");
		algorithm.printMenu();
		Date t2 = new Date();
		System.out.println("end time:"+t2.toString());
		System.out.println("cost=" +(t2.getTime()-t1.getTime()));
		
		
	}

}
