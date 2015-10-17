package dr.nlp.translator.impl;

import dr.nlp.structure.Document;
import dr.nlp.translator.DocumentTranslator;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.Collectors;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class XMLDocumentTranslator implements DocumentTranslator {
	@Override
	public String translateToString(Document document) {
		return document.getSentences().stream().map((s)->s.getTokens().stream().map((t)->t.getValue()).collect(
				Collectors.joining(","))).collect(Collectors.joining("\n"));
	}

	@Override
	public void write(Document document, OutputStream os) throws IOException {

	}

	@Override
	public void append(Document document, StringBuilder buf)
			throws IOException {

	}
}
