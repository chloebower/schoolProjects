/* 
Chloe Bower, student/12162700, 2nd June 2023
CarServiceMenu.java
A program for mechanics to help
organise their work lives
*/

import java.util.Objects;
import java.util.Scanner;

public class CarServiceMenu extends Service
{
	//constants
	final int ENTER_SERVICE = 1;
	final int DISPLAY_SERVICES = 2;
	final int DISPLAY_STATISTICS = 3;
	final int SEARCH_SERVICES = 4;
	final int SORT_SERVICES = 5;
	final int EXIT = 6;
	private final int MAX_SERVICES = 10;

	//array of service objects
	private Service[] Services = new Service[MAX_SERVICES];
	//variable for the current vehicle entered
	private int numServices = 0;
	//declare scanner
	static Scanner scanner = new Scanner(System.in);

	Scanner inputMenuChoice = new Scanner(System.in);


	//create menu
	private int getMenuItem()
	{
		System.out.println("\nPlease select from the following");
		System.out.println(ENTER_SERVICE + ". Enter vehicle service details for today");
		System.out.println(DISPLAY_SERVICES + ". Display all services for today");
		System.out.println(DISPLAY_STATISTICS + ". Display today's statistics");
		System.out.println(SEARCH_SERVICES + ". Search for service by vehicle");
		System.out.println(SORT_SERVICES + ". Sort daily services by time");
		System.out.println(EXIT + ". Exit the application");
		System.out.print("Enter choice==> ");

		String choice = inputMenuChoice.nextLine();
		while (choice.equals("") || !isStringNumeric(choice))
		{
			System.out.println("Error - Menu selection name cannot be blank and must be numeric");

			System.out.print("Enter choice==> ");

			choice = inputMenuChoice.nextLine();
		}

		return Integer.parseInt(choice);
	}

	private boolean isStringNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++)
		{
			if (!Character.isDigit(str.charAt(i)))
				return false;
		}

		return true;
	}

	//create menu methods
	private void processServices()
	{
		int choice = getMenuItem();
		while (choice != EXIT)
		{
			switch (choice)
			{
				case ENTER_SERVICE:
					enterService();
					break;
				case DISPLAY_SERVICES:
					displayAllServices();
					break;
				case DISPLAY_STATISTICS:
					displayStatistics();
					break;
				case SEARCH_SERVICES:
					searchServices();
					break;
				case SORT_SERVICES:
					sortServices();
					break;
				default:
					System.out.println("Choice not recognised");
			}
			choice = getMenuItem();		
		}
		if (choice == EXIT){ //exit message
				System.out.println("Thank you for using Chloe's Mechanic Organisation System!");
				System.out.println("Program created by 12162700");
			}
	}
	
	private void enterService()
	{
		//check if maximum Services has been reached
		if (numServices >= MAX_SERVICES){
			System.out.println("Maximum number of Services reached.");
		}
		//read in the car name
		System.out.print("Enter Car Make and Model: ");
		String car = scanner.nextLine();
		//validation loop
		while (car == ""){
			System.out.print("ERROR car make and model cannot be blank.");
			System.out.print("Enter Car Make and Model: "); //prompt for car name
            car = scanner.nextLine(); //read car name
		}

		//read in the cost
		System.out.print("Enter the amount charged to service the "+car+": $");
		double cost = scanner.nextDouble();
		//validation loop
		while (cost == 0){
			System.out.print("ERROR amount cannot be blank.");
			System.out.print("Enter service cost for the "+car+": $"); //prompt for cost
            cost = scanner.nextDouble(); //read cost
		}

		//read in the time
		System.out.print("Enter the time you will receive the car (24hrs): ");
		int time = scanner.nextInt();
		scanner.nextLine();
		
		//read in the business cost for oil filter
		System.out.print("Enter the business cost for the oil filter: $");
		double filter = scanner.nextDouble();
		scanner.nextLine();

		//read in the business cost for oil
		System.out.print("Enter the business cost for oil: $");
		double oil = scanner.nextDouble();
		scanner.nextLine();

		// adds the data to the array
		Service Service = new Service(car, cost, time, filter, oil);
		displayHeading();

		//displays the Service name, Service ID, mark, grade and the rebate
		System.out.printf("%-15s$%-20.2f%-15d$%-15.2f$%-12.2f\n",
                Service.getServiceCar(), Service.getServiceCost(), Service.getServiceTime(), 
				Service.getServiceFilter(), Service.getServiceOil());
		//increments the current Service variable for the next entry
		Services[numServices] = Service;
		numServices++;
	}

	//create display heading method
	private void displayHeading()
	{
		System.out.printf("%-15s%-20s%-15s%-15s%-12s\n", "Car Name", "Service Cost", "Time",
		"Filter Cost", "Oil Cost");
	}

	private void displayAllServices()
	{
		//checks if there has been a Service entered
		if (numServices == 0){
			System.out.println("No Services have been entered yet.");
		}
		//displays all of the entries entered so far
		displayHeading();
		for (int i = 0; i < numServices; i++){
            Service Service = Services[i];
            System.out.printf("%-15s$%-20.2f%-15d$%-15.2f$%-12.2f\n",
			Service.getServiceCar(), Service.getServiceCost(), Service.getServiceTime(), 
			Service.getServiceFilter(), Service.getServiceOil());
        }
	}

	private void displayStatistics()
	{
		//checks if there has been an Service entered
		if (numServices == 0){
			System.out.println("No vehicles have been entered yet.");
		}
		
		//loops though the current entries in the array to calculate and display the statistics
		for (int i = 0; i < numServices; i++) {

		Service Service = Services[i];
		
		//get variables from student object

		double totalCost = 0;
		double price = 0;
		
		String highestService = Services[0].getServiceCar();
		String lowestService = Services[0].getServiceCar();
	
		double highestPrice = Services[0].getServiceCost();
		double lowestPrice = Services[0].getServiceCost();
        
        price = Service.getServiceCost();
        totalCost += Service.getServiceCost();
		Service.calculateExpenses();
		double expenses = Services[i].getExpenses();

			//sets vehicle and price for highest price
            if (price > highestPrice) {
                highestPrice = price;
				String car = Service.getServiceCar();
				highestService = car;
            }
            
			//sets vehicle and price for lowest price
            if (price < lowestPrice) {
                lowestPrice = price;
				String car = Service.getServiceCar();
				lowestService = car;
            }
		
		//calculations
		double moneyMade = 0;
		double averagePrice = (totalCost/numServices);
		moneyMade = (totalCost - expenses);
		
		//display statistics
		System.out.println("\nDaily Statistics");
		System.out.println(numServices+" services were performed today");
        System.out.println("The "+highestService+" has the most expensive service today costing $" +highestPrice);
        System.out.println("The "+lowestService+" has the cheapest service today costing $" +lowestPrice);
        System.out.println("Average cost per service: $" +averagePrice);
		System.out.println("Total cost of services today $" +totalCost);
		System.out.println("Total expenses today: $"+expenses);
		System.out.println("Total money made today: $"+moneyMade);
		}
	}

	private void searchServices()
	{
		//checks if there has been a Service entered
		if (numServices == 0){
			System.out.println("No Services have been entered yet.");
		}
		//reads the search key (make and model)
		String searchID;
		System.out.print("Enter the Vehicle make and model: ");
		searchID = scanner.nextLine();
		//loop though the current entries in the array to search for the search key
		for (int i = 0; i < numServices; i++) {
            Service Service = Services[i];
			String car = Services[i].getServiceCar();
            if (Objects.equals(searchID, car)) {

		//display the found item or report it has not been found
		displayHeading();
		System.out.printf("%-15s$%-20.2f%-15d$%-15.2f$%-12.2f\n",
		Service.getServiceCar(), Service.getServiceCost(), Service.getServiceTime(), 
		Service.getServiceFilter(), Service.getServiceOil());	
		return;	
			}
		}
		System.out.println(""+searchID+" was not found.");	
		}

	private void sortServices()
	{
		//checks if there has been two services entered
		if (numServices < 2){
			System.out.print("At least two Services must be entered in order to sort.");
		}

        for (int i = 0; i < numServices - 1; i++) {

            for (int j = 0; j < numServices - i - 1; j++) {
				int time1 = Services[j].getServiceTime();
				int time2 = Services[j + 1].getServiceTime();
                //compare elements
				if (time1 > time2){
					//swap times to sort in descending order
					Service temp = Services[j];
					Services[j] = Services[j + 1];
					Services[j + 1] = temp;				
					}
                }
		}
		System.out.println("Services sorted by time");
		displayAllServices();
	}


	public static void main(String [] args)
	{
		CarServiceMenu app = new CarServiceMenu();
		//welcome message
		System.out.println("Welcome to Chloe's Mechanic Organisation Program!");
		//open menu
		app.processServices();
	}
}