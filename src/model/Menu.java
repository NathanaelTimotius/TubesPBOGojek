package model;

public class Menu {
    private String menuName;
    private MenuType menuType;
    private int price;
    
    public Menu(){
        
    }
    
    public Menu(String menuName, MenuType menuType, int price) {
        this.menuName = menuName;
        this.menuType = menuType;
        this.price = price;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public MenuType getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuType menuType) {
        this.menuType = menuType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuName=" + menuName + ", menuType=" + menuType + ", price=" + price + '}';
    }
    
    
}
