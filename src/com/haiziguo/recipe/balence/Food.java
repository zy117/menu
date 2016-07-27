package com.haiziguo.recipe.balence;

public class Food {
	private Integer id;
    private Integer day;
    private Integer meal;
    private String menuName;
    private Integer menuId;
    private String foodName;
    private Integer gram;
    private Integer add_gram;
    private Integer reduce_gram;
    private Integer foodId;

    private Integer foodPart = 100;
    private Boolean isAdjustable = true;
    
    private Integer type1;
    private Integer type2;
    private Integer type3;
    
    private Float energy=0.0f;
    private Float protein=0.0f;
    private Float fat=0.0f;
    private Float carbohydrate=0.0f;
    
    private Float na=0.0f;
    private Float ca=0.0f;
    private Float fe=0.0f;
    private Float zn=0.0f;
    
    private Float va=0.0f;
    private Float vb1=0.0f;
    private Float vb2=0.0f;
    private Float vc=0.0f;
    private Float ve=0.0f;
    
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
		case 11:
			return ve;
		case 12:
			return na;
		case 0:
		default:
			return energy;
		}
	}
    
    public Food(){
    	
    }
    
    /**
     * @author zhangy@mywayinfo.com
     * @version 0.0.1
     * @param energy 能量，单位（千卡/100g）
     * @param protein 蛋白质，单位（克/100g）
     * @param fat 脂肪，单位（克/100g)
     * @param carbohydrate 碳水化合物，单位（克/100g)
     * @param ca 钙，单位（毫克/100g)
     * @param fe 铁，单位（毫克/100g)
     * @param zn 锌，单位（毫克/100g)
     * @param va 维生素A，单位（微克/100g)
     * @param vb1 维生素B1，单位（毫克/100g)
     * @param vb2 维生素B2，单位（毫克/100g)
     * @param vc 维生素C，单位（克/100g)
     * 
     */
    public Food(
    		Integer id
    		,Integer day
    		,Integer meal
    		,String menuName
    		,Integer menuId
    		,String foodName
    		,Integer foodId
    		,Integer gram
    		,Integer foodPart
    		,Boolean isAdjustable
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
    	this.id = id;
    	this.day = day;
    	this.meal = meal;
    	this.menuName = menuName;
    	this.menuId = menuId;
    	this.foodName = foodName;
    	this.foodId = foodId;
    	this.gram = gram;
    	this.setFoodPart(foodPart);
    	this.setAdd_gram(gram<<1);
    	this.setReduce_gram(gram>>1);
    	this.isAdjustable = isAdjustable;
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
    	return " "+id+"\t"+day+"\t"+meal+"\t"+menuName+"\t"+foodId+"\t"+foodName+"\t"+gram;
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

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIsAdjustable() {
		return isAdjustable;
	}

	public void setIsAdjustable(Boolean isAdjustable) {
		this.isAdjustable = isAdjustable;
	}

	public Integer getFoodPart() {
		return foodPart;
	}

	public void setFoodPart(Integer foodPart) {
		this.foodPart = foodPart;
	}

	public Float getNa() {
		return na;
	}

	public void setNa(Float na) {
		this.na = na;
	}

	public Float getVe() {
		return ve;
	}

	public void setVe(Float ve) {
		this.ve = ve;
	}
    
}




