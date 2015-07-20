package com.example.complains.utils;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public enum PrimaryNavigationItem {
    Complains("Мои жалобы", FontAwesome.Icon.faw_home),
    Favourites("Избранное", FontAwesome.Icon.faw_star_o),
    Articles("Статьи", FontAwesome.Icon.faw_book);

    private String name;
    FontAwesome.Icon icon;

    PrimaryNavigationItem(String name, FontAwesome.Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public FontAwesome.Icon getIcon() {
        return icon;
    }

    public static PrimaryDrawerItem[] getPrimaryDrawerItemArray() {
        PrimaryNavigationItem[] navigationItems = values();
        PrimaryDrawerItem[] primaryDrawerItems = new PrimaryDrawerItem[navigationItems.length];
        for (int i = 0; i < navigationItems.length; i++) {
            PrimaryNavigationItem item = navigationItems[i];
            primaryDrawerItems[i] = new PrimaryDrawerItem()
                    .withName(item.getName())
                    .withIcon(item.getIcon())
                    .withIdentifier(item.ordinal());
        }
        return primaryDrawerItems;
    }
}
