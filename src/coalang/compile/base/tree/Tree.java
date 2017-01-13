package coalang.compile.base.tree;

import io.github.coalangsoft.lang.token.TokenType;
import io.github.coalangsoft.lang.tree.TreeItem;
import io.github.coalangsoft.lang.tree.TreeItemType;

import java.util.List;

import coalang.compile.base.token.TokenGroup;
import coalang.compile.base.tree.layer.DeepExtracter;
import coalang.compile.base.tree.layer.FlatExtracter;

public class Tree {
	
	private TokenGroup tokens;

	public Tree(TokenGroup tokens){
		this.tokens = tokens;
	}
	
	public TreeItem createTree(){
		TreeItem root = new TreeItem(TreeItemType.ROOT);
		
		List<TreeItem> l = tokens.asTreeItems();
		
		l = new FlatExtracter(TreeItemType.STRING, TokenType.STRING_MARK).call(l);
		l = new DeepExtracter(TreeItemType.GROUP, TokenType.ROUND_BRACKET_OPEN, TokenType.ROUND_BRACKET_CLOSE).call(l);
		l = new DeepExtracter(TreeItemType.BLOCK, TokenType.BRACKET_OPEN, TokenType.BRACKET_CLOSE).call(l);
		l = new DeepExtracter(TreeItemType.LIST, TokenType.SQUARE_BRACKET_OPEN, TokenType.SQUARE_BRACKET_CLOSE).call(l);
		
		for(int i = 0; i < l.size(); i++){
			root.add(l.get(i));
		}
		
		return root;
		
	}
	
}
