package nordlab03;


/**
*
* Created: Sat Mar 15 2009
*
* @author Kenrick Mock
* @version 1
* 
*          Modified Sat Jan 9, 2016
* @author Susanne Sherba
* @version 1.1
* 
*           Modified Sat Jan 12, 2019
* @author FT Liu
* @version 1.2
*/

public class PizzaOrder {

	private static final int MAX_NUM_PIZZAS = 5;
	private int numPizzas;
	private Pizza[] pizzas;


	public PizzaOrder() {
		pizzas = new Pizza[MAX_NUM_PIZZAS];
		numPizzas = 0;
	}
	
	//copy constructor
	public PizzaOrder(PizzaOrder other) {
		this.numPizzas = other.numPizzas;
		this.pizzas = new Pizza[other.pizzas.length];
		
		for (int i = 0; i < other.numPizzas; i++) {
			this.pizzas[i] = new Pizza(other.pizzas[i]);
		}
	}

	/*
	 * Adds a pizza to the order
	 */
	public void addPizza(Pizza p) {
		if (numPizzas >= MAX_NUM_PIZZAS) {
			System.out.println("Too many pizzas for this order");
		} else {
			pizzas[numPizzas] = p;
			numPizzas++;
		}

	}

	/*
	 * returns the number of pizzas in the order
	 * 
	 */

	public int getNumPizzas() {
		return numPizzas;
	}

	/*
	 * Changes the toppings on a specific pizza precondition: 0 <= pizzaNumber <
	 * numPizzas
	 * 
	 */
	public void changePizzaToppings(int pizzaNumber, int numCheeseToppings, int numHamToppings,
			int numPepperoniToppings) {
		if (pizzaNumber < 0 || pizzaNumber > numPizzas) {
			System.out.println("Invalid pizza number");
		} else {
			pizzas[pizzaNumber].setNumCheeseToppings(numCheeseToppings);
			pizzas[pizzaNumber].setNumHamToppings(numHamToppings);
			pizzas[pizzaNumber].setNumPepperoniToppings(numPepperoniToppings);
		}
	}

	/*
	 * Prints the entire pizza order
	 */
	public void printOrder() {
		for (int i = 0; i < numPizzas; i++) {
			System.out.println(pizzas[i]);
		}
		System.out.println("Order Total: $" + calcTotal());
		System.out.println();
	}

	/*
	 * Calculates the total cost of an order
	 */
	public double calcTotal() {
		double total = 0;
		for (int i = 0; i < numPizzas; i++) {
			total += pizzas[i].calcCost();
		}
		return total;
	}

}
