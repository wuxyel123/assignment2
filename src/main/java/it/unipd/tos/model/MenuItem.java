////////////////////////////////////////////////////////////////////
// Alessandro Discalzi 1169739
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
	public enum ItemType {Panino, Fritto, Bevande}
	private ItemType type;
	private String name;
	private double price;
	
	public MenuItem(ItemType type, String name, double price){
        this.setType(type);
        this.setName(name);
        this.setPrice(price);
    }

    public MenuItem(){}
    
    public void setType(ItemType type){
    	this.type=type;
    }
    
    public void setName(String name){
    	this.name=name;
    }
    
    public void setPrice(double price){
    	this.price=price;
    }
    
    public ItemType getType(){
    	return type;
    }
    
    public String getName(){
    	return name;
    }
    
    public double getPrice(){
    	return price;
    }
	

}
