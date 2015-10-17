package dr.nlp.atom.impl;

import dr.nlp.atom.Atom;

/**
 * Atoms consisting of White space.  All no visible ASCII characters are
 * assumed to be white space.
 * Created by eleri_000 on 10/16/2015.
 */
public class WhiteSpaceAtom implements Atom {
	@Override
	public boolean isValid(char c) {
		int i = (int) c;
		return isValid(i);
	}

	@Override
	public boolean isValid(int i) {
		return ((i >= 0 && i <= 32) || (i == 127));
	}
}
