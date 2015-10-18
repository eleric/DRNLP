package dr.nlp.structure.impl;

import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by eleri_000 on 10/18/2015.
 */
public class DictionaryImplTest {
	DictionaryImpl dictionary;
	String value1 = "value1";
	TokenType type1 = TokenType.PROPER_NOUN;
	String value2 = "value2";
	TokenType type2 = TokenType.WORD;
	String value3 = "value3";
	TokenType type3 = TokenType.PUNCTUATION;

	@Before
	public void setUp() throws Exception {
		dictionary = new DictionaryImpl();
		Token token = new Token(value1, type1);
		dictionary.putToken(token);
		token = new Token(value1, type2);
		dictionary.putToken(token);
		token = new Token(value2, type2);
		dictionary.putToken(token);
		token = new Token(value3, type3);
		dictionary.putToken(token);
	}

	@Test
	public void testGetTokens() throws Exception {
		assertEquals(4, dictionary.getTokens().size());
	}

	@Test
	public void testGetTokensOfType() throws Exception {
		assertEquals(2, dictionary.getTokens(type2).size());
	}

	@Test
	public void testPutTokenThatAlreadyMapped() throws Exception {
		Token token = new Token(value1, type1);
		int beforeSize = dictionary.getTokens().size();
		Token retrievedToken = dictionary.putToken(token);

		// Check nothing added
		assertEquals(beforeSize, dictionary.getTokens().size());
		// Check not the same object referenced in memory
		assertFalse(token == retrievedToken);
		// Check both tokens equal each other
		assertEquals(token, retrievedToken);
		// Check both have the same hash
		assertEquals(token.hashCode(), retrievedToken.hashCode());
	}

	@Test
	public void testPutTokenNotAlreadyMapped() throws Exception {
		Token token = new Token(value3, type1);
		int beforeSize = dictionary.getTokens().size();
		Token retrievedToken = dictionary.putToken(token);

		// Check 1 added
		assertEquals(beforeSize+1, dictionary.getTokens().size());
		// Check that the same object referenced in memory
		assertTrue(token == retrievedToken);
	}

	@Test
	public void testContainsTrue() throws Exception {
		assertTrue(dictionary.contains(new Token(value1, type1)));
	}

	@Test
	public void testContainsFalse() throws Exception {
		assertFalse(dictionary.contains(new Token(value3, type1)));
	}
}