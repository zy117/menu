package com.haiziguo.recipe.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.haiziguo.recipe.balence.Algorithm;
import com.haiziguo.recipe.balence.Food;
import com.haiziguo.recipe.util.Define;

public class test {
	
	private final static Float [] STANDARD ={652.5f, 23.06f, 21.75f, 81.56f,337.5f, 5.4f, 5.06f, 258.75f, 0.3f, 0.3f, 30.38f  };

	public static void main(String[] args){
		
		List<Food> food = new ArrayList<Food>();
		Food f1= new Food(1,1,2,	"饼干",			91,		"饼干",				1500,		12,		100,	true,		435f,		9f,		12.7f,		71.7f,	73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f1);
		Food f2= new Food(2,1,2,	"牛奶",			2,		"牛乳",				1225,		120,	100,	true,		54f,		3f,		3.2f,		3.4f,	104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f,	Define.FOOD_MILK,		Define.ENERGY_ANIMALITAS);
		food.add(f2);
		Food f3= new Food(3,1,4,	"",				-1,		"白砂糖",			1355,		4,		100,	false,		400f,		0f,		0f,			99.9f,	20f,	0.6f,	0.06f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f3);
		Food f4= new Food(4,1,4,	"",				-1,		"色拉油",			1264,		7,		100,	false,		898f,		0f,		99.8f,		0f,		18f,	1.7f,	0.23f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f4);
		Food f5= new Food(5,1,4,	"",				-1,		"稻米",				84,			60,		100,	true,		347f,		7.4f,	0.8f,		77.9f,	13f,	2.3f,	1.7f,	0f,		0.11f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f5);
		Food f6= new Food(6,1,4,	"菠菜油面筋汤",	1285,	"油面筋",			103,		5,		100,	true,		493f,		26.9f,	25.1f,		40.4f,	29f,	2.5f,	2.29f,	0f,		0.03f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f6);
		Food f7= new Food(7,1,4,	"菠菜油面筋汤",	1285,	"菠菜(脱水)",		368,		25,		100,	true,		308f,		6.4f,	0.6f,		75.7f,	411f,	25.9f,	3.91f,	598f,	0.2f,	0.18f,	82f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f7);
		Food f8= new Food(8,1,4,	"红烧膳筒",		1276,	"大蒜[蒜头]",		1619,		5,		85,		true,		126f,		4.5f,	0.2f,		27.6f,	39f,	1.2f,	0.88f,	5f,		0.04f,	0.06f,	7f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f8);
		Food f9= new Food(9,1,4,	"红烧膳筒",		1276,	"黄鳝[鳝鱼]",		1076,		75,		67,		true,		89f,		18f,	1.4f,		1.2f,	42f,	2.5f,	1.97f,	50f,	0.06f,	0.98f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f9);
		Food f10= new Food(10,1,4,	"清炒丝瓜",		1273,	"丝瓜",				552,		110,	83,		true,		21f,		1f,		0.2f,		4.2f,	14f,	0.4f,	0.21f,	15f,	0.02f,	0.04f,	5f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f10);
		Food f11= new Food(11,1,8,	"南瓜粥",		1090,	"优糯米",			111,		10,		100,	true,		345f,		9f,		1f,			75.3f,	8f,		0.8f,	1.2f,	0f,		0.1f,	0.03f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f11);
		Food f12= new Food(12,1,8,	"南瓜粥",		1090,	"南瓜[倭瓜，番瓜]",	551,		50,		85,		true,		23f,		0.7f,	0.1f,		5.3f,	16f,	0.4f,	0.14f,	148f,	0.03f,	0.04f,	8f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f12);
		Food f13= new Food(13,1,8,	"西瓜",			15,		"西瓜",				738,		60,		56,		true,		25f,		0.6f,	0.1f,		5.8f,	8f,		0.3f,	0.1f, 	75f,	0.02f,	0.03f,	6f,	Define.FOOD_FRUIT,		Define.ENERGY_VEGETALITAS);
		food.add(f13);
		
		Food f21= new Food(14,2,2,	"饼干",			91,		"饼干",				1500,		12,		100,	true,		435f,		9f,		12.7f,		71.7f,	73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f21);
		Food f22= new Food(15,2,2,	"牛奶",			2,		"牛乳",				1225,		120,	100,	true,		54f,		3f,		3.2f,		3.4f,	104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f,	Define.FOOD_MILK,		Define.ENERGY_ANIMALITAS);
		food.add(f22);
		Food f23= new Food(16,2,4,	"",				-1,		"白砂糖",			1355,		6,		100,	false,		400f,		0f,		0f,			99.9f,	20f,	0.6f,	0.06f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f23);
		Food f24= new Food(17,2,4,	"",				-1,		"色拉油",			1264,		8,		100,	false,		898f,		0f,		99.8f,		0f,		18f,	1.7f,	0.23f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f24);
		Food f25= new Food(18,2,4,	"",				-1,		"稻米",				84,			60,		100,	true,		347f,		7.4f,	0.8f,		77.9f,	13f,	2.3f,	1.7f,	0f,		0.11f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f25);
		Food f26 = new Food(19,2,4,	"番茄蛋汤",		748,	"鸡蛋",				1127,		20,		88,		true,		144f,		13.3f,	8.8f,		2.8f,	56f,	2f,		1.1f,	234f,	0.11f,	0.27f,	0f,	Define.FOOD_EGGS,		Define.ENERGY_ANIMALITAS);
		food.add(f26);
		Food f27 = new Food(20,2,4,	"番茄蛋汤",		748,	"番茄[西红柿]",		555,		30,		97,		true,		20f,		0.9f,	0.2f,		4f,		10f,	0.4f,	0.13f,	92f,	0.03f,	0.03f,	19f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f27);
		Food f28 = new Food(21,2,4,	"烂糊白菜",		1287,	"白菜(脱水)",		367,		110,	100,	true,		305f,		6.2f,	0.8f,		72.9f,	908f,	13.8f,	4.68f,	0f,		0.24f,	0f,		187f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f28);
		Food f29 = new Food(22,2,4,	"蜜汁基围虾",	1277,	"基围虾",			1110,		60,		60,		true,		101f,		18.2f,	1.4f,		3.9f,	83f,	2f,		1.18f,	0f,		0.02f,	0.07f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f29);	
		Food f210 = new Food(23,2,8,"",				-1,		"哈蜜瓜",			739,		60,		71,		true,		34f,		0.5f,	0.1f,		7.9f,	4f,		0f,		0.13f,	153f,	0f,		0.01f,	12f,Define.FOOD_FRUIT,		Define.ENERGY_VEGETALITAS);
		food.add(f210);	
		Food f211 = new Food(24,2,8,"葱油花卷",		1279,	"小葱",				543,		2,		73,		true,		27f,		1.6f,	0.4f,		4.9f,	72f,	1.3f,	0.35f,	140f,	0.05f,	0.06f,	21f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f211);
		Food f212 = new Food(25,2,8,"葱油花卷",		1279,	"小麦粉(标准粉)",	118,		35,		100,	true,		249f,		11.2f,	1.5f,		73.6f,	31f,	3.5f,	1.64f,	0f,		0.28f,	0.08f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f212);
		
		Food f31= new Food(26,3,2,	"饼干",			91,		"饼干",				1500,		12,		100,	true,		435f,		9f,		12.7f,		71.7f,	73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f31);
		Food f32= new Food(27,3,2,	"牛奶",			2,		"牛乳",				1225,		120,	100,	true,		54f,		3f,		3.2f,		3.4f,	104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f,	Define.FOOD_MILK,		Define.ENERGY_ANIMALITAS);
		food.add(f32);
		Food f33= new Food(28,3,4,	"",				-1,		"白砂糖",			1355,		4,		100,	false,		400f,		0f,		0f,			99.9f,	20f,	0.6f,	0.06f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f33);
		Food f34= new Food(29,3,4,	"",				-1,		"色拉油",			1264,		5,		100,	false,		898f,		0f,		99.8f,		0f,		18f,	1.7f,	0.23f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f34);
		Food f35= new Food(30,3,4,	"",				-1,		"稻米",				84,			60,		100,	true,		347f,		7.4f,	0.8f,		77.9f,	13f,	2.3f,	1.7f,	0f,		0.11f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f35);
		Food f36 = new Food(31,3,4,	"冬瓜海带汤",	469,	"海带[江白菜]",		444,		10,		100,	true,		13f,		1.2f,	0.1f,		2.1f,	46f,	0.9f,	0.16f,	0f,		0.02f,	0.15f,	0f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f36);
		Food f37 = new Food(32,3,4,	"冬瓜海带汤",	469,	"冬瓜",				547,		30,		80,		true,		12f,		0.4f,	0.2f,		2.6f,	19f,	0.2f,	0.07f,	13f,	0.01f,	0.01f,	18f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f37);
		Food f38 = new Food(33,3,4,	"红烧狮子头",	489,	"猪肉<肥瘦>",		1029,		55,		100,	true,		395f,		13.2f,	37f,		2.4f,	6f,		1.6f,	2.06f,	18f,	0.22f,	0.16f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f38);
		Food f39=new Food(34,3,4,	"清炒西葫芦",	1280,	"西葫芦",			428,		100,	73,		true,		19f,		0.8f,	0.2f,		3.8f,	15f,	0.3f,	0.12f,	5f,		0.01f,	0.03f,	6f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f39);
		Food f310=new Food(35,3,8,	"葡萄",			20,		"葡萄",				716,		60,		86,		true,		44f,		0.5f,	0.2f,		10.3f,	5f,		0.4f,	0.18f,	8f,		0.04f,	0.02f,	25f,Define.FOOD_FRUIT,		Define.ENERGY_VEGETALITAS);
		food.add(f310);
		Food f311=new Food(36,3,8,	"鲜肉小馄饨",	415,	"猪肉<瘦>",			747,		15,		100,	true,		143f,		20.3f,	6.2f,		1.5f,	6f,		3f,		2.99f,	44f,	0.54f,	0.1f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f311);
		Food f312=new Food(37,3,8,	"鲜肉小馄饨",	415,	"小麦粉(标准粉)",	118,		35,		100,	true,		249f,		11.2f,	1.5f,		73.6f,	31f,	3.5f,	1.64f,	0f,		0.28f,	0.08f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f312);	
		
			
		Food f41= new Food(38,4,2,	"饼干",			91,		"饼干",				1500,		12,		100,	true,		435f,		9f,		12.7f,		71.7f,	73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f41);
		Food f42= new Food(39,4,2,	"牛奶",			2,		"牛乳",				1225,		120,	100,	true,		54f,		3f,		3.2f,		3.4f,	104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f,	Define.FOOD_MILK,		Define.ENERGY_ANIMALITAS);
		food.add(f42);
		Food f43= new Food(40,4,4,	"",				-1,		"白砂糖",			1355,		6,		100,	false,		400f,		0f,		0f,			99.9f,	20f,	0.6f,	0.06f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f43);
		Food f44= new Food(41,4,4,	"",				-1,		"色拉油",			1264,		8,		100,	false,		898f,		0f,		99.8f,		0f,		18f,	1.7f,	0.23f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f44);
		Food f45= new Food(42,4,4,	"",				-1,		"稻米",				84,			60,		100,	true,		347f,		7.4f,	0.8f,		77.9f,	13f,	2.3f,	1.7f,	0f,		0.11f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f45);
		Food f46=new Food(43,4,4,	"蘑菇豆腐汤",	322,	"豆腐",				205,		20,		100,	true,		82f,		8.1f,	3.7f,		4.2f,	164f,	1.9f,	1.11f,	0f,		0.04f,	0.03f,	0f, Define.FOOD_BEANS,		Define.ENERGY_BEANS);
		food.add(f46);
		Food f47=new Food(44,4,4,	"蘑菇豆腐汤",	322,	"蘑菇(鲜蘑)",		566,		20,		99,		true,		24f,		2.7f,	0.1f,		4.1f,	6f,		1.2f,	0.92f,	2f,		0.08f,	0.35f,	2f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f47);
		Food f48=new Food(45,4,4,	"蒜蓉空心菜",	1282,	"大蒜[蒜头]",		1619,		2,		85,		true,		126f,		4.5f,	0.2f,		27.6f,	39f,	1.2f,	0.88f,	5f,		0.04f,	0.06f,	7f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f48);
		Food f49=new Food(46,4,4,	"蒜蓉空心菜",	1282,	"雍菜[空心菜,藤藤菜]",536,		95,		76,		true,		23f,		2.2f,	0.3f,		3.6f,	99f,	2.3f,	0.39f,	253f,	0.03f,	0.08f,	25f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f49);
		Food f410=new Food(47,4,4,	"香菇烧鸡",		1281,	"香菇(干)[香蕈，冬菇]",471,		2,		95,		true,		274f,		20f,	1.2f,		61.7f,	83f,	10.5f,	8.57f,	3f,		0.19f,	1.26f,	5f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f410);
		Food f411=new Food(48,4,4,	"香菇烧鸡",		1281,	"鸡",				761,		80,		66,		true,		167f,		19.3f,	9.4f,		1.3f,	9f,		1.4f,	1.09f,	48f,	0.05f,	0.09f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f411);
		Food f412=new Food(49,4,8,	"果酱面包",		41,		"草莓酱",			1478,		5,		100,	true,		270f,		0.8f,	0.2f,		66.3f,	44f,	2.1f,	0.5f,	0f,		0.15f,	0.1f,	1f,	Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f412);
		Food f413=new Food(50,4,8,	"果酱面包",		41,		"面包",				136,		40,		100,	true,		313f,		8.3f,	5.1f,		58.6f,	49f,	2f,		0.75f,	0f,		0.03f,	0.06f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f413);
		Food f414=new Food(51,4,8,	"香蕉",			50,		"香蕉[甘蕉]",		727,		60,		59,		true,		93f,		1.4f,	0.2f,		22f,	7f,		0.4f,	0.18f,	10f,	0.02f,	0.04f,	8f,	Define.FOOD_FRUIT,		Define.ENERGY_VEGETALITAS);
		food.add(f414);
		
		
		Food f51= new Food(52,5,2,	"饼干",			91,		"饼干",				1500,		12,		100,	true,		435f,		9f,		12.7f,		71.7f,	73f,	1.9f,	0.91f,	37f,	0.08f,	0.04f,	3f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f51);
		Food f52= new Food(53,5,2,	"牛奶",			2,		"牛乳",				1225,		120,	100,	true,		54f,		3f,		3.2f,		3.4f,	104f,	0.3f,	0.42f,	24f,	0.03f,	0.14f,	1f,	Define.FOOD_MILK,		Define.ENERGY_ANIMALITAS);
		food.add(f52);
		Food f53= new Food(54,5,4,	"",				-1,		"白砂糖",			1355,		4,		100,	false,		400f,		0f,		0f,			99.9f,	20f,	0.6f,	0.06f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f53);
		Food f54= new Food(55,5,4,	"",				-1,		"色拉油",			1264,		6,		100,	false,		898f,		0f,		99.8f,		0f,		18f,	1.7f,	0.23f,	0f,		0f,		0f,		0f,	Define.FOOD_HEAT,		Define.ENERGY_PUREHEAT);
		food.add(f54);
		Food f55=new Food(56,5,4,	"",				-1,		"猪奶面(硬五花排骨肉)",846,		50,		79,		true,		339f,		13.6f,	30.6f,		2.2f,	6f,		1.3f,	2.2f,	10f,	0.36f,	0.15f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f55);
		Food f56=new Food(57,5,4,	"扁卜骨头胡萝卜汤面",1283,	"胡萝卜(脱水)",	350,		15,		100,	true,		333f,		4.2f,	1.9f,		77.9f,	458f,	8.5f,	1.85f,	2875f,	0.12f,	0.15f,	32f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f56);
		Food f57=new Food(58,5,4,	"扁卜骨头胡萝卜汤面",1283,	"面条",			66,			60,		100,	true,		286f,		8.3f,	0.7f,		61.9f,	11f,	3.6f,	1.43f,	0f,		0.22f,	0.07f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f57);
		Food f58=new Food(59,5,4,	"扁卜骨头胡萝卜汤面",1283,	"长葫芦(瓠瓜)",	416,		100,	87,		true,		14f,		0.7f,	0.1f,		2.7f,	16f,	0.4f,	0.14f,	7f,		0.02f,	0.01f,	11f,Define.FOOD_VEGETABLE,	Define.ENERGY_VEGETALITAS);
		food.add(f58);
		Food f59=new Food(60,5,4,	"红烧鱼丸",		1284,	"鱼丸",				1125,		30,		100,	true,		107f,		11.1f,	1.3f,		12.7f,	97f,	1.2f,	1.59f,	5f,		0.02f,	0.04f,	0f,	Define.FOOD_MEAT,		Define.ENERGY_ANIMALITAS);
		food.add(f59);
		Food f510=new Food(61,5,8,	"",				-1,		"稻米",				84,			20,		100,	true,		347f,		7.4f,	0.8f,		77.9f,	13f,	2.3f,	1.7f,	0f,		0.11f,	0.05f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f510);
		Food f511=new Food(62,5,8,	"甜瓜",			382,	"甜瓜",				671,		60,		78,		true,		27f,		0.4f,	0.1f,		6.2f,	14f,	0.7f,	0.09f,	5f,		0.02f,	0.03f,	15f,Define.FOOD_FRUIT,		Define.ENERGY_VEGETALITAS);
		food.add(f511);
		Food f512=new Food(63,5,8,	"玉米粥",		1190,	"玉米片(即食粥)",	46,			15,		100,	true,		391f,		7.2f,	3.7f,		82.3f,	11f,	9f,		0.44f,	0f,		0.02f,	0.03f,	0f,	Define.FOOD_CEREAL,		Define.ENERGY_CEREAL);
		food.add(f512);
		
		Long total = Runtime.getRuntime().totalMemory();
		System.out.println("total runtime memeory = "+total/8/1024+"KB");
		Long max = Runtime.getRuntime().maxMemory();
		System.out.println("max runtime memeory = "+max/8/1024+"KB");
		Long free = Runtime.getRuntime().freeMemory();
		System.out.println("free runtime memeory = "+free/8/1024+"KB");
		Algorithm algorithm = new Algorithm();
		
		Date t1 = new Date();
		System.out.println("Init menu:start time="+t1.toString());
		algorithm.initFoodList(food);
		//algorithm.printMenu();
		algorithm.setStandard(STANDARD);
		Float Target [] = {0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f,0.8f};
		algorithm.setBalanceTarget(Target);
		food = algorithm.doBalance();
		System.out.println("Final balence menu:");
		algorithm.printMenu();
		Date t2 = new Date();
		System.out.println("end time:"+t2.toString());
		System.out.println("time cost =" +(t2.getTime()-t1.getTime())+"ms");
		Long free1 = Runtime.getRuntime().freeMemory();
		System.out.println("Memory use: "+ (free-free1)/8/1024+"KB");
	}

}
