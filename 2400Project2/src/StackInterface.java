/**
 * Interface for implementing linked stack and resizable array stack
 */
public interface StackInterface<T> {
    /**
     * Pushes the given entry to the top of the stack.
     * @param entry
     */
    public void push(T entry);

    /**
     * Removes and returns the top entry from the stack.
     * @return
     */
    public T pop();

    /**
     * Returns the top item of the stack. Does not affect the stack.
     * @return
     */
    public T peek();

    /**
     * Returns true if the stack contains no items.
     * @return true if stack is empty
     */
    public boolean isEmpty();

    /**
     * Removes all items from the stack.
     */
    public void clear();

}
