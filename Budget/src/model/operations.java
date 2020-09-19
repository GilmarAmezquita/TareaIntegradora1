package model;
public class operations{
	/**
	* Sum for: <br>
	*		1. Cost to pay for the materials. <br>
	*		2. Total cost for remodeling.
	* <b>pre: </b>
	*			  1. The prices must be natural numbers. <br>
	*			  2. The units to be purchased for each material must be greater than zero. <br>
	*             3. The locations of the property must be norte, centro o sur.
	* <b>post: </b>
	* @param prices array of the prices.
	* @param quantity array of units to be purchased for each material.
	* @param structuralWork labor cost for structuralWork.
	* @param finishWork labor cost for finishWork.
	* @param painting labor cost for painting.
	* @param location location of the property
	* @return result total sum of prices.
	*/
	public static double sumPrices(int[] prices,double[] quantity, int structuralWork, int finishWork,int painting, String location){
		double result = 0;
		for(int i =0; i<prices.length; i++){
			result+=(prices[i]*quantity[i]);
		}
		result += shippingPrice(result,location);
		result += structuralWork+finishWork+painting;
		return result;
	}
	/*
	* Compare the information to get the shipping price.
	* <b>pre: </b> The locations of the property must be norte, centro o sur. <br>
	* <b>post: </b> Return the price for shipping.
	* @param sum Cost of the materials withouth shipping.
	* @param location location of the property.
	* @return result price for shipping.
	*/
	public static int shippingPrice(double sum, String location){
		int result = 0;
		if(location.equalsIgnoreCase("norte")){
			if(sum<80000){
				result = 120000;
			}else if(sum<300000){
				result = 28000;
			}else result = 0;
		}else if(location.equalsIgnoreCase("centro")){
			if(sum<80000){
				result = 50000;
			}else result = 0;
		}else if(location.equalsIgnoreCase("sur")){
			if(sum<80000){
				result = 120000;
			}else if(sum<300000){
				result = 55000;
			}else result = 0;
		}
		return result;
	}
	/**
	* Check which store has the best price for a material. <br>
	* <b>pre: </b> Receive prices in natural numbers for the same material. <br>
	* <b>post: </b> Return the lowest price, greater than zero.
	* @param HomeCenter material price in the HomeCenter store.
	* @param IronmongeryCenter material price in the ironmongery of center.
	* @param IronmongeryDistrict material price in the ironmongery of the district.
	* @return result material lowest price.
	*/
	public static int lowerPrice(int HomeCenter, int IronmongeryCenter, int IronmongeryDistrict){
		int result = 0;
		int[] price = {HomeCenter, IronmongeryCenter, IronmongeryDistrict};
		for(int i=0; i<price.length; i++){
			if(price[i]<price[result] && price[i]>0){
				result = i;
			}
		}
		result = price[result];
		return result;
	}
}