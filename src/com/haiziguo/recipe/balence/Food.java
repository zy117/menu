package com.haiziguo.recipe.balence;

import java.util.Map;

import com.haiziguo.recipe.util.Define;
import com.haiziguo.recipe.util.FoodType;

public class Food {
	private Integer id;
    private Integer day;			//  week
    private Integer meal;			//	mealTimes
    
    
    private String menuName;
    private Integer menuId;
    private String foodName;
    private Integer foodId;
    private Integer gram;
    private Integer add_gram; 		//克数上限
    private Integer reduce_gram; 	//克数下限


    private Integer foodPart = 100;
    private Boolean isAdjustable = true;
    
    private Integer type1;			//主分类
    private Integer type2;			//子分类
    private Integer type3;			//能量分类
    
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
    
    /**
     * 获取单一营养素每克摄入量
     * @param index
     * @return
     */
    public Float getIndexIntakePerGram(Integer index){
    	return foodPart.floatValue()*getIndex(index)/10000.0f;
    }
    
    /**
     * 获取单一营养素每克摄入量
     * @param index
     * @return
     */
    public Float getIndexIntake(Integer index){
    	return foodPart.floatValue()*gram.floatValue()*getIndex(index)/10000.0f;
    }
    
    /**
     * 获取单一营养素含量
     * @param index
     * @return
     */
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
    
    @SuppressWarnings("unchecked")
	public Food(Integer id, Object obj){
    	this.id = id;
    	Map<String,Object> map = (Map<String, Object>) obj;
    	this.day = (Integer) map.get("week");
    	this.meal = (Integer) map.get("mealTimes");
    	this.menuId = (Integer) map.get("menuId");
    	this.menuName = map.get("menuName").toString();
    	this.foodId = (Integer) map.get("foodId");
    	this.foodName = map.get("foodName").toString();
    	this.foodPart = (Integer) map.get("foodpart");
    	this.energy = (Float) map.get("energy");
    	this.protein = (Float) map.get("energy");
    	this.fat = (Float) map.get("fats");
    	this.carbohydrate = (Float) map.get("carbohydrate");
    	this.ca = (Float) map.get("ca");
    	this.fe = (Float) map.get("fe");
    	this.zn = (Float) map.get("zn");
    	this.va = (Float) map.get("vitaminA");
    	this.vb1 = (Float) map.get("vitaminB1");
    	this.vb2 = (Float) map.get("vitaminB2");
    	this.vc = (Float) map.get("vitaminC");
    	
    	this.gram = ((Float)map.get("foodQuan")).intValue();
    	this.type1 = (Integer)map.get("typeMainId");
    	this.type2 = (Integer)map.get("typeSubId");
    	this.type3 = (Integer)map.get("typeEnergyId");
    	
    	this.isAdjustable = FoodType.isAdjustable(type1);
    	this.add_gram = FoodType.getMaxGram(type2);
    	this.reduce_gram = FoodType.getMinGram(type2);
    }
    
    public Food(Integer id, Object[] obj 
    		){
    	this.id = id;
    	this.day = Integer.parseInt(((java.sql.Timestamp)obj[0]).toString().substring(8, 10).trim());
    	this.meal = (Integer)obj[1];
    	this.menuName = (String)obj[2];
    	this.menuId = (Integer)obj[5];
    	this.foodName = (String)obj[3];
    	this.foodId = (Integer)obj[6];
    	this.gram = ((Double)obj[4]).intValue();
    	this.setFoodPart((Integer)obj[8]);
    	this.setAdd_gram(gram);
    	this.setReduce_gram(gram>>1);

    	this.isAdjustable = true;
    	this.energy = ((Double)obj[9]).floatValue();
    	this.protein = ((Double)obj[10]).floatValue();
    	this.fat = ((Double)obj[11]).floatValue();
    	this.carbohydrate = ((Double)obj[12]).floatValue();
    	this.ca = ((Double)obj[13]).floatValue();
    	this.fe = ((Double)obj[14]).floatValue();
    	this.zn = ((Double)obj[15]).floatValue();
    	this.va = ((Double)obj[16]).floatValue();
    	this.vb1 = ((Double)obj[17]).floatValue();
    	this.vb2 = ((Double)obj[18]).floatValue();
    	this.vc = ((Double)obj[19]).floatValue();
    	this.ve = ((Double)obj[20]).floatValue();
    	this.na = ((Double)obj[21]).floatValue();
    	this.type1 = (Integer)obj[22];
    	this.type3 = (Integer)obj[23];
    	if(Define.CANNOT_ADD_OR_REDUCE.contains(obj[24])){
    		this.isAdjustable = false;
    	}else{
    		this.isAdjustable = true;
    	}
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
     * @param ve 维生素E，单位（/100g）
     * @param na 钠，单位（/100g）
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
    		,Integer type1
    		,Integer energytype 
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
    	this.setAdd_gram(((gram<Define.ADD_UP_ALL)?(gram<<1):(gram+Define.ADD_UP_ALL)));
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
    	this.type1 = type1;
    	this.type3 = energytype;
    }
    
    public String toMenu(){
    	return " "+id+"\t"+day+"\t"+meal+"\t"+(menuName==null?menuName:(menuName.length()>6?menuName.substring(0, 5):menuName))+"\t"+foodId+"\t"+(foodName==null?foodName:(foodName.length()>6?foodName.substring(0, 5):foodName))+"\t"+gram;
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
    			+ "na:"+na+","
    			+ "va:"+va+","
    			+ "vb1:"+vb1+","
    			+ "vb2:"+vb2+","
    			+ "vc:"+vc+","
    			+ "ve:"+ve+","
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
		if(gram<1){
			return 1;
		}
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
		if(add_gram<1){
			return 1;
		}
		return add_gram;
	}

	public void setAdd_gram(Integer add_gram) {
		this.add_gram = add_gram;
	}

	public Integer getReduce_gram() {
		if(reduce_gram<1){
			return 1;
		}
		return reduce_gram;
	}

	public void setReduce_gram(Integer reduce_gram) {
		if(reduce_gram<1){
			this.reduce_gram = 1;
		}else{
			this.reduce_gram = reduce_gram;
		}
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