package ir.mbaas.mashhadconnect.models;

import android.content.Context;

public class AppMenu {
    public String title;
    public String icon;

    private int resIcon;
    private int resTitle;

    public AppMenus.MenuType type;

    public AppMenu(String title, String icon) {
        this(title, icon, AppMenus.MenuType.TEXT);
    }

    public AppMenu(Context ctx, int resTitle, int resIcon) {
        this(ctx, resTitle, resIcon, AppMenus.MenuType.TEXT);
    }

    public AppMenu(String title, String icon, AppMenus.MenuType menuType) {
        this.title = title;
        this.icon = icon;
        this.type = menuType;
    }

    public AppMenu(Context ctx, int resTitle, int resIcon, AppMenus.MenuType type) {
        this.resIcon = resIcon;
        this.icon = ctx.getResources().getString(resIcon);

        this.resTitle = resTitle;
        this.title = ctx.getResources().getString(resTitle);

        this.type = type;
    }

    public int getResTitle() {
        return resTitle;
    }
}
