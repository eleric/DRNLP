package dr.nlp.structure;

import java.util.List;
import java.util.Set;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public interface Folder {
	/**
	 * Lists documents in unorder list
	 *
	 * @return
	 */
	Set<Document> getDocuments();
}
