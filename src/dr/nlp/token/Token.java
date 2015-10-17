package dr.nlp.token;

import dr.nlp.token.type.TokenType;

/**
 * Created by eleri_000 on 10/15/2015.
 * Immutable object that represents component of a sentence.
 */
public class Token {

	private final String value;
	private final TokenType type;

	public Token(String value, TokenType type) {
		this.value = value;
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public TokenType getType() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Token token = (Token) o;

		if (!getValue().equals(token.getValue())) return false;
		return getType() == token.getType();

	}

	@Override
	public int hashCode() {
		int result = getValue().hashCode();
		result = 31 * result + getType().hashCode();
		return result;
	}
}
