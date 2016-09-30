package com.haiziguo.recipe.balence;

import com.haiziguo.recipe.util.Define;

public class Params {
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
    
    public Params plusFloat(Float f){
    	Params r = new Params(this.toFloatArray());
    	for(Integer i=0;i<Define.NUM;i++){
    		r.setIndex(i, r.getIndex(i)*f);
    	}
    	return r;
    }
    
    public Params minusParams(Params p){
    	Params r = new Params();
    	for(Integer index=0;index<Define.NUM;index++){
    		r.setIndex(index, this.getIndex(index)-p.getIndex(index));
    	}
    	return r;
    }
    
	public void plusDays(Integer days){
		Integer plus = days;
		if(plus<1)
			plus = 1;
		
		this.energy *= plus;
		this.protein *= plus;
		this.fat *= plus;
		this.carbohydrate *= plus;
		this.ca *= plus;
		this.fe *= plus;
		this.zn *= plus;
		this.va *= plus;
		this.vb1 *= plus;
		this.vb2 *= plus;
		this.vc *= plus;
		this.ve *= plus;
		this.na *= plus;
	}
    
	public Params(){
		
	}
	
    public Params(Float[] array){
    	if(array!=null){
        	for(Integer i=0;i<array.length;i++){
        		setIndex(i,array[i]);
        	}
    	}
    }
    
    public Params(float[] array){
    	for(int i=0;i<array.length;i++){
    		setIndex(i,array[i]);
    	}
    }
    
    public Float[] toFloatArray(){
    	Float [] array = {energy,protein,fat,carbohydrate,ca,fe,zn,va,vb1,vb2,vc,ve,na};
    	return array;
    }
    
    public float[] tofloatArray(){
    	float [] array = {energy,protein,fat,carbohydrate,ca,fe,zn,va,vb1,vb2,vc,ve,na};
    	return array;
    }
    
    public String toString(){
    	return "energy = "+Define.FormatFloat.format(energy)+",\t"+
    		   "protein = "+Define.FormatFloat.format(protein)+",\t"+
    		   "fat = "+Define.FormatFloat.format(fat)+",\t"+
    		   "carbohydrate = "+Define.FormatFloat.format(carbohydrate)+",\t"+
    		   "ca = "+Define.FormatFloat.format(ca)+",\t"+
    		   "fe = "+Define.FormatFloat.format(fe)+",\t"+
    		   "zn = "+Define.FormatFloat.format(zn)+",\t"+
    		   "va = "+Define.FormatFloat.format(va)+",\t"+
    		   "vb1 = "+Define.FormatFloat.format(vb1)+",\t"+
    		   "vb2 = "+Define.FormatFloat.format(vb2)+",\t"+
    		   "vc = "+Define.FormatFloat.format(vc);
    }
    
    public String toStringPer100(){
    	return "energy = "+energy*100+"%,\t"+
    		   "protein = "+protein*100+"%,\t"+
    		   "fat = "+fat*100+"%,\t"+
    		   "carbohydrate = "+carbohydrate*100+"%,\t"+
    		   "ca = "+ca*100+"%,\t"+
    		   "fe = "+fe*100+"%,\t"+
    		   "zn = "+zn*100+"%,\t"+
    		   "va = "+va*100+"%,\t"+
    		   "vb1 = "+vb1*100+"%,\t"+
    		   "vb2 = "+vb2*100+"%,\t"+
    		   "vc = "+vc*100+"%";
    }
    
    public String toStringPer(){
    	return "energy = "+Define.FormatFloat.format(energy)+"%,\t"+
     		   "protein = "+Define.FormatFloat.format(protein)+"%,\t"+
     		   "fat = "+Define.FormatFloat.format(fat)+"%,\t"+
     		   "carbohydrate = "+Define.FormatFloat.format(carbohydrate)+"%,\t"+
     		   "ca = "+Define.FormatFloat.format(ca)+"%,\t"+
     		   "fe = "+Define.FormatFloat.format(fe)+"%,\t"+
     		   "zn = "+Define.FormatFloat.format(zn)+"%,\t"+
     		   "va = "+Define.FormatFloat.format(va)+"%,\t"+
     		   "vb1 = "+Define.FormatFloat.format(vb1)+"%,\t"+
     		   "vb2 = "+Define.FormatFloat.format(vb2)+"%,\t"+
     		   "vc = "+Define.FormatFloat.format(vc)+"%";
    }
    
    public void setZero(){
		this.energy = 0.0f;
		this.protein = 0.0f;
		this.fat = 0.0f;
		this.carbohydrate = 0.0f;
		this.ca = 0.0f;
		this.fe = 0.0f;
		this.zn = 0.0f;
		this.va = 0.0f;
		this.vb1 = 0.0f;
		this.vb2 = 0.0f;
		this.vc = 0.0f;
		this.ve = 0.0f;
		this.na = 0.0f;
    }
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
    
    public void setIndex(Integer index,Float value){
		switch(index){
		case 1:
			protein = value;
			break;
		case 2:
			fat= value;
			break;
		case 3:
			carbohydrate= value;
			break;
		case 4:
			ca= value;
			break;
		case 5:
			fe= value;
			break;
		case 6:
			zn= value;
			break;
		case 7:
			va= value;
			break;
		case 8:
			vb1= value;
			break;
		case 9:
			vb2= value;
			break;
		case 10:
			vc= value;
			break;
		case 11:
			ve= value;
			break;
		case 12:
			na= value;
			break;
		case 0:
		default:
			energy= value;
		}
    }
    
    public void setIndexSum(Integer index,Float value){
		switch(index){
		case 1:
			protein += value;
			break;
		case 2:
			fat += value;
			break;
		case 3:
			carbohydrate += value;
			break;
		case 4:
			ca += value;
			break;
		case 5:
			fe += value;
			break;
		case 6:
			zn += value;
			break;
		case 7:
			va += value;
			break;
		case 8:
			vb1 += value;
			break;
		case 9:
			vb2 += value;
			break;
		case 10:
			vc += value;
			break;
		case 11:
			ve += value;
			break;
		case 12:
			na += value;
			break;
		case 0:
		default:
			energy += value;
		}
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
	public Float getNa() {
		return na;
	}
	public void setNa(Float na) {
		this.na = na;
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
	public Float getVe() {
		return ve;
	}
	public void setVe(Float ve) {
		this.ve = ve;
	}
}
