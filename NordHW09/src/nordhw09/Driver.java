package nordhw09;

import java.awt.Color;

public class Driver {

	public static int countHighestNum(int[] array) {
		int highest = 0;
		int amount = 0;
		
		for(int item : array) {
			if(item > highest) {
				highest = item;
				amount = 1;
			}
			else if (item == highest) {
				amount ++;
			}
		}
		
		return amount;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {3, 17, 0, 9, 5, 6, 9, 4, 1, 2, 3, 2, 9, 12, 17};
		
		System.out.println(Driver.countHighestNum(arr));
	}
}

class Shape {
	private double xCenter;
	private double yCenter;
	private Color color;
	
	public Shape(double x, double y, Color c)
	{
		this.xCenter = x;
		this.yCenter = y;
		this.color = c;
	}
	
	// copy constructor:
	public Shape(Shape otherShape) {
		if (otherShape != null) {
			this.xCenter = otherShape.xCenter;
			this.yCenter = otherShape.yCenter;
			this.color = otherShape.color;
		}
	}

}

class Circle extends Shape {
	private double radius;
	
	public Circle(double x, double y, Color c, double r) {
		super(x, y, c);
		this.radius = r;
	}
	
	public Circle(Circle otherCircle) {
		super(otherCircle);
		if(otherCircle != null) {
			this.radius = otherCircle.radius;
		}
	}
}