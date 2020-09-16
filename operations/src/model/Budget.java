package model;
public class Budget{
	/**
	* Realiza la suma para la informacion suministrada por el usuario. Precio a pagar por los materiales y el precio total por la remodelación.
	* <b>pre: </b>
	*			  1. Los precios deben ser validos, numeros naturales.<br>
	*			  2. Las unidades que se compran de cada material deben ser mayores a cero. <br>
	*             3. La ubicación del inmueble debe ser estrictamente norte, centro o sur.
	* <b>post: </b>
	* @param prices arreglo de los precios que ingresa.
	* @param quantity arreglo de  unidades que se compraran por cada material.
	* @param structuralWork costo de mano de obra por la obra negra.
	* @param finishWork costo de mano de obra por la obra blanca.
	* @param painting costo de mano de obra por la pintura.
	* @param location ubicación del inmueble.
	* @return result total de la suma de los precios.
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
	* Compara la informacion obtenida para devolver el costo de domicilio correspondiente.
	* <b>pre: </b> La ubicacion del inmueble debe ser estrictamente norte, centro o sur. <br>
	* <b>post: </b> Devuelve el costo correcto del domicilio de la compra de los materiales.
	* @param sum valor de los materiales a comprar sin incluir el domicilio.
	* @param location ubicación del inmueble.
	* @return result valor del domicilio.
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
	* Verifica que tienda ofrece el mejor precio. <br>
	* <b>pre: </b> Recibe precios en números naturales de un mismo material. <br>
	* <b>post: </b> El resultado debe ser el mas bajo, mayor a cero.
	* @param HomeCenter precio del material en la tienda HomeCenter.
	* @param IronmongeryCenter precio del material en la Ferreteria del centro.
	* @param IronmongeryDistrict precio del material en la Ferreteria del barrio.
	* @return result menor precio del material mayor a cero.
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