package product_homework;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Product {
//	A product should be an object created from a class. Each category should be its own type. Each product should have the base properties of:
//		name
//		quantity
//		serial number
//		cost
//		sell price
//		Other features to think about:
//		Add "sell by" dates to each product
//		Have it so the manager can see how many items need to be sold within the week.
//		List it out by product.
//		Determine how much each product cost the store to purchase each product could bring in in revenue
//		each product would make in profit
//		Create the ability to put an item on sale, or take an item off sale. Determine the sale discount.
	private String name;
	private int quantity;
	private String serialNumber;
	private float cost;
	private float sellPrice;
	private int sellByDate;
	private boolean onSale;
	private float onSalePrice;
	// formats numbers;
	DecimalFormat dFormat = new DecimalFormat("$#.##");

	public Product(String name, int quantity, String serialNumber, float cost, float sellPrice, int sellByDate,
			boolean onSale) {
		this.name = name;
		this.quantity = quantity;
		this.serialNumber = serialNumber;
		this.cost = cost;
		this.sellPrice = sellPrice;
		this.sellByDate = sellByDate;
		this.onSale = onSale;
	}

// start of getters:
	public String getName() {
		return "item name; " + name;
	}

	public String getQuantity() {
		return "quantity : " + quantity;
	}

	public String getSerialNumber() {
		return "serial number : " + serialNumber;
	}

	public String getCost() {
		return "cost of item : " + checkAccess(numFormat(cost));
	}

	public String getSellPrice() {
		if (onSale == false) {
			return "regular sale price : " + numFormat(sellPrice);
		} else {
			return "price of item on sale : " + numFormat(onSalePrice);
		}

	}

	public String getSellByDate() {
		return "sell by date : " + sellByDate;
	}

	public String getOnSale() {
		return "item on sale : " + onSale;
	}

	// end of getters

	// start of setters
	public Product setName(String updateName) {
		this.name = updateName;
		System.out.println("name has been updated");
		return this;
	}

	public Product setQuantity(int count) {
		this.quantity = count;
		System.out.println("Quantity has been updated");
		return this;
	}

	public Product setSerialNumber(String serial) {
		this.serialNumber = serial;
		System.out.println("Serial Number has been updated");
		return this;
	}

	public Product setSellPrice(int price) {
		checkAccess(this.sellPrice = price);
		System.out.println("Sell Price has been updated");
		return this;
	}

	public Product setSellByDate(int date) {
		checkAccess(this.sellByDate = date);
		System.out.println("Sell by date has been updated");
		return this;
	}

	public Product setOnSale(boolean sale, double percent) {
		// if item is on sale, reduce price by percent;
		if (sale == true) {
			checkAccess(this.onSale = sale);
			checkAccess(this.onSalePrice = saleDownByPercent(sellPrice, percent));
			System.out.println("on Sale has been updated");
			return this;
		} else {
			// if item is not on sale, price up by percent
			checkAccess(this.onSale = sale);
			checkAccess(this.sellPrice = sellPrice);
			System.out.println("on Sale has been updated");
			return this;
		}

	}
	// end of setters

	// this method checks to make sure the user name and password is correct;
	public Object checkAccess(Object func) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your user name (manager)");
		String userName = scanner.nextLine();
		System.out.println("Enter your password (password)");
		String userPw = scanner.nextLine();

		if (userName.equals("manager") && userPw.equals("password")) {
			System.out.println(func);// testing purposes;
			return func;
		} else {
			System.out.println(denied());// delete after testing;
			return denied();
		}
	}

	// returns numbers formatted into dollars;
	public static String numFormat(double price) {
		String formatNum = String.format("$%.2f", price);
		return formatNum;
	}

	// sell price down by percent
	public float saleDownByPercent(float sellPrice, double percent) {
		double newSellPrice = sellPrice - (sellPrice * (percent * .01));
		return (float) newSellPrice;
	}

	// sell price up by percent
	public float saleUpByPercent(float sellPrice, double percent) {
		double newSellPrice = sellPrice + (sellPrice * (percent * .01));
		return (float) newSellPrice;
	}

	// returns a message when user is denied;
	public String denied() {
		return "Access denied";
	}

	static Product cheese = new Product("cheese", 5, "123cheese", 1.00F, 2.50F, 5, false);

	public static void main(String[] arg) {
		cheese.getCost();
		System.out.println(cheese.getSellPrice());
		cheese.setName("cheesy cheese").setQuantity(3).setOnSale(true, 20);
		System.out.println(cheese.getName());
		System.out.println(cheese.getQuantity());
		System.out.println(cheese.getOnSale());
		System.out.println(cheese.getSellPrice());
		cheese.setName("cheesy cheese").setQuantity(3).setOnSale(false, 20);
		System.out.println(cheese.getOnSale());
		System.out.println(cheese.getSellPrice());
	}
}
