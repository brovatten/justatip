package com.classes.tip;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CountriesArray countries = new CountriesArray();
    private String chosen_dropalt;

    private EditText mEdit;
    private Button calculate_btn;
    private Button taxi_btn;
    private Button rest_btn;
    private TextView priceText;
    private ImageButton taxiImgBtn;
    private ImageButton restImgBtn;
    private ImageButton infoBtn;
    private TextView infoText;
    private TextView developerTxt;
    private TextView designTxt;
    private TextView assumeChargeTxt;

    private boolean isAlt_taxi;
    private boolean isAlt_restaurant;
    private boolean infoDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> dropdown_alts = new ArrayList<>();
        dropdown_alts = this.countries.retrieve_alternatives();


        final Spinner spinner = findViewById(R.id.spinner);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(100);
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }


        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                R.layout.spinner_item, dropdown_alts);

        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mEdit = (EditText) findViewById(R.id.textInputEditText);
        priceText = (TextView) findViewById(R.id.priceText);
        taxiImgBtn = (ImageButton) findViewById(R.id.TaxiImgBtn);
        restImgBtn = (ImageButton) findViewById(R.id.RestImgBtn);
        assumeChargeTxt = (TextView) findViewById(R.id.assumeCharge);

        infoBtn = (ImageButton) findViewById(R.id.InfoButton);
        infoText = (TextView) findViewById(R.id.infoText);
        developerTxt = (TextView) findViewById(R.id.developer);
        designTxt = (TextView) findViewById(R.id.design);
        infoText.setVisibility(View.GONE);
        developerTxt.setVisibility(View.GONE);
        designTxt.setVisibility(View.GONE);
        assumeChargeTxt.setVisibility(View.GONE);


        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!infoDisplayed) {
                    infoDisplayed = true;
                    mEdit.setVisibility(View.GONE);
                    priceText.setVisibility(View.GONE);
                    taxiImgBtn.setVisibility(View.GONE);
                    restImgBtn.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    calculate_btn.setVisibility(View.GONE);
                    assumeChargeTxt.setVisibility(View.GONE);


                    infoText.setVisibility(View.VISIBLE);
                    designTxt.setVisibility(View.VISIBLE);
                    developerTxt.setVisibility(View.VISIBLE);
                } else {
                    infoDisplayed = false;
                    calculate_btn.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.VISIBLE);
                    mEdit.setVisibility(View.VISIBLE);
                    priceText.setVisibility(View.VISIBLE);
                    taxiImgBtn.setVisibility(View.VISIBLE);
                    restImgBtn.setVisibility(View.VISIBLE);
                    assumeChargeTxt.setVisibility(View.VISIBLE);

                    infoText.setVisibility(View.GONE);
                    designTxt.setVisibility(View.GONE);
                    developerTxt.setVisibility(View.GONE);
                }

            }
        });

        taxiImgBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ScaleAnimation fade_in = new ScaleAnimation(
                        1.1f, 1f, 1.1f, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                fade_in.setDuration(150);
                fade_in.setFillAfter(true);
                findViewById(R.id.TaxiImgBtn).startAnimation(fade_in);
                isAlt_taxi = true;
                isAlt_restaurant = false;

                try {
                    if (String.valueOf(mEdit.getText()).equals("")) {
                        mEdit.setHint("Price of Taxi");
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }
        });

        restImgBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                ScaleAnimation fade_in = new ScaleAnimation(
                        1.1f, 1f, 1.1f, 1f,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                        0.5f);
                fade_in.setDuration(150);
                fade_in.setFillAfter(true);
                findViewById(R.id.RestImgBtn).startAnimation(fade_in);
                isAlt_restaurant = true;
                isAlt_taxi = false;

                try {
                    if (String.valueOf(mEdit.getText()).equals("")) {
                        mEdit.setHint("Price of Restaurant");
                    }
                } catch (NumberFormatException e) {
                    return;
                }
            }
        });

        calculate_btn = (Button) findViewById(R.id.button);
        calculate_btn.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 double price;
                                                 try {
                                                     price = Double.parseDouble(String.valueOf(mEdit.getText()));
                                                 } catch (NumberFormatException e) {
                                                     return;
                                                 }
                                                 Country chosenCountry = countries.returnCountry(chosen_dropalt);
                                                 String countryCurrency = chosenCountry.getCurrency();
                                                 double[] interval;


                                                 if (isAlt_taxi) {
                                                     interval = chosenCountry.getTaxi().calculate_tip(price);
                                                     assumeChargeTxt.setVisibility(View.GONE);
                                                 } else if (isAlt_restaurant) {
                                                     interval = chosenCountry.getRest().calculate_tip(price);
                                                     assumeChargeTxt.setVisibility(View.VISIBLE);
                                                     if (chosenCountry.isServiceCharge()) {
                                                         assumeChargeTxt.setText("If included Service Charge");
                                                     } else {
                                                         assumeChargeTxt.setText("If no included Service Charge");
                                                     }
                                                 } else
                                                     return;

                                                 int dec1;
                                                 int dec2;

                                                 /*System.out.println("length" + interval.length);
                                                 System.out.println("low:" + interval[0]);
                                                 System.out.println("high:" + interval[1]);*/
                                                 if (interval.length == 1) {
                                                     double inputLowEnd = interval[0] - (int) interval[0];
                                                     inputLowEnd = inputLowEnd * 10d;
                                                     dec1 = (int) inputLowEnd;

                                                     if (dec1 == 0.0) {
                                                         priceText.setText((int) interval[0] + "");
                                                     } else {
                                                         priceText.setText((int) interval[0] + "." + dec1);
                                                     }
                                                 } else if (interval.length == 2) {

                                                     if (interval[0] == 0.0 && interval[1] == 0.0) {
                                                         priceText.setText("0" + countryCurrency);
                                                         return;
                                                     }

                                                     double inputLowEnd = interval[0] - (int) interval[0];
                                                     inputLowEnd = inputLowEnd * 10d;
                                                     dec1 = (int) inputLowEnd;

                                                     double inputHighEnd = interval[1] - (int) interval[1];
                                                     inputHighEnd = inputHighEnd * 10d;
                                                     dec2 = (int) inputHighEnd;

                                                     if (dec1 + dec2 == 0) {
                                                         priceText.setText((int) interval[0]
                                                                 + "-" + (int) interval[1] + " " + countryCurrency);
                                                         return;
                                                     }
                                                     if (dec1 != 0 && dec2 != 0) {
                                                         priceText.setText((int) interval[0] + "." + dec1
                                                                 + "-" + (int) interval[1] + "." + dec2
                                                                 + " " + countryCurrency);
                                                         return;
                                                     }
                                                     if (dec1 != 0) {
                                                         priceText.setText((int) interval[0] + "." + dec1
                                                                 + "-" + (int) interval[1] + " " + countryCurrency);
                                                         return;
                                                     }
                                                     if (dec2 != 0) {
                                                         priceText.setText((int) interval[0]
                                                                 + "-" + (int) interval[1] + "." + dec2
                                                                 + " " + countryCurrency);
                                                         return;
                                                     }
                                                 }
                                             }

                                         }

        );
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0) {
            this.chosen_dropalt = parent.getItemAtPosition(position).toString();
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}