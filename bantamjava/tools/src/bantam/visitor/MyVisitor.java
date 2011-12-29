//MyVisitor Class
//Hopefully will assemble class tree nodes from the visited/accepted nodes

package visitor;

import ast.*;
import util.*;
import java.util.Iterator;
import java.util.ArrayList;

public class MyVisitor extends Visitor
{
	SymbolTable variables;
	SymbolTable methods;
	ErrorHandler error = new ErrorHandler();
	ClassTreeNode currNode;
	public MyVisitor(ClassTreeNode currNode)
	{
	    this.currNode = currNode;
		variables = currNode.getVarSymbolTable();
		methods = currNode.getMethodSymbolTable();
	}
		
//Build Class environments: All the methods and Fields
//Build variable and method symbol tables
//Scope 0 
//make a visitor method that takes the root and two symbol tables as parameters?

	public Object visit(ASTNode node) {
	throw new RuntimeException("This visitor method should not be called (node is abstract)");
    }

    /** Visit a list node (should never be called)
      * @param node the list node
      * @return result of the visit 
      * */
    public Object visit(ListNode node) {
	throw new RuntimeException("This visitor method should not be called (node is abstract)");
    }
    //Starts traversing the tree, building symbol tables
	
	
	public Object visit(ClassTreeNode node)
	{
		methods.enterScope();
		variables.enterScope();
		node.getASTNode().getMemberList().accept(this);
		error.checkErrors();
		return null;
	}
	
    public Object visit(MemberList node) {
	for (Iterator it = node.getIterator(); it.hasNext(); )
	    ((Member)it.next()).accept(this);
	return null;
    }
	
    public Object visit(Member node) {
	throw new RuntimeException("This visitor method should not be called (node is abstract)");
    }
    
	public Object visit(Field node)
	{
		variables.enterScope();
		//methods.enterScope();
		if(variables.lookup(node.getName(), variables.getCurrScopeLevel()-1) != null)
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Field is already declared globally");
		else
				variables.add(node.getName(), node.getType());
			return null;
	}
	
	public Object visit(Method node)
	{
		variables.enterScope();
		methods.add(node.getName(), node);
		node.getFormalList().accept(this);
		node.getStmtList().accept(this);
		return null;
	}
	
	public Object visit(IfStmt node)
	{
		variables.enterScope();
		node.getPredExpr().accept(this);
		node.getThenStmt().accept(this);
		variables.exitScope();
		if (node.getElseStmt() != null) {
			variables.enterScope();
	    	node.getElseStmt().accept(this);
	    	variables.exitScope();
	    }
		return null;
	}
	
	public Object visit(WhileStmt node)
	{
		variables.enterScope();
		node.getPredExpr().accept(this);
		node.getBodyStmt().accept(this);
		variables.exitScope();
		return null; 		
	}
	
	public Object visit(BlockStmt node)
	{
		variables.enterScope();
		node.getStmtList().accept(this);
		variables.exitScope();
		return null;
	}

    public Object visit(Formal node) {
    if(variables.peek(node.getName()) == null)
    	variables.add(node.getName(), node.getType());
    else error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Field "+node.getName() +" is already defined");
	return null;
    }
	
	
    public Object visit(DeclStmt node) {
    boolean wasAdded = false;
    if(variables.peek(node.getName()) == null) {
    	for(int i = 0; i < variables.getCurrScopeLevel(); i ++){
    		if(variables.peek(node.getName(), i) == null){
    			if(i == variables.getCurrScopeLevel()-1){
    				variables.add(node.getName(), node.getType());
    				}
    			}
    		}
    	} 	 
   	 else
   	 	 error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Variable "+node.getName() +" is already defined");
	return null;
    }
    
    public Object visit(VarExpr node)
    {
    	return node;
    }
    public Object visit(AssignExpr node)
    {
    	if(variables.lookup(node.getName()) == null)
    		error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),  "Attempting assignment to an uninstantiated variable");
    	return null;
    }
    		

    /** Visit an expression statement node
      * @param node the expression statement node
      * @return result of the visit 
      * */
    public Object visit(ExprStmt node) { 
	node.getExpr().accept(this);
	return null; 
    }
    
    public Object visit(BinaryCompEqExpr node)
    {
    	return node;
    }
    
    
    public Object visit(BinaryCompGeqExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryCompGtExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryCompLeqExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryCompLtExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryCompNeExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryArithDivideExpr node)
    {
    	return node;
    }    
    public Object visit(BinaryArithMinusExpr node)
    {
    	return node;
    }    
    public Object visit(BinaryArithModulusExpr node)
    {
    	return node;
    }    
    public Object visit(BinaryArithPlusExpr node)
    {
    	return node;
    }    
    public Object visit(BinaryArithTimesExpr node)
    {
    	return node;
    }
    public Object visit(BinaryLogicOrExpr node)
    {
    	return node;
    }
        
    public Object visit(BinaryLogicAndExpr node)
    {
    	return node;
    }
    
    
    //public Object visit(VarExpr node) {
    	
    
    /** Visit an int constant expression node
      * @param node the int constant expression node
      * @return result of the visit 
      * */
    public Object visit(ConstIntExpr node) { 
	return node.getIntConstant(); 
    }

    /** Visit an if statement node
      * @param node the if statement node
      * @return result of the visit 
      * *
    public Object visit(IfStmt node) { 
	node.getPredExpr().accept(this);
	node.getThenStmt().accept(this);
	if (node.getElseStmt() != null)
	    node.getElseStmt().accept(this);
	return null; 
    }
   /*

    /** Visit a while statement node
      * @param node the while statement node
      * @return result of the visit 
      * 
    public Object visit(WhileStmt node) { 
	node.getPredExpr().accept(this);
	node.getBodyStmt().accept(this);
	return null; 
    }
    /*

    /** Visit a block statement node
      * @param node the block statement node
      * @return result of the visit 
      * *//*
*/
    /** Visit a return statement node
      * @param node the return statement node
      * @return result of the visit 
      * *//*
    public Object visit(Return node) { 
	if (node.getExpr() != null)
	    node.getExpr().accept(this);
	return null; 
    }*/
	
    
    
    public SymbolTable getVarSymbolTable()
    {
    	return variables;
    }
    
    
    public SymbolTable getMethodSymbolTable()
    {
    	return methods;
    }
    
    
      /** Visit a program node 
      * @param node the program node 
      * @return result of the visit 
      * */

}
    
    //Overwritten to return a class
    //Recursive descent?  Won't work all the time
    //How to synthesize 
    //Does the compiler require a null return (see PrintVisitor
    //Visit for program returns a classTreeNode?
    //Says the input for the Semantic Analyzer is the AST.  Where is it/can I use it
    //What is the point of the hash table
    //Will my class constructions be similar to the built-in ones
    //Are the built-in classes added to the root? Comments say no but It looks like they 
    //are in implementation
    //Maybe make an ArrayList of classes for easier access?
	


