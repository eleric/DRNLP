package dr.nlp.structure;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class Document {
	private final List<Sentence> sentences = new LinkedList<>();

	public List<Sentence> getSentences() {
		return sentences;
	}

}
