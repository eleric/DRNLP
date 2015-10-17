package dr.nlp.atom;

/**
 * Atom is the smallest unit in a document.  A single character.
 * Created by eleri_000 on 10/16/2015.
 */
public interface Atom {
	/**
	 * Is the character passed a valid atom of this type.
	 * @param c
	 * @return
	 */
	boolean isValid(char c);

	/**
	 * Is the integer representation of a character a valid atom of this type.
	 * @param i
	 * @return
	 */
	boolean isValid(int i);
}
