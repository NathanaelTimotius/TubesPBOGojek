package model;

public class Cart {
    private Menu menu;
    int quantity;
    
    public Cart(){
        
    }
    
    public Cart(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" + "menu=" + menu + ", quantity=" + quantity + '}';
    }
    
}
