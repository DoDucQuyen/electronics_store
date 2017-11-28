package entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cart {
	private HashMap<Integer, Item> cartItems;

	public Cart() {
		cartItems = new HashMap<>();
	}

	public Cart(HashMap<Integer, Item> cartItems) {
		super();
		this.cartItems = cartItems;
	}

	public HashMap<Integer, Item> getCartItems() {
		return cartItems;
	}

	public void setCartItems(HashMap<Integer, Item> cartItems) {
		this.cartItems = cartItems;
	}
	// add to cart
	public void addToCart(Integer productId, Item item){
		boolean check = cartItems.containsKey(productId);
		if(check) {
			int quantity = cartItems.get(productId).getQuantity();
			item.setQuantity(quantity + 1);
			cartItems.put(productId, item);
		}
		else {
			cartItems.put(productId, item);
		}
	}
	public void removeToCart(Integer productId) {
		if(cartItems.containsKey(productId)){
			cartItems.remove(productId);
		}
	}
	public int count(){
		return cartItems.size();
	}
	public double totalCart(){
		double total = 0;
		for(Map.Entry<Integer, Item> list : cartItems.entrySet()){
			total += list.getValue().getProduct().getPrice() * list.getValue().getQuantity();
		}
		return total;
	}
	public String toString(){
		JSONArray cartDetail = new JSONArray();
		JSONObject jsonOb;
		for(Map.Entry<Integer, Item> list : cartItems.entrySet()) {
			jsonOb = new JSONObject();
			jsonOb.put(list.getKey(), list.getValue().getQuantity());
			cartDetail.add(jsonOb);
		}
		return cartDetail.toJSONString();
	}
	public void showCart(){
		for(Map.Entry<Integer, Item> list : cartItems.entrySet()) {
			System.out.println(list.getKey() + "-" + list.getValue().getQuantity());
		}
	}
	public static void main(String []args){
		Cart cart = new Cart();
		cart.addToCart(1, new Item(new Product(1), 2));
		cart.addToCart(3, new Item(new Product(3), 2));
		cart.addToCart(7, new Item(new Product(4), 1));
		String str = cart.toString();
	}
}
