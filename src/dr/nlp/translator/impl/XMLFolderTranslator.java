package dr.nlp.translator.impl;

import dr.nlp.structure.Document;
import dr.nlp.structure.Folder;
import dr.nlp.structure.impl.DocumentImpl;
import dr.nlp.structure.impl.FolderImpl;
import dr.nlp.translator.FolderTranslator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public class XMLFolderTranslator implements FolderTranslator {
	@Override
	public String translateToString(Folder folder) {
		return convert(folder);
	}

	@Override
	public void write(Folder folder, OutputStream os) throws IOException {
		os.write(convert(folder).getBytes());
	}

	private String convert(Folder folder) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(FolderImpl.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			jaxbMarshaller.marshal(folder, baos);

			return baos.toString(
					java.nio.charset.StandardCharsets.UTF_8.name());
		} catch (Exception e)
		{
			throw new RuntimeException("Unexpected error occured when tranlating document to xml.", e);
		}
	}
}
