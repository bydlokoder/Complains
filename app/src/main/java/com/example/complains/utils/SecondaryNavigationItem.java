package com.example.complains.utils;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public enum SecondaryNavigationItem {
    LawLink("О защите прав потребителя", FontAwesome.Icon.faw_external_link),
    Settings("Настройки", FontAwesome.Icon.faw_cog),
    About("О программе", FontAwesome.Icon.faw_info);

    private String name;
    FontAwesome.Icon icon;

    SecondaryNavigationItem(String name, FontAwesome.Icon icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public FontAwesome.Icon getIcon() {
        return icon;
    }

    public static SecondaryDrawerItem[] getSecondaryDrawerItemArray() {
        SecondaryNavigationItem[] navigationItems = values();
        SecondaryDrawerItem[] secondaryItems = new SecondaryDrawerItem[navigationItems.length];

        for (int i = 0; i < navigationItems.length; i++) {
            SecondaryNavigationItem item = navigationItems[i];
            secondaryItems[i] = new SecondaryDrawerItem()
                    .withName(item.getName())
                    .withIcon(item.getIcon())
                    .withIdentifier(item.ordinal());
        }
        return secondaryItems;
    }
}
