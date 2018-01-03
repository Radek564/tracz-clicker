package com.sd.traczclicker;

/**
 * Created by John on 2017-12-23.
 */

public class DecorationsItem {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription(int position) {
        if (ShopDecorations.shopDecBought == 1) {
            if (position == 0)
                return "Wykupiono!";
        }
        if (ShopDecorations.shopDec2Bought == 1) {
            if (position == 1)
                return "Wykupiono!";
        }
        if (ShopDecorations.shopDec3Bought == 1) {
            if (position == 2)
                return "Wykupiono!";
        }
        if (ShopDecorations.shopDec4Bought == 1) {
            if (position == 3)
                return "Wykupiono!";
        }
        return description;

    }

    public void setDescription(String description) {
        this.description = description;
    }
}
