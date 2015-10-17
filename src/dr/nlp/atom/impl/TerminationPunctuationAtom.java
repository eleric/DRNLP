package dr.nlp.atom.impl;

import dr.nlp.atom.Atom;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class TerminationPunctuationAtom implements Atom {
	@Override
	public boolean isValid(char c) {
		return (c == '.' || c == '?' || c == '!');
	}

	@Override
	public boolean isValid(int i) {
		char c = (char) i;
		return isValid(c);
	}

}
