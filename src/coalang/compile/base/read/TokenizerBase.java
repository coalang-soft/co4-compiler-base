package coalang.compile.base.read;

import io.github.coalangsoft.lang.token.Token;
import io.github.coalangsoft.lang.token.TokenGroup;
import io.github.coalangsoft.lang.token.TokenType;

import java.util.ArrayList;

public class TokenizerBase {
	
	private ArrayList<Token> tokens;
	int line;
	private String currentToken;
	int column;

	public TokenizerBase(){
		this.tokens = new ArrayList<Token>();
		line = 1;
		column = 0;
		currentToken = "";
	}
	
	public boolean feed(int i){
		if(i < 0){
			return false;
		}
		if(i == '\n'){
			line++;
			column = 0;
		}else{
			column++;
		}
		
		if(isSpace(i)){
			finishCurrent();
		}else{
			if(TokenType.find("" + (char) i) == TokenType.SPECIAL){
				finishCurrent();
				currentToken = "" + (char) i;
				finishCurrent();
			}else{
				String s = currentToken + (char) i;
				TokenType a = TokenType.find(currentToken);
				TokenType b = TokenType.find(s);
				
				if(a != b && !currentToken.isEmpty()){
					finishCurrent();
					currentToken = "" + (char) i;
				}else{
					currentToken = s;
				}
			}
		}
		
		return true;
	}

	private void finishCurrent() {
		if(!currentToken.isEmpty()){
			tokens.add(new Token(line, column, currentToken));
		}
		currentToken = "";
	}

	private boolean isSpace(int i) {
		return ("" + (char) i).trim().isEmpty();
	}
	
	public TokenGroup finish(){
		finishCurrent();
		return new TokenGroup(tokens);
	}
	
}
