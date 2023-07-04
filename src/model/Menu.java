package model;

public class Menu {
    private String menuName;
    private MenuType menuType;
    private String price;

    public Menu(String menuName, MenuType menuType, String price) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Menu{" + "menuName=" + menuName + ", menuType=" + menuType + ", price=" + price + '}';
    }
    
    
}
