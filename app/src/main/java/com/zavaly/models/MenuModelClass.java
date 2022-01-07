package com.zavaly.models;

public class MenuModelClass {

    private int menuId;
    private String menuTitle;
    private int menuImage;

    public MenuModelClass(int menuId, String menuTitle, int menuImage) {
        this.menuId = menuId;
        this.menuTitle = menuTitle;
        this.menuImage = menuImage;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }
}
