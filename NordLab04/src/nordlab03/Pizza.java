package nordlab03;


/**
 * Pizza class that stores information about a single pizza.
 * @author Kenrick Mock
 * @version 1 Modified Sat Jan 9, 2016
 * @author Susanne Sherba
 * @version 1.1 Modified Sat Jan 12, 2019
 * @author FT Liu
 * @version 1.2
 */

enum PizzaSize {SMALL, MEDIUM, LARGE};

public class Pizza {

	private PizzaSize size;
	private int numCheeseToppings;
	private int numHamToppings;
	private int numPepperoniToppings;

	// no argument constructor
	public Pizza() {
		size = PizzaSize.LARGE;
		numCheeseToppings = 1;
		numHamToppings = 0;
		numPepperoniToppings = 0;
	}
	
	public Pizza(Pizza other) {
		this.size = other.size;
		this.numCheeseToppings = other.numCheeseToppings;
		this.numHamToppings = other.numHamToppings;
		this.numPepperoniToppings = other.numPepperoniToppings;
	}

	/*
	 * Constructor takes 4 arguments: size of pizza, num of cheese toppings, 
	 * num of ham toppings, num of pepperoni toppings
	 */
	public Pizza(PizzaSize pizzaSize, int cheese, int ham, int pepperoni) {
		if (!(pizzaSize == PizzaSize.SMALL || pizzaSize == PizzaSize.MEDIUM || pizzaSize == PizzaSize.LARGE)) {
			// if size is invalid, set size to SMALL
			size = PizzaSize.SMALL;
		} else {
			size = pizzaSize;
		}
		numCheeseToppings = cheese;
		numHamToppings = ham;
		numPepperoniToppings = pepperoni;
	}


	public PizzaSize getSize() {
		return size;
	}

	public int getNumCheeseToppings() {
		return numCheeseToppings;
	}

	public int getNumHamToppings() {
		return numHamToppings;
	}

	public int getNumPepperoniToppings() {
		return numPepperoniToppings;
	}

	public void setNumCheeseToppings(int cheese) {
		numCheeseToppings = cheese;
	}

	public void setNumHamToppings(int ham) {
		numHamToppings = ham;
	}

	public void setNumPepperoniToppings(int pepperoni) {
		numPepperoniToppings = pepperoni;
	}

	/* 
	 * Calculates the cost of the pizza
	 */
	public double calcCost() {
		double baseCost = 10;
		if (size.equals(PizzaSize.SMALL)) {
			baseCost = 10;
		} else if (size.equals(PizzaSize.MEDIUM)) {
			baseCost = 12;
		} else if (size.equals(PizzaSize.LARGE)) {
			baseCost = 14;
		} else {
			System.out.println("Error, unknown size.");
			return 0;
		}
		return baseCost + (numHamToppings + numCheeseToppings + numPepperoniToppings) * 2;
	}

	public String toString() {
		return "Size: " + size + ", Cheese Toppings: " + numCheeseToppings + " Pepperoni Toppings: "
				+ numPepperoniToppings + " Ham Toppings: " + numHamToppings + ". Cost: " + calcCost();
	}

}
