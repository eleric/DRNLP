package dr.nlp.translator;

import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public interface FolderTranslator {
	String translateToString(Folder folder);
	void write(Folder folder, OutputStream os) throws IOException;
}
