package model;

public class MenuBuilder {
    private String menuName;
    private MenuType menuType;
    private int price;

    public MenuBuilder() {

    }
    public MenuBuilder withMenuname(String menuName) {
        this.menuName = menuName;
        return this;
    }


    public MenuBuilder withMenuType(MenuType menuType) {
        this.menuType = menuType;
        return this;
    }

    public MenuBuilder withPrice(int price) {
        this.price = price;
        return this;
    }

    public Menu build() {
        return new Menu(this.menuName, this.menuType, this.price);
    }


}