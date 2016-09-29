package com.haiziguo.recipe.util;

public final class FoodType {

	public final static Integer NOT_DEFINED = 0;
	
	public final static Boolean isAdjustable(Integer type){
		switch(type){
			case 17: //动物油
			case 18: //植物油
			case 19: //调味品
				return false;
			default:
				break;
		}
		return true;
	}
	
	public final static Boolean isHighProtein(Integer type){
		switch(type){
			case 107: //动物性
			case 108: //豆类
				return true;
			default:
				break;
		}
		return false;
	}
	
	public final static Integer getMinGram(Integer type){ 
		switch(type){
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
		case 29:
		case 30:
		case 31:
			return 5;
		case 32:
			return 1;
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			return 5;
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
		case 59:
			return 10;
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
		case 65:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
		case 71:
		case 72:
			return 5;
		case 73:
			return 100;
		case 74:
			return 5;
		case 75:
			return 100;
		case 76:
		case 77:
		case 78:
			return 5;
		case 79:
		case 80:
		case 81:
		case 82:
		case 83:
		case 84:
		case 85:
		case 86:
		case 87:
		case 88:
		case 89:
		case 90:
		case 91:
		case 92:
			return 10;
		case 93:
		case 94:
			return 5;
		case 95:
		case 96:
		case 97:
		case 98:
		case 99:
		case 100:
		case 101:
		case 102:
		case 103:
			return 1;
		}
		return 1;
	}
	
	public final static Integer getMaxGram(Integer type){ 
		switch(type){
		case 20:
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
			return 70;
		case 29:
		case 30:
		case 31:
			return 100;
		case 32:
			return 10;
		case 33:
		case 34:
		case 35:
		case 36:
			return 100;
		case 37:
		case 38:
			return 50;
		case 39:
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
			return 100;
		case 45:
		case 46:
		case 47:
		case 48:
		case 49:
		case 50:
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
			return 60;
		case 56:
		case 57:
		case 58:
		case 59:
			return 30;
		case 60:
		case 61:
		case 62:
		case 63:
		case 64:
			return 60;
		case 65:
		case 66:
		case 67:
		case 68:
		case 69:
		case 70:
			return 15;
		case 71:
		case 72:
			return 30;
		case 73:
			return 150;
		case 74:
			return 30;
		case 75:
			return 150;
		case 76:
		case 77:
		case 78:
			return 30;
		case 79:
		case 80:
		case 81:
		case 82:
		case 83:
			return 50;
		case 84:
		case 85:
		case 86:
		case 87:
		case 88:
		case 89:
		case 90:
		case 91:
		case 92:
			return 100;
		case 93:
		case 94:
			return 20;
		case 95:
		case 96:
		case 97:
		case 98:
		case 99:
		case 100:
		case 101:
		case 102:
		case 103:
			return 10;
		}
		return 10;
	}
}
