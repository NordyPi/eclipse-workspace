package nordlab03;

public class Lab03Driver {

	public static void main(String[] args) {
		PizzaOrder order1 = new PizzaOrder();
		
		Pizza pizza1 = new Pizza(PizzaSize.LARGE, 1, 30, 0);
		Pizza pizza2 = new Pizza(PizzaSize.SMALL, 2, 0, 2);
		
		
		order1.addPizza(pizza1);
		order1.addPizza(pizza2);
		
		order1.printOrder();
		
		PizzaOrder order2 = new PizzaOrder();
		
		Pizza pizza3 = new Pizza(PizzaSize.SMALL, 2, 1, 0);
		Pizza pizza4 = new Pizza(PizzaSize.MEDIUM, 2, 3, 0);
		Pizza pizza5 = new Pizza(PizzaSize.LARGE, 1, 4, 2);
		
		order2.addPizza(pizza3);
		order2.addPizza(pizza4);
		order2.addPizza(pizza5);
		
		order2.printOrder();
	
		PizzaOrder order3 = order2;
		order3.printOrder();
		
		order1.changePizzaToppings(1, 5, 5, 5);
		order1.printOrder();
		
		order2.changePizzaToppings(2, 7, 7, 7);
		order2.printOrder();
		order3.printOrder();
		
		
	}

}
