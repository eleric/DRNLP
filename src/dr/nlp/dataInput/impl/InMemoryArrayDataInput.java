package dr.nlp.dataInput.impl;

import dr.nlp.dataInput.DataInput;

import java.io.EOFException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Holds an entire inputstream in an array.  Only good for small to
 * moderly sized files.
 * Created by eleri_000 on 10/17/2015.
 */
public class InMemoryArrayDataInput implements DataInput<Character> {
	private final char[] inputArray;
	private int index;
	private int size;

	public InMemoryArrayDataInput(Path path) throws IOException {
		byte[] bytes = Files.readAllBytes(path);
		size = bytes.length;
		inputArray = new char[size];
		for (int i=0; i < size; i++)
		{
			inputArray[i] = (char) bytes[i];
		}
		index = 0;
	}

	@Override
	public Character get() throws EOFException {
		if (isEof())
		{
			throw new EOFException();
		}
		return inputArray[index++];
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setIndex(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		this.index = index;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void reset() {
		index = 0;
	}

	@Override
	public void eof() {
		index = size;
	}

	@Override
	public boolean isBeginning() {
		return index == 0;
	}

	@Override
	public boolean isEof() {
		return index >= size;
	}

	@Override
	public void stepBack() throws IndexOutOfBoundsException {
		if (isBeginning())
		{
			throw new IndexOutOfBoundsException();
		}
		index--;
	}
}
