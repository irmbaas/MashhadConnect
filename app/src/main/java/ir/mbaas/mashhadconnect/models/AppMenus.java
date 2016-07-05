package ir.mbaas.mashhadconnect.models;

import java.util.List;

public class AppMenus {
    public List<AppMenu> menus;

    public enum MenuType {
        TEXT,
        DIVIDER
    }

    public AppMenus(List<AppMenu> menus) {
        this.menus = menus;
    }
}
