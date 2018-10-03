package com.example.android.justjava;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=2;
    int price =0 ;
    String name ;
    boolean checkboxW;
    boolean checkboxC;
    int priceOfCoffee = 5;
    int priceOfWhippedCream = 1 ;
    int priceOfChocolate = 2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayQuantity(quantity);
        getname();
        Ck();
        calculatePrice();
//        createOrderSummary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.coffe_ord_sum, name));
        intent.putExtra(Intent.EXTRA_TEXT,(getString(R.string.sum_name, name) +
                " \n" + getString(R.string.sum_wc_q, checkboxW) +
                " \n" + getString(R.string.sum_c_q, checkboxC) +
                " \n" + getString(R.string.quantityq, quantity) +
                " \n" + getString(R.string.total, NumberFormat.getCurrencyInstance(Locale.US).format(price)) +
                " \n" + getString(R.string.ty)));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantity >= 100){
            Toast.makeText(this, getString(R.string.toast_overflow), Toast.LENGTH_SHORT).show();
            return ;
        }else {
            displayQuantity(quantity += 1);
        }
    }

    public void decrement(View view) {
        if (quantity <= 1){
            Toast.makeText(this, getString(R.string.toast_underflow), Toast.LENGTH_SHORT).show();
            return ;
        }else {
            displayQuantity(quantity -= 1);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


//    /**
//     * This method displays the given price on the screen.
//     */
//    private void displayPrice() {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(price));
//    }

//    /**
//     * This method displays the given text on the screen.
//     */
//    private void createOrderSummary() {
//        getname();
//        Ck();
//        calculatePrice();
//        Intent intent = new Intent(Intent.ACTION_SENDTO);
//        intent.setData(Uri.parse("mailto:"));
//        intent.putExtra(Intent.EXTRA_SUBJECT,"Coffee Order Summary");
//        intent.putExtra(Intent.EXTRA_TEXT,("Name : "+ name +
//                " \n" + "Wants Whipped Cream ? "+ checkboxW +
//                " \n" + "Wants Chocolate ? " + checkboxC +
//                " \n" + "Quantity : " + quantity +
//                " \n" + "Total : " + NumberFormat.getCurrencyInstance(Locale.US).format(price) +
//                " \n" +"Thank You"));
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }

    private void calculatePrice()
    {
       price=quantity* priceOfOne();
        return ;
    }

    private void Ck()
    {
        CheckBox chkbWhipC = (CheckBox                                                                                                                                  ) findViewById(R.id.whippedCream_checkbox);
        checkboxW = chkbWhipC.isChecked();

        CheckBox chkbC = (CheckBox) findViewById(R.id.chocolate_checkbox);
        checkboxC = chkbC.isChecked();
    }

    private void getname() {
        EditText nameEdit = (EditText) findViewById(R.id.name_edit_text);
        name = nameEdit.getText().toString();
    }

    private int priceOfOne(){
        int priceOf1Cup = priceOfCoffee;
        if (checkboxW)
        {
            priceOf1Cup += priceOfWhippedCream ;
        }
        if (checkboxC){
            priceOf1Cup += priceOfChocolate ;
        }
        return priceOf1Cup ;
    }
}
