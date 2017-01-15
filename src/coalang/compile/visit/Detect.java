package coalang.compile.visit;

import io.github.coalangsoft.lang.context.CompileContext;
import io.github.coalangsoft.lang.context.CompileSystem;
import io.github.coalangsoft.lang.context.StateUpdateException;
import io.github.coalangsoft.lang.tree.TreeItem;

public class Detect {

	public static DetectResult handle(CompileContext c, TreeItem item, boolean asValue) {
		for(int i = 0; i < c.compileSystemCount(); i++){
			CompileSystem s = c.getCompileSystem(i);
			if(s.accept(c, item, asValue)){
				try {
					c.enterSystem(s);
				} catch (StateUpdateException e) {
					item.makeException(e.getMessage());
				}
				DetectResult r = new DetectResult(s.compile(c, item, asValue));
				c.exitSystem(s);
				return r;
			}
		}
		return new DetectResult(null);
	}

}
