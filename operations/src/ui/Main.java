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
	
	public static void showData(Scanner sc){
		System.out.print("Ingrese la cantidad de materiales de la lista: ");
		int listQuantity = sc.nextInt();
		System.out.println("\nIngrese la ubicacion del inmueble (norte, centro o sur): ");
		sc.nextLine();
		String location  = sc.nextLine();
		String[] materialNames = new String[listQuantity];
		String[] materialDestination = new String[listQuantity];
		int[] materialQuantity = new int[listQuantity];
		for(int i = 0; i<materialNames.length; i++){
			materialNames[i] = getMaterialsName(sc,i);
			materialDestination[i] = getMaterialsDestination(sc,materialNames[i]);
			materialQuantity[i] = getMaterialsQuantity(sc,materialNames[i]);
		}
		int[] priceHomeCenter = getPrice(sc,materialNames,store1);
		getTotalPrice(priceHomeCenter,materialQuantity,store1,structuralWork,finishWork,painting,location);
		int[] priceIronmongeryCenter = getPrice(sc,materialNames,store2);
		getTotalPrice(priceIronmongeryCenter,materialQuantity,store2,structuralWork,finishWork,painting,location);
		int[] priceIronmongeryDistrict = getPrice(sc,materialNames,store3);
		getTotalPrice(priceIronmongeryDistrict,materialQuantity,store3,structuralWork,finishWork,painting,location);
		int[] lowerPriceMaterials = getLowerPrices(priceHomeCenter, priceIronmongeryCenter, priceIronmongeryDistrict, materialNames);
		getTotalPrice(lowerPriceMaterials,materialQuantity,"el mejor precio",structuralWork,finishWork,painting,location);
		getMaterialsByDestination(materialDestination,materialNames);
	}
	
	public static String getMaterialsName(Scanner sc, int index){
		System.out.println("\nNombre del material No."+(index+1)+":");
		String result = sc.nextLine();
		return result;
	}
	
	public static String getMaterialsDestination(Scanner sc, String materialNames){
		System.out.println("Utilizacion del '"+materialNames+"' (obra blanca u obra negra):");
		String result = sc.nextLine();
		return result;
	}
	
	public static int getMaterialsQuantity(Scanner sc, String materialNames){
		System.out.println("Cantidad que comprara del material '"+materialNames+"' (unidades):");
		int result = sc.nextInt();
		sc.nextLine();
		return result;
	}
	
	public static int[] getPrice(Scanner sc, String[] materials, String store){
		int[] resultPrices = new int[materials.length];
		System.out.println("\n\nIngrese los precios en "+store+":");
		for(int i = 0; i<resultPrices.length; i++){
			System.out.print("Precio por unidad para '"+materials[i]+"': ");
			resultPrices[i] = sc.nextInt();
		}
		return resultPrices;
	}
	
	public static void getTotalPrice(int[] prices,int[] quantity, String store,int structuralWork,int finishWork,int painting, String location){
		int subResult = Operations.sumPrices(prices,quantity,0,0,0,location);
		int result = Operations.sumPrices(prices,quantity,structuralWork,finishWork,painting,location);
		System.out.print("El precio total a pagar por los materiales, incluyendo domicilio, si se compra en "+store+" es: $"+subResult+
		"\nSi se incluye mano de obra son: $"+result+"\n");
	}
	
	public static int[] getLowerPrices(int[] HomeCenter, int[] IronmongeryCenter, int[] IronmongeryDistrict, String[] materialNames){
		int[] result = new int[HomeCenter.length];
		int low = 0;
		System.out.println("\n");
		for(int i = 0; i<HomeCenter.length; i++){
			low = Operations.lowerPrice(HomeCenter[i],IronmongeryCenter[i],IronmongeryDistrict[i]);
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
	
	public static void getMaterialsByDestination(String[] destination, String[] materialsNames){
		String[] kindDestination = {"obra blanca", "obra negra"};
		for(int i = 0; i<kindDestination.length; i++){
			System.out.println("\nLos materiales con el tipo de utilizacion '"+kindDestination[i]+"' son:");
			for(int j = 0; j<destination.length; j++){
				if(destination[j].equalsIgnoreCase(kindDestination[i])){
					System.out.println(materialsNames[j]);
				}
			}
		}
	}
}