package steve.diceroller;

import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

import java.util.ArrayList;

import steve.diceroller.die.EightDie;
import steve.diceroller.die.FourDie;
import steve.diceroller.die.HundredDie;
import steve.diceroller.die.SixDie;
import steve.diceroller.die.TenDie;
import steve.diceroller.die.TwelveDie;
import steve.diceroller.die.TwentyDie;
import steve.diceroller.die.base.Die;


public class MainActivity extends DieActivity
{
    private ArrayList die;
    private int NUMBER_OF_DICE, rerollAbove, doubleAbove;
    private boolean doubl = false, reroll = false;
    private String dieTypeAsString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadWidgets();

        dieTypeAsString = "6";
        NUMBER_OF_DICE = 2;
        die = new ArrayList();
        for (int i = 0; i < NUMBER_OF_DICE; i++)
            die.add(new SixDie());

        editNumOfDice.setText("" + NUMBER_OF_DICE);

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
                    makeToastShort("Invalid Input Entered\n, Please Enter an Integer Value > 0.");
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
                    makeToastShort("Invalid Input Entered\n Please Enter an Integer Value > 0 and < 2,147,483,647.");
                }
            }
        });

        npDieSides.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                dieTypeAsString = picker.getDisplayedValues()[newVal - 1];

                txtDieSides.setText("Die Sides:" + dieTypeAsString);
            }
        });
    }

    public void setDice()
    {
        try {
            this.NUMBER_OF_DICE = Integer.parseInt(editNumOfDice.getText().toString());

            switch (dieTypeAsString) {
                case "4":
                    die = new ArrayList<FourDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new FourDie());
                    break;
                case "6":
                    die = new ArrayList<SixDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new SixDie());
                    break;
                case "8":
                    die = new ArrayList<EightDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new EightDie());
                    break;
                case "10":
                    die = new ArrayList<TenDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new TenDie());
                    break;
                case "12":
                    die = new ArrayList<TwelveDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new TwelveDie());
                    break;
                case "20":
                    die = new ArrayList<TwentyDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new TwentyDie());
                    break;
                case "100":
                    die = new ArrayList<HundredDie>();
                    for (int i = 0; i < NUMBER_OF_DICE; i++)
                        die.add(new HundredDie());
                    break;
                default:
                    throw new IllegalArgumentException("Unable to pick DieType from: " + dieTypeAsString);
            }
        }
        catch (Exception e)
        {
            makeToastShort("Invalid Input Entered\n, Please Enter an Integer Value > 0.");
        }
    }

    public void roll()
    {
        setDice();
        if (die.size() > 0) {
            StringBuffer buff = new StringBuffer();
            //        int dice[] = new int[NUMBER_OF_DICE];
            rolls.setText("");
            //        int i = 0;
            Die d = null;
            for (Object obj : die) {

                switch (dieTypeAsString)
                {
                    case "4":
                        d = (FourDie) obj;
                        break;
                    case "6":
                        d = (SixDie) obj;
                        break;
                    case "8":
                        d = (EightDie) obj;
                        break;
                    case "10":
                        d = (TenDie) obj;
                        break;
                    case "12":
                        d = (TwelveDie) obj;
                        break;
                    case "20":
                        d = (TwentyDie) obj;
                        break;
                    case "100":
                        d = (HundredDie) obj;
                        break;

                }
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
            makeToastShort("Number Too large");
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
            makeToastShort("Number Too Small");
        }
    }
}