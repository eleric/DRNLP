package dr.nlp.dataInput;

import java.io.EOFException;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public interface DataInput<T> {
	/**
	 * Returns the element that the index currently points to.
	 * Then increments index to point to next element.
	 *
	 * @return
	 * @throws EOFException
	 */
	T get() throws EOFException;

	/**
	 * Gets current index.
	 * @return
	 */
	int getIndex();

	/**
	 * Sets index.  Starts at 0.  Throws exception if less then 0 or greater then
	 * size - 1.
	 * @param index
	 * @throws IndexOutOfBoundsException
	 */
	void setIndex(int index) throws IndexOutOfBoundsException;

	/**
	 * return number of data elements.
	 * @return
	 */
	int getSize();

	/**
	 * Sets index to 0.
	 */
	void reset();

	/**
	 * Sets index to size - 1.
	 */
	void eof();

	/**
	 * True if index equals 0, else false.
	 * @return
	 */
	boolean isBeginning();

	/**
	 * True if index equal or greater then size - 1, else false;
	 * @return
	 */
	boolean isEof();

	/**
	 * Sets index to index - 1.  Throws exception if isBeginning true.
	 */
	void stepBack() throws IndexOutOfBoundsException;
}
