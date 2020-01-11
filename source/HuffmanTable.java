import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Arrays;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Huffman Table class
 * 
 * @author NC03
 * @version 2.0.1
 */
public class HuffmanTable<T> {
    private T[] data;
    private List<FrequencyNode<T>> lookupTable;
    private boolean addEOM = true;
    private EOM<T> eom;

    public static void main(String[] args) {
        if (args.length > 0) {
            String msg = args[0];
            StringHuffmanTable table = new StringHuffmanTable(msg);
            table.generateLookupTable();
            System.out.println(table);

            String ascii = "";
            for (char c : msg.toCharArray()) {
                int num = (int) c;
                ascii += "" + (num / 256 % 2) + (num / 128 % 2) + (num / 64 % 2) + (num / 32 % 2) + (num / 16 % 2)
                        + (num / 8 % 2) + (num / 4 % 2) + (num / 2 % 2);
            }
            String huff = table.generateOutput();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(new File("huffmanTableOutput_2.txt")));
                bw.write("Message: " + msg.length() + "\n");
                bw.write(msg + "\n\n");
                bw.write("ASCII: " + ascii.length() + "\n");
                bw.write(ascii);
                bw.write("\n\nHuffman Table Data:" + huff.length() + " \n");
                bw.write(huff);
                bw.write("\n\nHuffman Table Key: \n");
                bw.write(table.viewLookupTable());
                bw.newLine();
                bw.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public HuffmanTable(T[] d) {
        this.data = d;
    }

    public void generateLookupTable() {
        // Calculate Frequencies
        lookupTable = new ArrayList<FrequencyNode<T>>();
        dataiterator: for (T t : data) {
            for (FrequencyNode<T> l : lookupTable) {
                if (t.equals(l.getObject())) {
                    l.incrementFrequency();
                    continue dataiterator;
                }
            }
            lookupTable.add(new FrequencyNode<T>(t));
        }
        if (addEOM) {
            eom = new EOM<T>();
            lookupTable.add((FrequencyNode<T>) eom);
        }

        // Generate Node Tree
        PriorityQueue<FrequencyNode<T>> nodeTree = new PriorityQueue<FrequencyNode<T>>(lookupTable.size(),
                new Comparator<FrequencyNode<T>>() {
                    @Override
                    public int compare(FrequencyNode<T> arg0, FrequencyNode<T> arg1) {
                        return arg0.compareTo(arg1);
                    }
                });
        lookupTable.forEach(a -> nodeTree.add(a));
        while (nodeTree.size() > 1) {
            FrequencyNode<T> newNode = new FrequencyNode<T>();
            newNode.setLeftNode(nodeTree.poll());
            newNode.setRightNode(nodeTree.poll());
            nodeTree.add(newNode);
        }

        // Build Patterns
        recursiveGeneratePattern("", nodeTree.poll());
    }

    private void recursiveGeneratePattern(String prefix, FrequencyNode<T> n) {
        if (n.endLeaf()) {
            n.setPattern(prefix);
        } else {
            FrequencyNode<T> a = (FrequencyNode<T>) n.getLeftNode();
            FrequencyNode<T> b = (FrequencyNode<T>) n.getRightNode();
            recursiveGeneratePattern(prefix + 0, a);
            recursiveGeneratePattern(prefix + 1, b);
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + Arrays.toString(this.data) + "\n" + viewLookupTable();
    }

    private String line(int len) {
        String out = "|";
        for (int i = 0; i < len - 2; i++) {
            out += "-";
        }
        return out + "|";
    }

    public String viewLookupTable() {
        String out = "";
        int objectLength = "Object".length(), patternLength = "Pattern".length(),
                frequencyLength = "Frequency".length(), depthLength = "Depth".length();
        for (FrequencyNode<T> o : lookupTable) {
            if (!o.isEOM()) {
                int tempObjectLength = o.getObject().toString().length();
                int tempPatternLength = o.getPattern().length();
                int tempFrequencyLength = (o.getFrequency() + "").length();
                int tempDepthLength = ("" + tempPatternLength).length();
                if (tempObjectLength > objectLength) {
                    objectLength = tempObjectLength;
                }
                if (tempPatternLength > patternLength) {
                    patternLength = tempPatternLength;
                }
                if (tempFrequencyLength > frequencyLength) {
                    frequencyLength = tempFrequencyLength;
                }
                if (tempDepthLength > depthLength) {
                    depthLength = tempDepthLength;
                }
            }
        }
        int overallLength = objectLength + patternLength + frequencyLength + depthLength + 5;

        out = line(overallLength) + "\n";
        out += "|" + centerString("Object", objectLength) + "|" + centerString("Pattern", patternLength) + "|"
                + centerString("Frequency", frequencyLength) + "|" + centerString("Depth", depthLength) + "|\n";
        out += line(overallLength) + "\n";
        for (FrequencyNode<T> o : lookupTable) {
            out += "|" + centerString(o.isEOM() ? "EOM" : o.getObject().toString(), objectLength) + "|"
                    + centerString(o.getPattern(), patternLength) + "|"
                    + centerString(o.getFrequency() + "", frequencyLength) + "|"
                    + centerString(o.getPattern().length() + "", depthLength) + "|\n";
        }
        out += line(overallLength);
        return out;
    }

    public String centerString(String s, int len) {
        int pad = len - s.length();
        String out = "";
        for (int i = 0; i < pad / 2; i++) {
            out += " ";
        }
        out += s;
        for (int i = 0; i < pad - pad / 2; i++) {
            out += " ";
        }
        return out;
    }

    public String generateOutput() {
        String out = "";
        dataiterator: for (T t : data) {
            for (FrequencyNode<T> o : lookupTable) {
                if (o.getObject().equals(t)) {
                    out += o.getPattern();
                    continue dataiterator;
                }
            }
        }
        if (addEOM) {
            out += eom.getPattern();
        }
        return out;
    }
}