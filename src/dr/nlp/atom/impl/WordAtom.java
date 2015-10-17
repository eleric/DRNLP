package dr.nlp.atom.impl;

import dr.nlp.atom.Atom;

/**
 * The atoms consisting of Words.
 * Created by eleri_000 on 10/16/2015.
 */
public class WordAtom implements Atom {
	@Override
	public boolean isValid(char c) {
		return ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') ||
				(c >= 'a' && c <= 'z'));
	}

	@Override
	public boolean isValid(int i) {
		char c = (char) i;
		return isValid(c);
	}
}
