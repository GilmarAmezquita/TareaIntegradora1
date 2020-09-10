package model;
public class Operations{
	
	public static int sumPrices(int[] prices,int[] quantity, int structuralWork, int finishWork,int painting, String location){
		int result = structuralWork+finishWork+painting;
		for(int i =0; i<prices.length; i++){
			result+=(prices[i]*quantity[i]);
		}
		result += shippingPrice(result,location);
		return result;
	}
	
	public static int shippingPrice(int result, String location){
		if(location.equalsIgnoreCase("norte")){
			if(result<80000){
				result = 120000;
			}else if(result<300000){
				result = 28000;
			}else result = 0;
		}else if(location.equalsIgnoreCase("centro")){
			if(result<80000){
				result = 50000;
			}else result = 0;
		}else if(location.equalsIgnoreCase("sur")){
			if(result<80000){
				result = 120000;
			}else if(result<300000){
				result = 55000;
			}else result = 0;
		}
		return result;
	}
	
	public static int lowerPrice(int HomeCenter, int IronmongeryCenter, int IronmongeryDistrict){
		int result = 0;
		int[] price = {HomeCenter, IronmongeryCenter, IronmongeryDistrict};
		for(int i=0; i<price.length; i++){
			if(price[i]<price[result]){
				result = i;
			}
		}
		result = price[result];
		return result;
	}
}