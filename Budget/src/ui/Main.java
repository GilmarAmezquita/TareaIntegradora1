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
	* Prints the instruction and receives the name of the material. <br>
	* <b>pre: </b> The length of the list that the user enters must be a natural number. <br>
	* <b>post: </b> Return a string with the material name.
	* @param sc scanner that receives the material name.
	* @param index position of the material in the array
	* @return result material name
	*/
	public static String getMaterialsName(Scanner sc, int index){
		System.out.println("\nNombre del material No."+(index+1)+":");
		String result = sc.nextLine();
		return result;
	}
	/**
	* Prints the instruction and receives the utilization of the material. <br>
	* <b>pre: </b> Valid string for the material name: materialNames <br>
	* <b>post: </b> Return a string with the material utilization.
	* @param sc scanner that receives the material utilization.
	* @param materialNames string that saves the name of the material.
	* @return result material utilization.
	*/
	public static String getMaterialsDestination(Scanner sc, String materialNames){
		System.out.println("Utilizacion del '"+materialNames+"' (obra negra, obra blanca o pintura):");
		String result = sc.nextLine();
		return result;
	}
	/**
	* Prints the instruction and receives the quantity of units for material. <br>
	* <b>pre: </b> Valid string for the material name: materialNames <br>
	* <b>post: </b> Return a real number with the quantity of material units.
	* @param sc scanner that receives the quantity.
	* @param materialNames String that saves the name of the material.
	* @return result quantity of units required of the material.
	*/
	public static double getMaterialsQuantity(Scanner sc, String materialNames){
		System.out.println("Cantidad que comprara del material '"+materialNames+"' (unidades):");
		double result = sc.nextDouble();
		sc.nextLine();
		return result;
	}
	/**
	* Prints the instruction and receives the price of each material in one of the three stores. <br>
	* <b>pre: </b> Valid array of strings for the materials name: materials<br>
	* <b>post: </b> Return an array with natural numbers.
	* @param sc scanner that receives the price for each material.
	* @param materials array of strings that saves the materials names.
	* @param store string with the name of the store.
	* @return resultPrices array with the price of each material in a store.
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
	* Prints the cost of the materials and the total cost of the entire remodel. <br>
	* <b>pre: </b> <br>
	*         1. The prices of the materials must be natural numbers (0 if the store doesn't have the material). <br>
	*         2. The quantity of units must be greater than zero. <br>
	*         3. The location of the property must be norte, sur o centro.
	* <b>post: </b> Prints the total cost for the materials and the cost for the entire remodel.
	* @param prices array with the price for each material where the method takes the prices.
	* @param quantity array with the units required of each material.
	* @param store string that saves the place where the method takes the prices.
	* @param location location of the property.
	*/
	public static void getTotalPrice(int[] prices, double[] quantity, String store, String location){
		double subResult = operations.sumPrices(prices,quantity,0,0,0,location);
		double result = operations.sumPrices(prices,quantity,structuralWork,finishWork,painting,location);
		System.out.print("El precio total por los materiales, incluyendo domicilio, si se compra en "+store+" es: $"+subResult+
		"\nSi se incluye mano de obra son: $"+result+"\n");
	}
	/**
	* Prints the location where is better buy the material and its price. <br>
	* <b>pre: </b> The prices must be real numbers <br>
	* <b>post: </b> Prints where it's best to buy each material and its price.
	* @param HomeCenter array with the price of each material in HomeCenter store.
	* @param IronmongeryCenter array with the price of each material in the downtown ironmongery.
	* @param IronmongeryDistrict array with the price of each material in district ironmongery.
	* @param materialNames array with the name of the materials.
	* @return result array with the lowest price for each material.
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
	* Prints the list by the kind of utilization <br>
	* <b>pre: </b> The use can only be one of the three mentioned <br>
	* <b>post: </b> Print the name of the materials by utilization.
	* @param destination array string with the utilization of each material. 
	* @param materialsNames array string with the names of the materials. 
	* @param sc scanner that go to method printDestination.
	*/
	public static void getMaterialsByDestination(String[] destination, String[] materialsNames, Scanner sc){
		int print = printDestination(sc);
		String[] kindDestination = {"obra blanca", "obra negra", "pintura"};
		System.out.println("\nLos materiales con el tipo de utilizacion '"+kindDestination[print-1]+"' son:");
		for(int j=0; j<destination.length; j++){
			if(destination[j].equalsIgnoreCase(kindDestination[print-1])){
				System.out.println(materialsNames[j]);
			}
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