package dr.nlp.structure;

/**
 * Created by eleri_000 on 10/15/2015.
 */
public class Token {
	public enum Type
	{
		WHITE_SPACE,
		PUNCTUATION,
		WORD
	}
	private String value;
	private Type type;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
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
