package ca.ece.ubc.cpen221.mp5;

/** USED BY QUERYBASELISTENER
 * Represents an operation that is conformed to by multiple suboperations.
 * Examples of instances of these interface is the OR expression and AND expressions
 */
public interface Addable extends Operation {
    /**
     * Takes an operation and adds it to the list of suboperations that conform this operations
     * @param o the operation that it is wanted to add
     */
    public void addOperation(Operation o);
}
