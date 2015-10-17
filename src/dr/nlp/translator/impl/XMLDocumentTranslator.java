package dr.nlp.translator.impl;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import dr.nlp.structure.Document;
import dr.nlp.structure.impl.DocumentImpl;
import dr.nlp.translator.DocumentTranslator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.beans.XMLDecoder;
import java.io.*;
import java.util.stream.Collectors;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public class XMLDocumentTranslator implements DocumentTranslator {
	@Override
	public String translateToString(Document document) {
		return convert(document);
//		document.getSentences().stream().map((s)->s.getTokens().stream().map((t)->t.getValue()).collect(
//				Collectors.joining(","))).collect(Collectors.joining("\n"));
	}

	@Override
	public void write(Document document, OutputStream os) throws IOException {
		os.write(convert(document).getBytes());
	}

	private String convert(Document document) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DocumentImpl.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(document, baos);

			return baos.toString(
					java.nio.charset.StandardCharsets.UTF_8.name());
		} catch (Exception e)
		{
			throw new RuntimeException("Unexpected error occured when tranlating document to xml.", e);
		}
	}
}
