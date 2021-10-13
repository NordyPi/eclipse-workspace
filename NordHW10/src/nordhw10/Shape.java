package nordhw10;

import java.awt.Color;

public abstract class Shape {
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
	
	public abstract double area();
	
}

class Circle extends Shape {
	private double radius;
	
	public Circle(double x, double y, Color c, double r) {
		super(x, y, c);
		this.radius = r;
	}
	
	public double area() {
		return Math.PI * this.radius * this.radius;
	}
}