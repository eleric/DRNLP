package dr.nlp.structure.impl;

import dr.nlp.structure.Document;
import dr.nlp.structure.Sentence;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class DocumentImpl implements Document {
	private final List<Sentence> sentences = new LinkedList<>();

	public List<Sentence> getSentences() {
		return sentences;
	}

}
