package coalang.compile.visit;

import io.github.coalangsoft.lang.context.CompileContext;
import io.github.coalangsoft.lang.context.CompileSystem;
import io.github.coalangsoft.lang.tree.TreeItem;

public class Detect {

	public static DetectResult handle(CompileContext c, TreeItem item, boolean asValue) {
		for(int i = 0; i < c.compileSystemCount(); i++){
			CompileSystem s = c.getCompileSystem(i);
			if(s.accept(c, item, asValue)){
				return new DetectResult(s.compile(c, item, asValue));
			}
		}
		return new DetectResult(null);
	}

}
