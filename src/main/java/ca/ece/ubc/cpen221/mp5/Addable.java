package ca.ece.ubc.cpen221.mp5;

/**
 * Represents an operation that is conformed by multiple suboperations.
 * Examples of instances of these interface is the or expression and and expressions
 */
public interface Addable extends Operation {
    /**
     * Takes an operation and adds it to the list of suboperations that conform this operations
     * @param o the operation that it is wanted to add
     */
    public void addOperation(Operation o);
}
