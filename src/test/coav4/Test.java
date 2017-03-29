package test.coav4;

import io.github.coalangsoft.lang.context.CompileContext;
import io.github.coalangsoft.lang.token.TokenGroup;
import io.github.coalangsoft.lang.tree.Tree;
import io.github.coalangsoft.lang.visit.StandardVisitor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

import coalang.compile.base.read.TokenizeException;
import coalang.compile.base.read.Tokenizer;
import coalang.compile.systems.CommaSystem;
import coalang.compile.systems.DotSystem;
import coalang.compile.systems.EqualsSystem;
import coalang.compile.systems.KeywordSystem;
import coalang.compile.systems.SemicolonSystem;
import coalang.compile.systems.SpecialSystem;
import coalang.compile.systems.complex.BlockSystem;
import coalang.compile.systems.complex.GroupSystem;
import coalang.compile.systems.complex.StringSystem;
import coalang.compile.systems.value.NameLoadSystem;
import coalang.compile.systems.value.NumberSystem;

public class Test {
	
	public static void main(String[] args) throws FileNotFoundException, TokenizeException {
		
		String fname = args[0];
		
		Tokenizer t = new Tokenizer();
		t.stream(new FileInputStream(new File(fname)), fname);
		TokenGroup tokens = t.finish();
		
		Tree tree = new Tree(tokens);
		
		CompileContext c = new CompileContext();
		
		c.addCompileSystem(KeywordSystem.VAR);
		
		c.addCompileSystem(new NameLoadSystem());
		c.addCompileSystem(new NumberSystem());
		
		c.addCompileSystem(new DotSystem());
		c.addCompileSystem(new SemicolonSystem());
		c.addCompileSystem(new EqualsSystem());
		c.addCompileSystem(new CommaSystem());
		
		c.addCompileSystem(new GroupSystem());
		c.addCompileSystem(new BlockSystem());
		c.addCompileSystem(new StringSystem());
		
		SpecialSystem special = new SpecialSystem();
		special.add("-", "putI 0\nget sub");
		c.addCompileSystem(special);
		
		fname = fname.substring(0,fname.length() - 1);
		System.out.println(fname + "0");
		
		PrintStream s = new PrintStream(new File(fname + "0"));
		s.print(tree.createTree().visit(c, new StandardVisitor()));
		s.close();
		
	}
	
}
