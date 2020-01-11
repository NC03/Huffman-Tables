/**
 * End of Message subclass of Node class for a binary tree
 * @author NC03
 * @version 2.0.1
 */
public class EOM<T> extends FrequencyNode<T>
{
    @Override
    public boolean isEOM()
    {
        return true;
    }

    @Override
    public int getDepth()
    {
        return 1;
    }

    @Override
    public int getFrequency()
    {
        return 1;
    }
}