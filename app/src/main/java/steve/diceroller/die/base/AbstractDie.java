package steve.diceroller.die.base;

/**
 * Created by steve on 7/16/15.
 */
public abstract class AbstractDie implements Die
{

//  Represents one die (singular of dice) with faces showing values
//  between 1 and 6.

    protected int NUM_OF_SIDES;

    protected int faceValue;  // current value showing on the die

    public AbstractDie()
    {
        faceValue = 1;
    }

    //-----------------------------------------------------------------
    //  Rolls the die and returns the result.
    //-----------------------------------------------------------------

    @Override
    public int roll()
    {
        faceValue = (int)(Math.random() * NUM_OF_SIDES) + 1;

        return faceValue;
    }

    //-----------------------------------------------------------------
    //  Face value mutator.
    //-----------------------------------------------------------------
    @Override
    public void setFaceValue (int value)
    {
        faceValue = value;
    }

    @Override
    public int getFaceValue()
    {
        return faceValue;
    }

    // Returns a string representation of this die.
    public String toString()
    {
        String result = Integer.toString(faceValue);
        return result;
    }

    public void setNumberOfSides(int numberOfSides)
    {
        this.NUM_OF_SIDES = numberOfSides;
    }
}