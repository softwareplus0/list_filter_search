package com.example.basic_project;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ShopItem {

    final BigDecimal TAX_PERCENTAGE = new BigDecimal("1.0925");

    String itemName = "";
    int productImage = 0;
    GregorianCalendar datePurchased = null;
    BigDecimal itemPrice = new BigDecimal("0");

    public ShopItem(String itemName, int productImage, GregorianCalendar datePurchased, BigDecimal itemPrice) {

        this.itemName = itemName;
        this.productImage = productImage;
        this.datePurchased = datePurchased;
        this.itemPrice = itemPrice;


    }

    public Calendar getDatePurchased() {

        return datePurchased;

    }

    public String getPriceWithoutTax() {

        return itemPrice.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

    }

    public String getPriceWithTax() {

        BigDecimal price = itemPrice.multiply(TAX_PERCENTAGE);
        return price.setScale(2, BigDecimal.ROUND_HALF_UP).toString();

    }

}
