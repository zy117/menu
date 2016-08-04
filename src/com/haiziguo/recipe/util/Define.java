package com.haiziguo.recipe.util;

public interface Define {
	public final static Integer NUM = 11;
	
	public final static Integer ENERGY = 0;
	public final static Integer PROTEIN = 1;
	public final static Integer FAT = 2;
	public final static Integer CARBOHYDRATE = 3;
	public final static Integer CA = 4;
	public final static Integer FE = 5;
	public final static Integer ZN = 6;
	public final static Integer VA = 7;
	public final static Integer VB1 = 8;
	public final static Integer VB2 = 9;
	public final static Integer VC = 10;
	public final static Integer VE = 11;
	public final static Integer NA = 12;
	
	public final static Integer[] ENERGY_GROUP = {0,1,2,3};
	public final static Integer[] MINERAL_GROUP = {4,5,6,12};
	public final static Integer[] VITAMIN_GROUP = {7,8,9,10,11};
	
	//SYNC table SZBJ_MenuMgr.foodEnergyType
	public final static Integer ENERGY_CEREAL = 1;
	public final static Integer ENERGY_VEGETALITAS = 2;
	public final static Integer ENERGY_BEANS = 3;
	public final static Integer ENERGY_TUBERS = 4;
	public final static Integer ENERGY_ANIMALITAS = 5;
	public final static Integer ENERGY_PUREHEAT = 6;
	
	public final static Integer[] ENERGY_HIGH = {3,5};
	public final static Integer[] ENERGY_LOW = {1,2,4,6};
	public final static Float ENERGY_HIGH_PER = 0.500001f;
	
	public final static Float ENERGY_BALANCE_REMAIN=0.05f;
	
	public final static Float ENERGY_PROTEIN_UP = 0.15f;
	public final static Float ENERGY_PROTEIN_DOWN = 0.125f;
	public final static Float ENERGY_FAT_UP = 0.3f;
	public final static Float ENERGY_FAT_DOWN = 0.25f;
	public final static Float ENERGY_CARBOHYDRATE_UP = 0.6f;
	public final static Float ENERGY_CARBOHYDRATE_DOWN = 0.5f;
	
	//SYNC table SZBJ_MenuMgr.foodtype
	public final static Integer FOOD_CEREAL = 1;
	public final static Integer FOOD_BEANS = 2;
	public final static Integer FOOD_VEGETABLE = 3;
	public final static Integer FOOD_FRUIT = 4;
	public final static Integer FOOD_MEAT = 5;
	public final static Integer FOOD_EGGS = 6;
	public final static Integer FOOD_MILK = 7;
	public final static Integer FOOD_HEAT = 8;
	public final static Integer FOOD_CAKE = 9;
	public final static Integer FOOD_OTHERS = 10;
	
	public final static Boolean ORDER_ASC = true;
	public final static Boolean ORDER_DESC = false;
	
	public final static Float FLOAT_ZERO = 0.000001f;
	
	public final static Integer REDUCE_1G = -1;
	public final static Integer ADD_1G = 1;
	
	public final static Integer ADD_STEP = 1;
	public final static Integer REDUCE_STEP = 3;
	public final static Integer ADD_UP_ALL = 30;
	
	public final static Float [] DEFAULT_TARGET = {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
	public final static Float [] DEFAULT_TARGET_MAX = {1.0f,1.0f,1.0f,1.0f,2.0f,2.0f,2.0f,2.0f,2.0f,2.0f,2.0f};
	public final static Float [] DEFAULT_TARGET_OVER_SHIFT = {0.1f,0.1f,0.1f,0.1f,0.6f,0.6f,0.6f,0.6f,0.6f,0.6f,0.6f};
	public final static Float [] DEFAULT_TARGET_MAX_SHIFT = {0.2f,0.2f,0.2f,0.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f,1.2f};
	
}
