package com.example.basic_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basic_project.R;
import com.example.basic_project.ShopItem;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class ShopItemsListAdapter extends ArrayAdapter<ShopItem> {
    public ShopItemsListAdapter(Context context, ArrayList<ShopItem> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View viewMyLayout, ViewGroup parent) {
        // Get the data item for this position
        ShopItem shopItem = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (viewMyLayout == null) {
            viewMyLayout = LayoutInflater.from(getContext()).inflate(R.layout.receipt_element_2, parent, false);
        }
        // Lookup view for data population

        TextView itemNameTextView = viewMyLayout.findViewById(R.id.itemName);
        TextView purchasePriceTextView = viewMyLayout.findViewById(R.id.purchasePrice);
        TextView datePurchased = viewMyLayout.findViewById(R.id.datePurchased);
        ImageView productImage = viewMyLayout.findViewById(R.id.productImage);

        // Populate the data into the template view using the data object
        itemNameTextView.setText(shopItem.itemName);
        purchasePriceTextView.setText(shopItem.getPriceWithTax());
        datePurchased.setText(printCalendar(shopItem.datePurchased));
        productImage.setImageResource(shopItem.productImage);
        // Return the completed view to render on screen
        return viewMyLayout;
    }

    public String printCalendar(GregorianCalendar calendarToPrint) {

        String date = String.valueOf(calendarToPrint.get(GregorianCalendar.MONTH)) + "/" +
                String.valueOf(calendarToPrint.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + String.valueOf(calendarToPrint.get(GregorianCalendar.YEAR));

        return date;

    }

}