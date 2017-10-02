

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */

package com.example.android.justjava;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.attr.id;
import static android.R.id.edit;
import static android.R.id.message;
import static com.example.android.justjava.R.id.name_editText;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=1;
    int totalPriceValue=0;
    String checkBoxValString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        totalPriceValue = calculatePrice(quantity);
        createOrderSummary(quantity,totalPriceValue);
        //displayPrice(totalPriceValue);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }

    public void increment(View view) {


        if (quantity>100)
        {
            callToast(quantity);

//                       Toast toast = Toast.makeText(this, "Cannot be over 100", Toast.LENGTH_LONG);
//            View toastView = toast.getView(); //This'll return the default View of the Toast.


        }
        else {
            quantity =quantity+1;
            display(quantity);
            displayPrice(quantity * 5);
            String priceMessage = "Total Price: $" + quantity * 5;
            displayMessage(priceMessage);
        }
        //displayPrice(numberOfCoffees*5);
    }


    public void decrement(View view) {
        //int quantity=2;

        if (quantity<1)
        {
            callToast(quantity);
        }
        else{
            quantity =quantity-1;
            display(quantity);
            displayPrice(quantity * 5);
            String priceMessage = "Total Price: $" + quantity * 5;
            displayMessage(priceMessage);
        }
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
    void callToast(int quantity){
        String strToast="";
        if (quantity<1)
        {
            strToast = "Cannot be Less than 1";
        }
        else
        {
            strToast = "Cannot be more than 100";
        }
        Toast toast = Toast.makeText(this, strToast, Toast.LENGTH_LONG);
        View toastView = toast.getView(); //This'll return the default View of the Toast.
        TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
        toastMessage.setTextSize(16);
        toastMessage.setTextColor(Color.BLACK);
        toastMessage.setGravity(Gravity.CENTER);
        toastMessage.setCompoundDrawablePadding(16);
        toastView.setBackgroundColor(Color.WHITE);
        toast.show();
    }
    int calculatePrice(int quantity) {
        CheckBox checkBoxValue = (CheckBox) findViewById(R.id.checkBox_whippedCream);
        int price = quantity * 5;
        boolean val;
        val = checkBoxValue.isChecked();
        if(val) {
            checkBoxValString = "Yes";
            price+=(1*quantity);
        }
        else {
            checkBoxValString = "No";
        }


        return price;
    }
    void createOrderSummary(int q, int tPV){
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        EditText edit = (EditText)findViewById(R.id.name_editText);
        String strName = edit.getText().toString();

        priceTextView.setText("Name:"  +strName+ "\nCoffee(s): " + q + " with topping: " +checkBoxValString+ "\nTotal Price: " + tPV + "\nThank you for your order!");

    }
}