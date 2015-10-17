package dr.nlp.processor;

import dr.nlp.dataInput.DataInput;
import dr.nlp.exception.WrongTypeException;
import dr.nlp.token.Token;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by eleri_000 on 10/16/2015.
 */
public interface Processor<T> {
	/**
	 * Returns the next token in the input.  Returns an empty Optional
	 * if token can not be processed by this processor. Throws EOFException
	 * if input has reached the end of index.
	 *
	 * @param dataInput
	 * @return
	 * @throws EOFException
	 */
	Optional<Token> process(DataInput<T> dataInput) throws EOFException;
}
