package com.example.basic_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {


    ArrayList<ShopItem> shopItems = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getList();

   /*     Button changeTextButton = findViewById(R.id.changeTextButton_1);

        changeTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textToChange = findViewById(R.id.textToChange);
                textToChange.setText("TEST");
            }
        });
*/




        ShopItemsListAdapter adapter = new ShopItemsListAdapter(this, shopItems);
        ListView listOfProducts = findViewById(R.id.listOfProducts);
        listOfProducts.setAdapter(adapter);

/*********************
 * FILER BY YEAR BUTTON
 */
        Button filterResultsByYearButton = findViewById(R.id.filterByYear);

        filterResultsByYearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<ShopItem> filteredList = getItemsFromCurrentCalendarParameter(shopItems, Calendar.YEAR);

                ShopItemsListAdapter adapter = new ShopItemsListAdapter(getApplicationContext(), filteredList);
                ListView listOfProducts = findViewById(R.id.listOfProducts);
                listOfProducts.setAdapter(adapter);

            }
        });

/*********************
 * FILER BY MONTH BUTTON
 */
        Button filterResultsByMonthButton = findViewById(R.id.filterByMonth);

        filterResultsByMonthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<ShopItem> filteredList = getItemsFromCurrentCalendarParameter(shopItems, Calendar.MONTH);

                ShopItemsListAdapter adapter = new ShopItemsListAdapter(getApplicationContext(), filteredList);
                ListView listOfProducts = findViewById(R.id.listOfProducts);
                listOfProducts.setAdapter(adapter);

            }
        });

/*********************
 * FILER BY WEEK BUTTON
 */
        Button filterResultsByWeekButton = findViewById(R.id.filterByWeek2);

        filterResultsByWeekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<ShopItem> filteredList = getItemsFromCurrentCalendarParameter(shopItems, Calendar.WEEK_OF_YEAR);

                ShopItemsListAdapter adapter = new ShopItemsListAdapter(getApplicationContext(), filteredList);
                ListView listOfProducts = findViewById(R.id.listOfProducts);
                listOfProducts.setAdapter(adapter);

            }
        });




    }

    public ArrayList<ShopItem> getItemsFromCurrentCalendarParameter(ArrayList<ShopItem> items, int calendarParameter) {

        ArrayList<ShopItem> filteredShopItems = new ArrayList<>();
        Calendar currentDate = GregorianCalendar.getInstance();
        int currentWeekOfYear = currentDate.get(calendarParameter);

        for(ShopItem shopItem : items) {

            int weekOfYearInList = shopItem.getDatePurchased().get(calendarParameter);

            if(currentWeekOfYear == weekOfYearInList) {
                filteredShopItems.add(shopItem);
            }


        }

        return filteredShopItems;

    }

    public void getList() {
        final int SIZE = 1000;
        shopItems = new ArrayList<>();

        for (int i = 0; i < SIZE; i++) {

            shopItems.add(getRandomShopItemData("EXAMPLE_TEST", R.drawable.milk_gallon));

        }

    }

    public ShopItem getRandomShopItemData(String itemName, int itemImage) {

        GregorianCalendar randomCalendar = getRandomCalendar(2018, 2020);
        BigDecimal randomPrice = new BigDecimal(String.valueOf(randomNumberInRange(3, 15)));

        ShopItem randomShopItem = new ShopItem(itemName, itemImage, randomCalendar, randomPrice);

        return randomShopItem;

    }

    public GregorianCalendar getRandomCalendar(int yearStart, int yearEnd) {

        GregorianCalendar randomDatePurchased = new GregorianCalendar();
        int randomYear = randomNumberInRange(yearStart, yearEnd);
        int randomDay = randomNumberInRange(1, 28);
        int randomMonth = randomNumberInRange(1, 12);
        randomDatePurchased.set(randomYear, randomMonth, randomDay);

        return randomDatePurchased;

    }

    public String printCalendar(GregorianCalendar calendarToPrint) {

        String date = String.valueOf(calendarToPrint.get(GregorianCalendar.MONTH)) + "/" +
                String.valueOf(calendarToPrint.get(GregorianCalendar.DAY_OF_MONTH)) + "/" + String.valueOf(calendarToPrint.get(GregorianCalendar.YEAR));

        return date;

    }

    public static int randomNumberInRange(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

}