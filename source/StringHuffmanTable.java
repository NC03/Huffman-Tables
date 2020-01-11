/**
 * String specific subclass of Huffman Table class
 * @author NC03
 * @version 2.0.1
 */
public class StringHuffmanTable extends HuffmanTable<String>
{
    public StringHuffmanTable(String msg)
    {
        super(splitString(msg));
    }
    public static String[] splitString(String msg)
    {
        char[] c = msg.toCharArray();
        String[] o = new String[c.length];
        for(int i = 0; i < c.length; i++)
        {
            o[i] = ""+c[i];
        }
        return o;
    }
}