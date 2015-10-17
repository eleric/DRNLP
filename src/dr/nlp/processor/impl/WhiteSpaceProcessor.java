package dr.nlp.processor.impl;

import dr.nlp.atom.impl.WhiteSpaceAtom;
import dr.nlp.processor.AbstractCharacterProcessor;
import dr.nlp.processor.Processor;
import dr.nlp.token.type.TokenType;

/**
 * Created by eleri_000 on 10/17/2015.
 */
public class WhiteSpaceProcessor extends AbstractCharacterProcessor implements Processor<Character> {
	public WhiteSpaceProcessor() {
		tokenAtom = new WhiteSpaceAtom();
		tokenType = TokenType.WHITE_SPACE;
	}
}
