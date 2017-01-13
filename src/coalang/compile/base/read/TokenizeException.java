package coalang.compile.base.read;

public class TokenizeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5176939372951977958L;

	public TokenizeException(Exception base, int line, int column, String info){
		super(info + "(" + line + ":" + column + ")", base);
	}
	
}
