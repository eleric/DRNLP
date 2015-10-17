package dr.nlp.translator;

import dr.nlp.structure.Document;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public interface DocumentTranslator {
	String translateToString(Document document);
	void write(Document document, OutputStream os) throws IOException;
	void append(Document document, StringBuilder buf) throws IOException;

}
