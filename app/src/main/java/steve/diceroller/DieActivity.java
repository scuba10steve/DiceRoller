package steve.diceroller;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by steve on 9/18/2016.
 * Include anything related to the Interface.  Keep separate from application backend code.
 */



public class DieActivity extends Activity
{
    TextView rolls;
    EditText editNumOfDice, editRerollAbove, editDoubleAbove;
    ToggleButton btnDouble, btnReroll;
    Button btnRoll, btnPlus, btnMinus;
    NumberPicker npDieSides;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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

    public void loadWidgets()
    {
        setContentView(R.layout.activity_main);

        rolls = (TextView) findViewById(R.id.txtRolls);
        rolls.setMovementMethod(new ScrollingMovementMethod());

        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);

        btnReroll = (ToggleButton) findViewById(R.id.tglBtnReroll);
        btnDouble = (ToggleButton) findViewById(R.id.tglBtnDouble);

        editNumOfDice = (EditText) findViewById(R.id.editNumOfDice);
        editDoubleAbove = (EditText) findViewById(R.id.txtEditDouble);
        editRerollAbove = (EditText) findViewById(R.id.txtEditReroll);

        npDieSides = (NumberPicker) findViewById(R.id.npDieSides);
        populateNumberPicker(npDieSides);
    }

    public void makeToastShort(String message)
    {
        Toast t = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT);
        t.show();
    }

    public void makeToastLong(String message)
    {
        Toast t = Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_LONG);
        t.show();
    }

    public void populateNumberPicker(NumberPicker picker)
    {
        String[] numbers = new String[7];

        numbers[0] = Integer.toString(4);
        numbers[1] = Integer.toString(6);
        numbers[2] = Integer.toString(8);
        numbers[3] = Integer.toString(10);
        numbers[4] = Integer.toString(12);
        numbers[5] = Integer.toString(20);
        numbers[6] = Integer.toString(100);

        picker.setMinValue(1);
        picker.setMaxValue(14);
        picker.setWrapSelectorWheel(false);
        picker.setDisplayedValues(numbers);
        picker.setValue(4);
    }
}
