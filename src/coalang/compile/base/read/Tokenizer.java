package coalang.compile.base.read;

import io.github.coalangsoft.lang.token.TokenGroup;

import java.io.IOException;
import java.io.InputStream;

public class Tokenizer {
	
	private TokenizerBase base;

	public Tokenizer(){
		this.base = new TokenizerBase();
	}
	
	public void stream(InputStream stream, String info) throws TokenizeException {
		try {
			while(base.feed(stream.read()));
		} catch (IOException e) {
			throw new TokenizeException(e, base.line, base.column, info);
		}
	}
	
	public TokenGroup finish(){
		return base.finish();
	}
	
}
