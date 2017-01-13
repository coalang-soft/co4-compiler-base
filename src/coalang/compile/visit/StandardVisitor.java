package coalang.compile.visit;

import io.github.coalangsoft.lang.context.CompileContext;
import io.github.coalangsoft.lang.tree.TreeItem;
import io.github.coalangsoft.lib.data.Func;
import io.github.coalangsoft.lib.data.Pair;

public class StandardVisitor implements Func<Pair<TreeItem, CompileContext>, String>{

	private TreeItem current;
	private StringBuilder builder;
	
	public String call(Pair<TreeItem, CompileContext> p) {
		current = new TreeItem(null);
		builder = new StringBuilder();
		
		for(int i = 0; i < p.getA().length(); i++){
			visit(p.getA().at(i), p.getB());
		}
		
		if(current.length() > 0){
			current.makeException("Uncompiled Code! " + current);
		}
		return builder.toString();
	}

	private void visit(TreeItem item, CompileContext context) {
		System.out.println("visiting " + item);
		
		current.add(item);
		
		DetectResult r = Detect.handle(context, current, false);
		if(r.isCompiled()){
			builder.append(r.compiled());
			current.clear();
		}
	}

}
