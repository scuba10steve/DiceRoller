package steve.diceroller;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import steve.diceroller.die.DieType;
import steve.diceroller.die.FourDie;
import steve.diceroller.die.SixDie;


public class MainActivity extends DieActivity
{
    private ArrayList die;
    private int NUMBER_OF_DICE, rerollAbove, doubleAbove;
    private boolean doubl = false, reroll = false;
    private DieType dieType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadWidgets();
        dieType = DieType.SixDie;
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
    }

    public void setDice()
    {

//        switch (dieType)
//        {
//            case FourDie:
//                die = new ArrayList<FourDie>();
//                break;
//            case SixDie:
//                die = new ArrayList<SixDie>();
//                break;
//            case EightDie:
//
//            case TenDie:
//
//            case TwelveDie:
//
//            case TwentyDie:
//
//            default:
//                throw new IllegalArgumentException("Unable to pick DieType from: " + dieType);
//        }

        try
        {
            this.NUMBER_OF_DICE = Integer.parseInt(editNumOfDice.getText().toString());
            die = new ArrayList();
            for (int i = 0; i < NUMBER_OF_DICE; i++)
                die.add(new SixDie());
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
            for (Object obj : die) {
                SixDie d = (SixDie) obj;
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