package robot;

public class Robot {
	public static void main() {
		System.out.println(r1);
	}
    String name;
	String color;
	int weight;
	
	Robot(String name, String color, int weight) {
		this.name = name;
		this.color = color;
		this.weight = weight;
	}
	
	public void greeting() {
		System.out.println("hello my name is " + this.name);
	}
	
	static Robot r1 = new Robot("tom", "blue", 30);
}
