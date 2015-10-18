package dr.nlp.processor.impl;

import dr.nlp.atom.impl.WordAtom;
import dr.nlp.processor.AbstractCharacterProcessor;
import dr.nlp.processor.Processor;
import dr.nlp.structure.Dictionary;
import dr.nlp.token.type.TokenType;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class WordProcessor extends AbstractCharacterProcessor implements Processor<Character> {
	public WordProcessor(Dictionary masterDictionary) {
		super(masterDictionary);
		tokenAtom = new WordAtom();
		tokenType = TokenType.WORD;
	}
}
