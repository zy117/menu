package com.haiziguo.recipe.balence;

import java.util.Date;

public class Food {
    private Date date;
    private Integer meal;
    private String menuName;
    private Integer menuId;
    private String foodName;
    private Integer gram;
    private Integer add_gram;
    private Integer reduce_gram;
    private Integer foodId;

    private Integer isFromMarket;
    
    private Integer type1;
    private Integer type2;
    private Integer type3;
    
    private Float energy=0.0f;
    private Float protein=0.0f;
    private Float fat=0.0f;
    private Float carbohydrate=0.0f;
    
    private Float ca=0.0f;
    private Float fe=0.0f;
    private Float zn=0.0f;
    
    private Float va=0.0f;
    private Float vb1=0.0f;
    private Float vb2=0.0f;
    private Float vc=0.0f;
    
	public Float getIndex(Integer index){
		switch(index){
		case 1:
			return protein;
		case 2:
			return fat;
		case 3:
			return carbohydrate;
		case 4:
			return ca;
		case 5:
			return fe;
		case 6:
			return zn;
		case 7:
			return va;
		case 8:
			return vb1;
		case 9:
			return vb2;
		case 10:
			return vc;
		case 0:
		default:
			return energy;
		}
	}
    
    public Food(){
    	
    }
    
    public Food(
    		Integer meal
    		,String menuName
    		,Integer menuId
    		,String foodName
    		,Integer foodId
    		,Integer gram
    		,Float energy
    		,Float protein
    		,Float fat
    		,Float carbohydrate
    		,Float ca
    		,Float fe
    		,Float zn
    		,Float va
    		,Float vb1
    		,Float vb2
    		,Float vc
    		){
    	this.meal = meal;
    	this.menuName = menuName;
    	this.menuId = menuId;
    	this.foodName = foodName;
    	this.foodId = foodId;
    	this.gram = gram;
    	this.setAdd_gram(gram<<1);
    	this.setReduce_gram(gram>>1);
    	this.energy = energy;
    	this.protein = protein;
    	this.fat = fat;
    	this.carbohydrate = carbohydrate;
    	this.ca = ca;
    	this.fe = fe;
    	this.zn = zn;
    	this.va = va;
    	this.vb1 = vb1;
    	this.vb2 = vb2;
    	this.vc = vc;
    }
    
    public String toMenu(){
    	return " "+meal+"\t"+menuName+"\t"+foodName+"\t"+gram;
    }
    public String toString(){
    	return "Food={"
    			+ "mealmark:"+meal+","
    			+ "menuName:"+menuName+","
    			+ "menuId:"+menuId+","
    			+ "foodName:"+foodName+","
    			+ "gram:"+gram+","
    			+ "foodId:"+foodId+","
    			+ "type1:"+type1+","
    			+ "type2:"+type2+","
    			+ "type3:"+type3+","
    			+ "energy:"+energy+","
    			+ "protein:"+protein+","
    			+ "fat:"+fat+","
    			+ "carbohydrate:"+carbohydrate+","
    			+ "ca:"+ca+","
    			+ "fe:"+fe+","
    			+ "zn:"+zn+","
    			+ "va:"+va+","
    			+ "vb1:"+vb1+","
    			+ "vb2:"+vb2+","
    			+ "vc:"+vc+","
    			+ "}";
    }
    
    
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getMeal() {
		return meal;
	}
	public void setMeal(Integer meal) {
		this.meal = meal;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Integer getGram() {
		return gram;
	}
	public void setGram(Integer gram) {
		this.gram = gram;
	}
	public Integer getFoodId() {
		return foodId;
	}
	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}
	public Integer getIsFromMarket() {
		return isFromMarket;
	}
	public void setIsFromMarket(Integer isFromMarket) {
		this.isFromMarket = isFromMarket;
	}
	public Integer getType1() {
		return type1;
	}
	public void setType1(Integer type1) {
		this.type1 = type1;
	}
	public Integer getType2() {
		return type2;
	}
	public void setType2(Integer type2) {
		this.type2 = type2;
	}
	public Integer getType3() {
		return type3;
	}
	public void setType3(Integer type3) {
		this.type3 = type3;
	}
	public Float getEnergy() {
		return energy;
	}
	public void setEnergy(Float energy) {
		this.energy = energy;
	}
	public Float getProtein() {
		return protein;
	}
	public void setProtein(Float protein) {
		this.protein = protein;
	}
	public Float getFat() {
		return fat;
	}
	public void setFat(Float fat) {
		this.fat = fat;
	}
	public Float getCarbohydrate() {
		return carbohydrate;
	}
	public void setCarbohydrate(Float carbohydrate) {
		this.carbohydrate = carbohydrate;
	}
	public Float getCa() {
		return ca;
	}
	public void setCa(Float ca) {
		this.ca = ca;
	}
	public Float getFe() {
		return fe;
	}
	public void setFe(Float fe) {
		this.fe = fe;
	}
	public Float getZn() {
		return zn;
	}
	public void setZn(Float zn) {
		this.zn = zn;
	}
	public Float getVa() {
		return va;
	}
	public void setVa(Float va) {
		this.va = va;
	}
	public Float getVb1() {
		return vb1;
	}
	public void setVb1(Float vb1) {
		this.vb1 = vb1;
	}
	public Float getVb2() {
		return vb2;
	}
	public void setVb2(Float vb2) {
		this.vb2 = vb2;
	}
	public Float getVc() {
		return vc;
	}
	public void setVc(Float vc) {
		this.vc = vc;
	}

	public Integer getAdd_gram() {
		return add_gram;
	}

	public void setAdd_gram(Integer add_gram) {
		this.add_gram = add_gram;
	}

	public Integer getReduce_gram() {
		return reduce_gram;
	}

	public void setReduce_gram(Integer reduce_gram) {
		this.reduce_gram = reduce_gram;
	}
    
}




