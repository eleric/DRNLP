package dr.nlp.structure;

import dr.nlp.structure.Sentence;

import java.util.List;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public interface Document {
	/**
	 *
	 * @return
	 * Lists sentences in order for a given document
	 */
	List<Sentence> getSentences();
}
