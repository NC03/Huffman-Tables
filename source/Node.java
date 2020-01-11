/**
 * Node interface for a binary tree
 * @author NC03
 * @version 2.0.1
 */
public interface Node<T>
{
    /**
     * Set the left Node of this object
     */
    public void setLeftNode(Node<T> n);
    /**
     * Set the right Node of this object
     */
    public void setRightNode(Node<T> n);
    /**
     * Return the left Node of this object
     * @return The Node object in the left position
     */
    public Node<T> getLeftNode();
    /**
     * Return the right Node of this object
     * @return The Node object in the right position
     */
    public Node<T> getRightNode();
    /**
     * Boolean whether the object is an end leaf
     * @return True if the object is an end leaf else false
     */
    public boolean endLeaf();
    /**
     * The object stored by the Node
     * @return The object stored by the Node
     */
    public T getObject();
}