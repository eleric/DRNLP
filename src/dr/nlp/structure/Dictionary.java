package dr.nlp.structure;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class Dictionary {
	private final Map<Token, Token> tokens = new HashMap<>();

	/**
	 *
	 * @return All tokens
	 */
	public Set<Token> getTokens() {
		return tokens.keySet();
	}

	/**
	 *
	 * @param type
	 * @return All tokens of a given type
	 */
	public Set<Token> getTokens(Token.Type type)
	{
		if (type == null)
		{
			return getTokens();
		}
		return getTokens().stream().filter((t)->t.getType().equals(type)).collect(
				Collectors.toSet());
	}

	/**
	 *
	 * @param token
	 * @return Token from dictionary if it exists.  Else returns token passed after writing it to dictionary.
	 * Adds a token to the dictionary if it doesn't already exist.  Thread safe.
	 */
	public Token putToken(Token token)
	{
		if (!tokens.containsKey(token))
		{
			synchronized (this) {
				if (!tokens.containsKey(token)) {
					tokens.put(token, token);
				}
			}
		}
		return tokens.get(token);
	}
}
