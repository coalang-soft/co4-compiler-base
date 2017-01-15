package test.coav4;

import io.github.coalangsoft.lang.context.CompileContext;
import io.github.coalangsoft.lang.token.TokenType;
import io.github.coalangsoft.lib.data.Func;
import io.github.coalangsoft.lib.sequence.BaseSequence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import coalang.compile.base.read.TokenizeException;
import coalang.compile.base.read.Tokenizer;
import coalang.compile.base.token.TokenGroup;
import coalang.compile.base.tree.Tree;
import coalang.compile.systems.DotSystem;
import coalang.compile.systems.NameLoadSystem;
import coalang.compile.visit.StandardVisitor;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException, TokenizeException {
		
		Tokenizer t = new Tokenizer();
		t.stream(new FileInputStream(new File("test.txt")), "test.txt");
		TokenGroup tokens = t.finish();
		
		Tree tree = new Tree(tokens);
		
		CompileContext c = new CompileContext();
		c.addCompileSystem(new NameLoadSystem());
		c.addCompileSystem(new DotSystem());
		
		System.out.println(tree.createTree().visit(c, new StandardVisitor()));
		
	}
	
}
