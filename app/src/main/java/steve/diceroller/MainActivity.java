package steve.diceroller;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import steve.diceroller.die.HexDie;


public class MainActivity extends Activity
{
    private ArrayList<HexDie> die;
    private int NUMBER_OF_DICE, rerollAbove, doubleAbove;
    private boolean doubl = false, reroll = false;
    private TextView rolls;
    private EditText editNumOfDice, editRerollAbove, editDoubleAbove;
    private Button btnRoll, btnPlus, btnMinus;
    private ToggleButton btnDouble, btnReroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NUMBER_OF_DICE = 2;
        die = new ArrayList<HexDie>();
        for (int i = 0; i < NUMBER_OF_DICE; i++)
            die.add(new HexDie());
        editNumOfDice = (EditText) findViewById(R.id.editNumOfDice);
        editDoubleAbove = (EditText) findViewById(R.id.txtEditDouble);
        editRerollAbove = (EditText) findViewById(R.id.txtEditReroll);


        editNumOfDice.setText("" + NUMBER_OF_DICE);

        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnReroll = (ToggleButton) findViewById(R.id.tglBtnReroll);
        btnDouble = (ToggleButton) findViewById(R.id.tglBtnDouble);

        btnRoll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                roll();
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                increaseDice();
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                decreaseDice();
            }
        });
        btnReroll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                reroll = btnReroll.isChecked();
                try
                {
                    rerollAbove = Integer.parseInt(editRerollAbove.getText().toString());
                }
                catch (Exception e)
                {
                    Toast message = Toast.makeText(getApplicationContext(), "Invalid Input Entered\n, Please Enter an Integer Value > 0.", Toast.LENGTH_SHORT);
                    message.show();
                }
            }
        });
        btnDouble.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                doubl = btnDouble.isChecked();
                try
                {
                    doubleAbove = Integer.parseInt(editDoubleAbove.getText().toString());
                }
                catch (Exception e)
                {
                    Toast message = Toast.makeText(getApplicationContext(), "Invalid Input Entered\n, Please Enter an Integer Value > 0.", Toast.LENGTH_SHORT);
                    message.show();
                }
            }
        });


        rolls = (TextView) findViewById(R.id.txtRolls);
        rolls.setMovementMethod(new ScrollingMovementMethod());

    }

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

    public void setDice()
    {
        try
        {
            this.NUMBER_OF_DICE = Integer.parseInt(editNumOfDice.getText().toString());
            die = new ArrayList<HexDie>();
            for (int i = 0; i < NUMBER_OF_DICE; i++)
                die.add(new HexDie());
        }
        catch (Exception e)
        {
            Toast message = Toast.makeText(this.getApplicationContext(), "Invalid Input Entered\n, Please Enter an Integer Value > 0.", Toast.LENGTH_SHORT);
            message.show();
        }
    }
//
//    public ContextWrapper getAppContext()
//    {
//        return this.getApplicationContext();
//    }

    public void roll()
    {
        setDice();
        if (die.size() > 0) {
            StringBuffer buff = new StringBuffer();
            //        int dice[] = new int[NUMBER_OF_DICE];
            rolls.setText("");
            //        int i = 0;
            for (HexDie d : die) {
                d.roll();
                //            dice[i] = d.getFaceValue();
                if (reroll && d.getFaceValue() >= rerollAbove) {
                    d.roll();
                }
                if (doubl && d.getFaceValue() >= doubleAbove) {
                    d.setFaceValue(d.getFaceValue() * 2);
                }
                buff.append(d.getFaceValue() + ", ");
                //            i++;
            }
            buff = new StringBuffer(buff.subSequence(0, buff.length() - 2));
            rolls.setText(buff);
        }
        else {
            Toast t = Toast.makeText(this.getApplicationContext(), "Input needs to be greater than 0, entered: " + NUMBER_OF_DICE, Toast.LENGTH_SHORT);
            t.show();
        }
    }

    public void increaseDice()
    {
        if (NUMBER_OF_DICE < 10000)
        {
            this.NUMBER_OF_DICE++;
            editNumOfDice.setText("" + NUMBER_OF_DICE);
        }
        else
        {
            Toast message = Toast.makeText(this.getApplicationContext(), "Number Too large", Toast.LENGTH_SHORT);
            message.show();
        }
    }

    public void decreaseDice()
    {
        if (NUMBER_OF_DICE > 0)
        {
            this.NUMBER_OF_DICE--;
            editNumOfDice.setText("" + NUMBER_OF_DICE);
        }
        else
        {
            Toast message = Toast.makeText(this.getApplicationContext(), "Number Too Small", Toast.LENGTH_SHORT);
            message.show();
        }
    }
}