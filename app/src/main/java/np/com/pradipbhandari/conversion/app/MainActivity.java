package np.com.pradipbhandari.conversion.app;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Spinner unitTypeSpinner;
    private EditText amountTextView;
    TextView teaspoonTextView,tablespoonTextView,cupTextView,ounceTextView,
            pintTextView,quartTextView,gallonTextView,poundTextView,milliliterTextView,
            literTextView,milligramTextView, kilogramTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        addItemsToUnitTypeSpinner();
        addlistnerToUnitTypeSpinner();
        amountTextView = (EditText) findViewById(R.id.amount_text_view);
        initializeTextViews();
    }
    public void initializeTextViews(){
        teaspoonTextView = (TextView) findViewById(R.id.tsp_text_view);
        tablespoonTextView = (TextView) findViewById(R.id.tbs_text_view);
        cupTextView = (TextView) findViewById(R.id.cup_text_view);
        ounceTextView = (TextView) findViewById(R.id.oz_text_view);
        pintTextView = (TextView) findViewById(R.id.print_text_view);
        quartTextView = (TextView) findViewById(R.id.quart_text_view);
        gallonTextView = (TextView) findViewById(R.id.gallon_text_view);
        poundTextView = (TextView) findViewById(R.id.pound_text_view);
        milliliterTextView = (TextView) findViewById(R.id.ml_text_view);
        literTextView = (TextView) findViewById(R.id.liter_text_view);
        milligramTextView = (TextView) findViewById(R.id.mg_text_view);
        kilogramTextView = (TextView) findViewById(R.id.kg_text_view);

    }
    public void addItemsToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        ArrayAdapter<CharSequence> unitTypeSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_types, android.R.layout.simple_spinner_item);
        unitTypeSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitTypeSpinner.setAdapter(unitTypeSpinnerAdapter);

    }

    public void addlistnerToUnitTypeSpinner() {
        unitTypeSpinner = (Spinner) findViewById(R.id.unit_type_spinner);
        unitTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelectedInSpinner = parent.getItemAtPosition(position).toString();
                checkIfConvertingFromTsp(itemSelectedInSpinner);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void checkIfConvertingFromTsp(String currentUnit) {
        if (currentUnit.equals("teaspoon")) {
            updateUnitTypeUsingTsp(Quantity.Unit.tsp);
        } else if (currentUnit.equals("tablespoon")) {
                updateUnitTypeUsingOther(Quantity.Unit.tbs);
        }else if (currentUnit.equals("cup")) {
                updateUnitTypeUsingOther(Quantity.Unit.cup);
        } else if (currentUnit.equals("ounce")) {
            updateUnitTypeUsingOther(Quantity.Unit.oz);
        } else if (currentUnit.equals("pint")) {
            updateUnitTypeUsingOther(Quantity.Unit.pint);
        } else if (currentUnit.equals("quart")) {
            updateUnitTypeUsingOther(Quantity.Unit.quart);
        } else if (currentUnit.equals("gallon")) {
            updateUnitTypeUsingOther(Quantity.Unit.gallon);
        } else if (currentUnit.equals("pound")) {
            updateUnitTypeUsingOther(Quantity.Unit.pound);
        } else if (currentUnit.equals("milliliter")) {
            updateUnitTypeUsingOther(Quantity.Unit.ml);
        } else if (currentUnit.equals("liter")) {
            updateUnitTypeUsingOther(Quantity.Unit.liter);
        } else if (currentUnit.equals("milligram")) {
            updateUnitTypeUsingOther(Quantity.Unit.mg);
        } else   {
            updateUnitTypeUsingOther(Quantity.Unit.kg);
        }
    }

    public void updateUnitTypeUsingTsp(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        String teaspoonValueAndUnit = doubleToConvert + "tsp";

        teaspoonTextView.setText(teaspoonValueAndUnit);

        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.cup, cupTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.oz, ounceTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.pint, pintTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.quart, quartTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.gallon, gallonTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.pound, poundTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.ml, milliliterTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.liter, literTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.mg, milligramTextView);
        updateUnitTypeUsingTsp(doubleToConvert, Quantity.Unit.kg, kilogramTextView);
    }

    public void updateUnitTypeUsingTsp(double doubleToCOnvert, Quantity.Unit unitConvertingTo, TextView theTextView) {
        Quantity unitQuantity = new Quantity(doubleToCOnvert, Quantity.Unit.tsp);
        String tempUnit = unitQuantity.to(unitConvertingTo).toString();
        theTextView.setText(tempUnit);

    }
    public void updateUnitTypeUsingOther(Quantity.Unit currentUnit) {
        double doubleToConvert = Double.parseDouble(amountTextView.getText().toString());
        Quantity currentQuantitySelected = new Quantity(doubleToConvert, currentUnit);
        String valueInTeaspoons = currentQuantitySelected.to(Quantity.Unit.tsp).toString();
        teaspoonTextView.setText(valueInTeaspoons);

        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.tbs, tablespoonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.cup, cupTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.oz, ounceTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.quart, quartTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pint, pintTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.gallon, gallonTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.pound, poundTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.ml, milliliterTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.liter, literTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.mg, milligramTextView);
        updateUnitTextFieldUsingTsp(doubleToConvert, currentUnit, Quantity.Unit.kg, kilogramTextView);
        if (currentUnit.name().equals(currentQuantitySelected.unit.name())) {
            String currentUnitTExtViewText = doubleToConvert + " " + currentQuantitySelected.unit.name();
            String currentTextViewName = currentQuantitySelected.unit.name() + "_text_view";
            int currenId = getResources().getIdentifier(currentTextViewName, "id", MainActivity.this.getPackageName());
            TextView currentTextView = (TextView) findViewById(currenId);
            currentTextView.setText(currentUnitTExtViewText);

        }
    }

    public void updateUnitTextFieldUsingTsp(double doubleTOConvert, Quantity.Unit currentUnit,
                                            Quantity.Unit preferredUnit, TextView targetTExtView) {
        Quantity currentQuantitySelected = new Quantity(doubleTOConvert, currentUnit);
        String tempTextView = currentQuantitySelected.to(Quantity.Unit.tsp).to(preferredUnit).toString();
        targetTExtView.setText(tempTextView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
