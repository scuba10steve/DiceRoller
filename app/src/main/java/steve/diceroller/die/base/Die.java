package steve.diceroller.die.base;

/**
 * Created by steve on 6/8/2017.
 */

public interface Die
{
    public int roll();

    public int getFaceValue();

    public void setFaceValue(int value);
}
