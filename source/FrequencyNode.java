/**
 * Node class for a binary tree
 * @author NC03
 * @version 2.0.1
 */
public class FrequencyNode<T> implements Comparable<FrequencyNode<T>>, Node<T> {
    private int frequency;
    private String pattern;
    private T object;
    private FrequencyNode<T> leftNode;
    private FrequencyNode<T> rightNode;

    // Constructors
    public FrequencyNode(T object, int frequency) {
        this.object = object;
        this.frequency = frequency;
    }

    public FrequencyNode(T object) {
        this(object, 1);
    }

    public FrequencyNode() {
        this(null);
    }

    // Getters
    public int getFrequency() {
        if (endLeaf()) {
            return frequency;
        } else {
            return ((FrequencyNode<T>) getLeftNode()).getFrequency()
                    + ((FrequencyNode<T>) getRightNode()).getFrequency();
        }
    }

    public String getPattern() {
        return pattern;
    }

    public int getDepth() {
        if (endLeaf()) {
            return 1;
        } else {
            return 1 + Math.max(((FrequencyNode<T>) getLeftNode()).getDepth(),
                    ((FrequencyNode<T>) getRightNode()).getDepth());
        }
    }

    @Override
    public Node<T> getLeftNode() {
        return leftNode;
    }

    @Override
    public Node<T> getRightNode() {
        return rightNode;
    }

    @Override
    public T getObject() {
        return object;
    }

    public boolean isEOM() {
        return false;
    }

    @Override
    public boolean endLeaf() {
        return getLeftNode() == null && getRightNode() == null;
    }

    // Setters
    public void incrementFrequency() {
        frequency++;
    }

    public void setPattern(String s) {
        pattern = s;
    }

    @Override
    public void setLeftNode(Node<T> n) {
        leftNode = (FrequencyNode<T>) n;
    }

    @Override
    public void setRightNode(Node<T> n) {
        rightNode = (FrequencyNode<T>) n;
    }

    // Other Methods
    public int compareTo(FrequencyNode<T> other) {
        int out = getFrequency() - other.getFrequency();
        if (out == 0) {
            out = getDepth() - other.getDepth();
        }
        return out;
    }

}