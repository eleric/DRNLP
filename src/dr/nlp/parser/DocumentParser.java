package dr.nlp.parser;

import dr.nlp.dataInput.DataInput;
import dr.nlp.structure.Document;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public interface DocumentParser {
	Document parse(Path path) throws IOException;
}
