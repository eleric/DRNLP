package dr.nlp.atom.impl;

import dr.nlp.atom.Atom;

/**
 * Consider all characters not part of a word or white space punctuation.
 * Created by eleri_000 on 10/17/2015.
 */
public class PunctuationAtom implements Atom {
	private final Atom wordAtom = new WordAtom();
	private final Atom whiteSpaceAtom = new WhiteSpaceAtom();

	@Override
	public boolean isValid(char c) {
		return (!wordAtom.isValid(c) && !whiteSpaceAtom.isValid(c));
	}

	@Override
	public boolean isValid(int i) {
		char c = (char) i;
		return isValid(c);
	}
}
