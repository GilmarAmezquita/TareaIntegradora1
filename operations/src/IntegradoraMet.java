import java.util.Scanner;
public class IntegradoraMet{
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
	//MAIN
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
		int result = sumPrices(prices,quantity,structuralWork,finishWork,painting,location);
		System.out.print("\nEl precio total a pagar por el trabajo si se compra en "+store+" es: "+result+"\n");
	}
	public static int[] getLowerPrices(int[] HomeCenter, int[] IronmongeryCenter, int[] IronmongeryDistrict, String[] materialNames){
		int[] result = new int[HomeCenter.length];
		int low = 0;
		for(int i = 0; i<HomeCenter.length; i++){
			low = lowerPrice(HomeCenter[i],IronmongeryCenter[i],IronmongeryDistrict[i]);
			if(low==HomeCenter[i]){
				System.out.println("El mejor lugar para comprar el '"+materialNames[i]+"' es en HomeCenter a: $"+low);
			}else if(low==IronmongeryCenter[i]){
				System.out.println("El mejor lugar para comprar el '"+materialNames[i]+"' es en la Ferreteria del centro a: $"+low);
			}else if(low==IronmongeryDistrict[i]){
				System.out.println("El mejor lugar para comprar el '"+materialNames[i]+"' es en la Ferreteria del barrio a: $"+low);
			}
		}
		return result;
	}
	//OPERATIONS
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
		}else if(location.equalsIgnoreCase("sut")){
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