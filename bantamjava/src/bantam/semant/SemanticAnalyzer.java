/* Bantam Java Compiler and Language Toolset.

   Copyright (C) 2009 by Marc Corliss (corliss@hws.edu) and 
                         David Furcy (furcyd@uwosh.edu) and
                         E Christopher Lewis (lewis@vmware.com).
   ALL RIGHTS RESERVED.

   The Bantam Java toolset is distributed under the following 
   conditions:

     You may make copies of the toolset for your own use and 
     modify those copies.

     All copies of the toolset must retain the author names and 
     copyright notice.

     You may not sell the toolset or distribute it in 
     conjunction with a commerical product or service without 
     the expressed written consent of the authors.

   THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS 
   OR IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE 
   IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A 
   PARTICULAR PURPOSE. 
*/

package semant;

import ast.*;
import util.*;
import visitor.*;
import java.util.*;

/** The <tt>SemanticAnalyzer</tt> class performs semantic analysis.
  * In particular this class is able to perform (via the <tt>analyze()</tt>
  * method) the following tests and analyses: (1) legal inheritence
  * hierarchy (all classes have existing parent, no cycles), (2) 
  * legal class member declaration, (3) there is a correct Main class
  * and main() method, and (4) each class member is correctly typed.
  * 
  * This class is incomplete and will need to be implemented by the student.
  * */
public class SemanticAnalyzer {
    /** Root of the AST */
    private Program program;
    
    /** Root of the class hierarchy tree */
    private ClassTreeNode root;
    
    /** Maps class names to ClassTreeNode objects describing the class */
    private Hashtable<String,ClassTreeNode> classMap = new Hashtable<String,ClassTreeNode>();
    
    /** Object for error handling */
    private ErrorHandler errorHandler = new ErrorHandler();
    
    /** Boolean indicating whether debugging is enabled */
    private boolean debug = false;

    /** Maximum number of inherited and non-inherited fields that can be defined for 
	any one class */
    private final int MAX_NUM_FIELDS = 1500;

    /** SemanticAnalyzer constructor
      * @param program root of the AST
      * @param debug boolean indicating whether debugging is enabled
      * */
    public SemanticAnalyzer(Program program, boolean debug) {
	this.program = program;
	this.debug = debug;
    }
    
    /** Analyze the AST checking for semantic errors and annotating the tree
      * Also builds an auxiliary class hierarchy tree 
      * @return root of the class hierarchy tree (needed for code generation)
      *
      * Must add code to do the following:
      *   1 - build built-in class nodes in class hierarchy tree (already done)
      *   2 - build and check the class hierarchy tree
      *   3 - build the environment for each class (adding class members only) and check
      *       that members are declared properly
      *   4 - check that the Main class and main method are declared properly
      *   5 - type check each class member
      * See the lab manual for more details on each of these steps.
      * */
    public ClassTreeNode analyze() {
	// 1 - add built in classes to class tree
	updateBuiltins();

	// comment out
	//throw new RuntimeException("Semantic analyzer unimplemented");

	// add code below...
	//Get an Iterator for class list
	//Create Class Tree
	ArrayList<ClassTreeNode> classes = constructTree();
	
//	if(main != null	&& !(main.getReturnType.equals("void")))
//		throw new RuntimeException("Error: main must return a void type");
	
		//Check main method, void type, no parameters
	printTree();
	//int x = 3;
	SymbolTable t1 = new SymbolTable();
	SymbolTable t2 = new SymbolTable();
	MyVisitor visitor = new MyVisitor(root);
	for(int i = 0; i < classes.size(); i++)
	{
		visitor.visit(classes.get(i));
	t1 = visitor.getMethodSymbolTable();
	t2 = visitor.getVarSymbolTable();
	}
	ClassTreeNode main = root.lookupClass("Main");
	if(main == null)
		throw new RuntimeException("Error: at least one class must be Main");
	else
	{
		SymbolTable mainTable = main.getMethodSymbolTable();
		mainTable.enterScope();
		Method mainMethod = (Method)(mainTable.lookup("main"));
		if(!(mainMethod.getReturnType().equals("void")))
			errorHandler.register(errorHandler.SEMANT_ERROR, "main method must return void type");
		if(!(mainMethod.getFormalList().getSize()==0))
			errorHandler.register(errorHandler.SEMANT_ERROR, "main method must not have any parameters");
	}
	errorHandler.checkErrors();
	
	TypeCheckVisitor typeChecker = new TypeCheckVisitor(main);
	for(int i = 3; i < classes.size(); i++)
		typeChecker.visit(classes.get(i));
	//Update ;
	// uncomment out
	 return root;
    }

    /** Add built in classes to the class tree 
      * */
      
    private ArrayList<ClassTreeNode> constructTree()
    {
    	ArrayList<ClassTreeNode> listOfClasses = new ArrayList<ClassTreeNode>();
    	ClassTreeNode workingNode = root;
    	ClassList classes = program.getClassList();
    	ClassTreeNode string = root.lookupClass("String");
		ClassTreeNode textio = root.lookupClass("TextIO");
		ClassTreeNode sys = root.lookupClass("Sys");
		root.addChild(string);
		root.addChild(textio);
		root.addChild(sys);
		listOfClasses.add(root.lookupClass("Object"));
		listOfClasses.add(string);
		listOfClasses.add(textio);
		listOfClasses.add(sys);
		for (Iterator it = classes.getIterator(); it.hasNext(); )
		{
			//Retrieve class, construct ClassTreeNode
			Class_ c = (Class_)(it.next());
			String parentsName = c.getParent();
		//	System.out.println("Current Class: " + c.getName());	
		//	System.out.println("Parent's name: "+parentsName);

			ClassTreeNode child = new ClassTreeNode(c, false,
				true, classMap);
			
			listOfClasses.add(child);
			if(c.getParent().equals("Object"))
			{
				root.addChild(child);
				child.setParent(child.getParent());
				classMap.put(c.getName(), child);
			}
		}
		for(int i = 0; i < listOfClasses.size(); i ++)
		{
			if(!(listOfClasses.get(i).getName().equals("Object")) && root.lookupClass(listOfClasses.get(i).getASTNode().getParent()) != null)
				{
					ClassTreeNode parent = root.lookupClass(listOfClasses.get(i).getASTNode().getParent());
					parent.addChild(listOfClasses.get(i));
					listOfClasses.get(i).setParent(parent);
					classMap.put(listOfClasses.get(i).getName(), listOfClasses.get(i));
				}
		}
		
		//Put in Class Map first, then build tree little by little
		//Checks for repeated names
		if(listOfClasses.size() > 1)
		{
		
			for(int i = 0; i < listOfClasses.size(); i++)
			{
				for(int j = i+1; i < listOfClasses.size(); i++)
				{	
					if(listOfClasses.get(i).getName().equals(listOfClasses.get(j).getName()) && i != j)
						throw new RuntimeException("Two classes share the name "+listOfClasses.get(i).getName());
				}
			}
		}
		treeCheck(listOfClasses);
		return listOfClasses;
	}
	
	void treeCheck(ArrayList<ClassTreeNode> listOfClasses)
	{
		for(int i = 0; i < listOfClasses.size(); i++)
			if(root.lookupClass(listOfClasses.get(i).getName()) == null)
				throw new RuntimeException("Tree is not well formed. Class "+ listOfClasses.get(i).getName()+" is not found");
	}
		
    
    void printTree()
    {

    }
    	
    

    private void updateBuiltins() {
	// create AST node for object
	Class_ astNode = 
	    new Class_(-1, "<built-in class>", "Object", null, 
		       (MemberList)(new MemberList(-1))
		       .addElement(new Method(-1, "Object", "clone", 
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new VarExpr(-1, null, "null"))))
		       .addElement(new Method(-1, "boolean", "equals",
					      (FormalList)(new FormalList(-1))
					      .addElement(new Formal(-1, "Object", "o")),
					      new StmtList(-1),
					      new Return(-1, new ConstBooleanExpr(-1, "false"))))
		       .addElement(new Method(-1, "String", "toString", 
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new VarExpr(-1, null, "null")))));
	// create a class tree node for object, save in variable root
	root = new ClassTreeNode(astNode, /*built-in?*/true, /*extendable?*/true, classMap);
	// add object class tree node to the mapping
	classMap.put("Object", root);
	
	// note: String, TextIO, and Sys all have fields that are not shown below.  Because
	// these classes cannot be extended and fields are protected, they cannot be accessed by
	// other classes, so they do not have to be included in the AST.
	
	// create AST node for String
	astNode =
	    new Class_(-1, "<built-in class>",
		       "String", "Object",
		       (MemberList)(new MemberList(-1))
		       .addElement(new Field(-1, "int", "length", /*0 by default*/null))
		       /* note: str is the character sequence -- no applicable type for a
			  character sequence so it is just made an int.  it's OK to
			  do this since this field is only accessed (directly) within
			  the runtime system */
		       .addElement(new Field(-1, "int", "str", /*0 by default*/null))
                       .addElement(new Method(-1, "int", "length",
                                              new FormalList(-1), new StmtList(-1),
                                              new Return(-1, 
                                                         new ConstIntExpr(-1, "0"))))
		       .addElement(new Method(-1, "boolean", "equals",
					      (FormalList)(new FormalList(-1))
					      .addElement(new Formal(-1, "Object", "str")),
					      new StmtList(-1),
					      new Return(-1, 
							 new ConstBooleanExpr(-1, "false"))))
		       .addElement(new Method(-1, "String", "toString", 
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new VarExpr(-1, null, "null"))))
		       .addElement(new Method(-1, "String", "substring",
					      (FormalList)(new FormalList(-1))
					      .addElement(new Formal(-1, "int", 
								     "beginIndex"))
					      .addElement(new Formal(-1, "int", "endIndex")),
					      new StmtList(-1),
					      new Return(-1, new ConstStringExpr(-1, ""))))
		       .addElement(new Method(-1, "String", "concat",
					      (FormalList)(new FormalList(-1))
					      .addElement(new Formal(-1, "String",
								     "str")), 
					      new StmtList(-1),
					      new Return(-1, new ConstStringExpr(-1, "")))));
	// create class tree node for String, add it to the mapping
	classMap.put("String", new ClassTreeNode(astNode, /*built-in?*/true, /*extendable?*/false, classMap));
	
	// create AST node for TextIO
	astNode =
	    new Class_(-1, "<built-in class>", 
		       "TextIO", "Object", 					
		       (MemberList)(new MemberList(-1))
		       .addElement(new Field(-1, "int", "readFD", /*0 by default*/null))
		       .addElement(new Field(-1, "int", "writeFD", new ConstIntExpr(-1, "1")))
		       .addElement(new Method(-1, "void", "readStdin", 
					      new FormalList(-1), new StmtList(-1), new Return(-1, null)))
		       .addElement(new Method(-1, "void", "readFile",
					      (FormalList)(new FormalList(-1)).addElement(new Formal(-1, "String", 
												     "readFile")),
					      new StmtList(-1),
					      new Return(-1, null)))
		       .addElement(new Method(-1, "void", "writeStdout", 
					      new FormalList(-1), new StmtList(-1), new Return(-1, null)))
		       .addElement(new Method(-1, "void", "writeStderr", 
					      new FormalList(-1), new StmtList(-1), new Return(-1, null)))
		       .addElement(new Method(-1, "void", "writeFile",
					      (FormalList)(new FormalList(-1)).addElement(new Formal(-1, "String", 
												     "writeFile")),
					      new StmtList(-1),
					      new Return(-1, null)))
		       .addElement(new Method(-1, "String", "getString",
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new ConstStringExpr(-1, ""))))
		       .addElement(new Method(-1, "int", "getInt",
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new ConstIntExpr(-1, "0"))))
		       .addElement(new Method(-1, "TextIO", "putString",
					      (FormalList)(new FormalList(-1)).addElement(new Formal(-1, "String", 
												     "str")),
					      new StmtList(-1),
					      new Return(-1, new VarExpr(-1, null, "null"))))
		       .addElement(new Method(-1, "TextIO", "putInt",
					      (FormalList)(new FormalList(-1)).addElement(new Formal(-1, "int", 
												     "n")),
					      new StmtList(-1),
					      new Return(-1, new VarExpr(-1, null, "null")))));
	// create class tree node for TextIO, add it to the mapping
	classMap.put("TextIO", new ClassTreeNode(astNode, /*built-in?*/true, /*extendable?*/false, classMap));
	
	// create AST node for Sys
	astNode =
	    new Class_(-1, "<built-in class>",
		       "Sys", "Object", 
		       (MemberList)(new MemberList(-1))
		       .addElement(new Method(-1, "void", "exit",
					      (FormalList)(new FormalList(-1)).addElement(new Formal(-1, "int", 
												     "status")), 
					      new StmtList(-1),
					      new Return(-1, null)))
		       /* MC: time() and random() requires modifying SPIM to add a time system call
			  (note: random() does not need its own system call although it uses the time
			  system call).  We have a version of SPIM with this system call available,
			  otherwise, just comment out. (For x86 and jvm there are no issues.) */
		       .addElement(new Method(-1, "int", "time",
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new ConstIntExpr(-1, "0"))))
		       .addElement(new Method(-1, "int", "random",
					      new FormalList(-1), new StmtList(-1),
					      new Return(-1, new ConstIntExpr(-1, "0"))))
		       );
	// create class tree node for Sys, add it to the mapping
	classMap.put("Sys", new ClassTreeNode(astNode, /*built-in?*/true, /*extendable?*/false, classMap));
    }
}
