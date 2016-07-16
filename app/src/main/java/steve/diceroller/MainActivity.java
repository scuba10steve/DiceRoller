package steve.diceroller;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity
{
    private ArrayList<Die> die;
    private int NUMBER_OF_DICE;
    private boolean doubl = false, reroll = false;
    private TextView rolls;
    private EditText editNumOfDice;
    private Button btnRoll, btnPlus, btnMinus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NUMBER_OF_DICE = 2;
        die = new ArrayList<Die>();
        for (int i = 0; i < NUMBER_OF_DICE; i++)
            die.add(new Die());
        editNumOfDice = (EditText) findViewById(R.id.editNumOfDice);
        editNumOfDice.setText("" + NUMBER_OF_DICE);

        btnRoll = (Button) findViewById(R.id.btnRoll);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);

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
            die = new ArrayList<Die>();
            for (int i = 0; i < NUMBER_OF_DICE; i++)
                die.add(new Die());
        }
        catch (Exception e)
        {
            Toast message = Toast.makeText(this.getApplicationContext(), "Invalid Input Entered\n, Please Enter an Integer Value > 0.", Toast.LENGTH_SHORT);
            message.show();
        }
    }
    public void roll()
    {
        setDice();
        rolls.setText("");
        for (Die d : die) {
            d.roll();
            rolls.append("" + d.getFaceValue() + ", ");

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

