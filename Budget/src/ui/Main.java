package ui;
import model.*;
import java.util.Scanner;
public class Main{
	private final static int structuralWork = 1300000;
	private final static int finishWork = 2600000;
	private final static int painting = 980000;
	private final static String store1 = "HomeCenter";
	private final static String store2 = "la Ferreteria del centro";
	private final static String store3 = "la Ferreteria del barrio";
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		showData(sc);
	}
	/**
	* Shows the information on screen.
	* <b>pre: </b> <br>
	* <b>post: </b>
	* @param sc scanner that receives the information from the user.
	*/
	public static void showData(Scanner sc){
		System.out.print("Ingrese la cantidad de materiales de la lista: ");
		int listQuantity = sc.nextInt();
		System.out.println("\nIngrese la ubicacion del inmueble (norte, centro o sur): ");
		sc.nextLine();
		String location  = sc.nextLine();
		String[] materialNames = new String[listQuantity];
		String[] materialDestination = new String[listQuantity];
		double[] materialQuantity = new double[listQuantity];
		for(int i = 0; i<materialNames.length; i++){
			materialNames[i] = getMaterialsName(sc,i);
			materialDestination[i] = getMaterialsDestination(sc,materialNames[i]);
			materialQuantity[i] = getMaterialsQuantity(sc,materialNames[i]);
		}
		int[] priceHomeCenter = getPrice(sc,materialNames,store1);
		getTotalPrice(priceHomeCenter,materialQuantity,store1,location);
		int[] priceIronmongeryCenter = getPrice(sc,materialNames,store2);
		getTotalPrice(priceIronmongeryCenter,materialQuantity,store2,location);
		int[] priceIronmongeryDistrict = getPrice(sc,materialNames,store3);
		getTotalPrice(priceIronmongeryDistrict,materialQuantity,store3,location);
		int[] lowerPriceMaterials = getLowerPrices(priceHomeCenter, priceIronmongeryCenter, priceIronmongeryDistrict, materialNames);
		getTotalPrice(lowerPriceMaterials,materialQuantity,"el mejor precio",location);
		getMaterialsByDestination(materialDestination,materialNames,sc);
	}
	/**
	* Imprime la instruccion y recibe el nombre del material. <br>
	* <b>pre: </b> La longitud de la lista que ingresa el usuario debe ser un numero natural. <br>
	* <b>post: </b> Devuelve una cadena.
	* @param sc escaner con el que recibe el nombre del material.
	* @param index posición del material en la lista.
	* @return result nombre del material.
	*/
	public static String getMaterialsName(Scanner sc, int index){
		System.out.println("\nNombre del material No."+(index+1)+":");
		String result = sc.nextLine();
		return result;
	}
	/**
	* Imprime la instruccion y recibe la utilizacion que tendra el material. <br>
	* <b>pre: </b> Cadena valida para el nombre del material. <br>
	* <b>post: </b> Devuelve una cadena.
	* @param sc escaner con el que se recibe la utilización.
	* @param materialNames cadena en la que se guarda el nombre del material.
	* @return result utilización del material.
	*/
	public static String getMaterialsDestination(Scanner sc, String materialNames){
		System.out.println("Utilizacion del '"+materialNames+"' (obra negra, obra blanca o pintura):");
		String result = sc.nextLine();
		return result;
	}
	/**
	* Imprime la instrucción y recibe la cantidad de unidades del material. <br>
	* <b>pre: </b> Cadena valida para el nombre del material. <br>
	* <b>post: </b> Devuelve un numero real.
	* @param sc escaner con el que se recibe la cantidad.
	* @param materialNames cadena en la que se guarda el nombre del material.
	* @return result cantidad que se necesita del material.
	*/
	public static double getMaterialsQuantity(Scanner sc, String materialNames){
		System.out.println("Cantidad que comprara del material '"+materialNames+"' (unidades):");
		double result = sc.nextDouble();
		sc.nextLine();
		return result;
	}
	/**
	* Imprime la instruccion y recibe el precio de cada material en una de las tiendas. <br>
	* <b>pre: </b> Cadenas validas para los nombres de los materiales. <br>
	* <b>post: </b> Devuelve un número entero.
	* @param sc escaner con el que se recibe los precios para cada tienda.
	* @param materials arreglo de cadenas que tiene los nombres de los materiales.
	* @param store cadena contenedora del nombre de la tienda que se recibe el precio.
	* @return resultPrices arreglo de los precios para cada material.
	*/
	public static int[] getPrice(Scanner sc, String[] materials, String store){
		int[] resultPrices = new int[materials.length];
		System.out.println("\n\nIngrese los precios en "+store+":");
		for(int i = 0; i<resultPrices.length; i++){
			System.out.print("Precio por unidad para '"+materials[i]+"': ");
			resultPrices[i] = sc.nextInt();
		}
		return resultPrices;
	}
	/**
	* Imprime los valores del precio de los materiales y el total a pagar por la obra despues de llamar al metodo operations.sumPrices. <br>
	* <b>pre: </b> 
	*         1.Los precios ingresados para cada material debe ser un numero natural (0 si no tienen el material). <br>
	*         2.Las cantidades de unidades de los materiales deben ser mayores a cero. <br>
	*         3.La localizacion del inmueble solo puede ser norte, sur o centro.
	* <b>post: </b> Imprime informacion, que contiene la tienda, el valor total a pagar por los materiales y el valor a pagar por toda la remodelacion.
	* @param prices arreglo con los precios de los materiales.
	* @param quantity unidades que se compraran de los materiales.
	* @param store cadena que contiene el nombre de la tienda.
	* @param location localización del inmueble.
	*/
	public static void getTotalPrice(int[] prices, double[] quantity, String store, String location){
		double subResult = operations.sumPrices(prices,quantity,0,0,0,location);
		double result = operations.sumPrices(prices,quantity,structuralWork,finishWork,painting,location);
		System.out.print("El precio total por los materiales, incluyendo domicilio, si se compra en "+store+" es: $"+subResult+
		"\nSi se incluye mano de obra son: $"+result+"\n");
	}
	/**
	* El metodo imprime el lugar donde es mejor comprar un material y su precio, despues de llamar al metodo operations.lowerPrice. <br>
	* <b>pre: </b> Los precios deben haberse estar inicializados con numeros reales. <br>
	* <b>post: </b> Imprime el lugar donde es mejor comprar cada material y su precio.
	* @param HomeCenter arreglo con los costos de cada material en la tienda HomeCenter.
	* @param IronmongeryCenter arreglo con los costos de cada material en la Ferreteria del centro.
	* @param IronmongeryDistrict arreglo con los costos de cada material en la Ferreteria del barrio.
	* @param materialNames arreglo con los nombres de los materiales.
	* @return result arreglo con los precios mas bajos para cada material.
	*/
	public static int[] getLowerPrices(int[] HomeCenter, int[] IronmongeryCenter, int[] IronmongeryDistrict, String[] materialNames){
		int[] result = new int[HomeCenter.length];
		int low = 0;
		System.out.println("\n");
		for(int i = 0; i<HomeCenter.length; i++){
			low = operations.lowerPrice(HomeCenter[i],IronmongeryCenter[i],IronmongeryDistrict[i]);
			result[i] = low;
			if(low==HomeCenter[i]){
				System.out.println("El mejor lugar para comprar el material '"+materialNames[i]+"' es en HomeCenter a: $"+low);
			}else if(low==IronmongeryCenter[i]){
				System.out.println("El mejor lugar para comprar el material '"+materialNames[i]+"' es en la Ferreteria del centro  a: $"+low);
			}else if(low==IronmongeryDistrict[i]){
				System.out.println("El mejor lugar para comprar el material '"+materialNames[i]+"' es en la Ferreteria del barrio a: $"+low);
			}
		}
		return result;
	}
	/**
	* Print the list by the kind of utilization <br>
	* <b>pre: </b> The use can only be one of the three mentioned <br>
	* <b>post: </b> Print the name of the materials by utilization.
	* @param destination array string with the utilization of each material. 
	* @param materialsNames array string with the names of the materials. 
	* @param sc scanner that go to method printDestination.
	*/
	public static void getMaterialsByDestination(String[] destination, String[] materialsNames, Scanner sc){
		int print = printDestination(sc);
		String[] kindDestination = {"obra blanca", "obra negra", "pintura"};
		System.out.println("\nLos materiales con el tipo de utilizacion "+kindDestination[print-1]+" son:");
		switch(print){
			case 1:
				for(int j=0; j<destination.length; j++){
					if(destination[j].equalsIgnoreCase(kindDestination[0])){
						System.out.println(materialsNames[j]);
					}
				}
				break;
			case 2:
				for(int j=0; j<destination.length; j++){
					if(destination[j].equalsIgnoreCase(kindDestination[1])){
						System.out.println(materialsNames[j]);
					}
				}
				break;
			case 3:
				for(int j=0; j<destination.length; j++){
					if(destination[j].equalsIgnoreCase(kindDestination[2])){
						System.out.println(materialsNames[j]);
					}
				}
				break;
			default:
		}
	}
	/**
	* Receives the kind of utilization that user wants to print the list.
	* <b>pre:</b> <br>
	* <b>post:</b> The option just can be one of the three utilizations.
	* @param sc scanner that receives the option of the user.
	* @return result the option of the user.
	*/
	public static int printDestination(Scanner sc){
		System.out.println("\nIngrese el numero respectivo para imprimir los materiales de la utilizacion: \n1. Obra blanca. \n2. Obra negra. \n3. Pintura");
		int result = sc.nextInt();
		return result;
	}
}