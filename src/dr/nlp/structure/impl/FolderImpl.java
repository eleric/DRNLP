package dr.nlp.structure.impl;

import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by eleri_000 on 10/18/2015.
 */
@XmlRootElement(name = "folder")
@XmlSeeAlso({DocumentImpl.class})
public class FolderImpl implements Folder {
	@XmlElementWrapper
	@XmlAnyElement
	private final Set<Document> documents = new HashSet<>();

	@Override
	public Set<Document> getDocuments() {
		return documents;
	}
}
