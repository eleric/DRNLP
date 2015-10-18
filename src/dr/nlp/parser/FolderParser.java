package dr.nlp.parser;

import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;

import java.io.IOException;
import java.nio.file.Path;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public interface FolderParser {
	Folder parse(Path path) throws IOException;
}
