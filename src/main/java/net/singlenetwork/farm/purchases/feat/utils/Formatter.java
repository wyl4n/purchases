package net.singlenetwork.farm.purchases.feat.utils;

import java.text.DecimalFormat;

public class Formatter {
    public static String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String priceToString(Double price) {
        return priceWithoutDecimal(price);
    }
}
