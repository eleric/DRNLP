package dr.nlp.structure;

import dr.nlp.token.Token;
import dr.nlp.token.type.TokenType;

import java.util.Set;

/**
 * Created by eleri_000 on 10/16/2015.
 * Holds the tokens to be referenced by the other structure objects.
 */
public interface Dictionary {
	Set<Token> getTokens();
	Set<Token> getTokens(TokenType type);
	Token putToken(Token token);
}
