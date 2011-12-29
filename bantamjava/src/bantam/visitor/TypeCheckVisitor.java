package visitor;

import ast.*;
import util.*;
import java.util.Iterator;
import java.util.ArrayList;

//If the type is not boolean, int, or Object
//Search the heriarchy tree for a class named that type
//if it's there, hooray
//if not, too bad so sad.
public class TypeCheckVisitor extends Visitor
{
	ErrorHandler error = new ErrorHandler();
	ClassTreeNode currNode;
	SymbolTable methods, variables;
	
	public TypeCheckVisitor(ClassTreeNode currNode)
	{
		this.currNode = currNode;
		methods= currNode.getMethodSymbolTable();
		variables = currNode.getVarSymbolTable();
	}
	
	public Object visit(ClassTreeNode node)
	{
		node.getASTNode().getMemberList().accept(this);
		error.checkErrors();
		return null;
	}
	
	public Object visit(Field node)
	{
		methods.enterScope();
		variables.enterScope();
		if(variables.lookup(node.getName()) == null){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Field "+node.getName()+" not found");
			return null;
		}
		String type = node.getType();
		if(node.getInit() == null)
			return null;
		node.getInit().accept(this);
		if(!(type.equals(node.getInit().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Left hand type "+node.getType()+ "does not match right hand expression type "
			+node.getInit().getExprType());
			return null;
		}
		return null;
	}
	public Object visit(Method node)
	{
		
		boolean returnChecked = false;
		methods.enterScope();
		variables.enterScope();
		Method lookedUpMethod = (Method)(methods.lookup(node.getName()));
		if(lookedUpMethod == null)
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Method "+ node.getName() +"not found");
			return null;
		}
		String givenType = (String)(lookedUpMethod.getReturnType());
		if(givenType == null)
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Return type not found");
		String returnType  = node.getReturnType();
		if(givenType.equals(returnType))
		;
		else
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),  "Return types do not match");
		Return retnStmt = node.getReturnStmt();
		node.getFormalList().accept(this);
		node.getStmtList().accept(this);
		node.getReturnStmt().accept(this);
		if(node.getReturnStmt().getExpr() == null && returnType.equals("void")){
			returnChecked = true;
			return null;
		}
		if(node.getReturnStmt().getExpr() == null && !(returnType.equals("void"))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Method "+node.getName()+
				" claims to return "+givenType+" and does not");
				return null;
		}
		if(node.getReturnStmt().getExpr() != null && node.getReturnStmt().getExpr().getExprType().equals(givenType))
			returnChecked = true;
		
		if(returnChecked == false)
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Method "+node.getName()+
				" claims to return "+givenType+" and does not");
		}
		return null;
	}
	
	public Object visit(Formal node)
	{
		return null;
	}
	public Object visit(DeclStmt node)
	{
		String expectedType = node.getType();
		node.getInit().accept(this);
		checkValidity(node.getInit().getExprType());
		
		if(!(node.getInit().getExprType().equals(expectedType))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type "+expectedType+" and righthand declaration type "
			+node.getInit().getExprType()+" do not match");
		}
		return null;
	}
	
	public Object visit(Return node)
	{
		if(node.getExpr() != null)
			node.getExpr().accept(this);
		return null;
	}
	
	public Object visit(VarExpr node)
	{
		node.setExprType((String)(variables.lookup(node.getName())));
		checkValidity((String)(variables.lookup(node.getName())));
		return null;
	}
	public Object visit(NewExpr node)
	{
		node.setExprType(node.getType());
		checkValidity(node.getType());
		return null;
	}
	
	public Object visit(BinaryArithDivideExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("int") && node.getRightExpr().getExprType().equals("int")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary arithmetic on something other than an int");
			return null;
		}
		node.setExprType("int");
		return null;
	}
	
	
	
	public Object visit(BinaryArithMinusExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("int") && node.getRightExpr().getExprType().equals("int")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary arithmetic on something other than an int");
			return null;
		}
		node.setExprType("int");
		return null;
	}
	
	
	public Object visit(BinaryArithModulusExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("int") && node.getRightExpr().getExprType().equals("int")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary arithmetic on something other than an int");
			return null;
		}
		node.setExprType("int");
		return null;
	}
	
	public Object visit(BinaryArithPlusExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("int") && node.getRightExpr().getExprType().equals("int")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary arithmetic on something other than an int");
			return null;
		}
		node.setExprType("int");
		return null;
	}
	
	public Object visit(BinaryArithTimesExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("int") && node.getRightExpr().getExprType().equals("int")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary arithmetic on something other than an int");
			return null;
		}
		node.setExprType("int");
		return null;
	}
	
	public Object visit(BinaryCompEqExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryCompGeqExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	
	public Object visit(BinaryCompGtExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryCompLeqExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryCompLtExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryCompNeExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryLogicAndExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("boolean") && node.getRightExpr().getExprType().equals("boolean")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary logic on something other than boolean");
			return null;
		}
		return null;
	}
	
	public Object visit(BinaryLogicOrExpr node)
	{
		node.getLeftExpr().accept(this);
		node.getRightExpr().accept(this);
		node.setExprType("boolean");
		
		if(!(node.getLeftExpr().getExprType().equals(node.getRightExpr().getExprType()))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand type: "+node.getLeftExpr().getExprType() +" does not match right hand type: "+
			node.getRightExpr().getExprType());
			return null;
		}
		if(!(node.getLeftExpr().getExprType().equals("boolean") && node.getRightExpr().getExprType().equals("boolean")))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting binary logic on something other than boolean");
			return null;
		}
		return null;
	}
	
	public Object visit(UnaryNegExpr node)
	{
		node.getExpr().accept(this);
		node.setExprType("int");
		if(!(node.getExpr().getExprType().equals("int"))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting Unary Negation on something other than int");
			return null;
		}
		return null;
	}
	
	
	public Object visit(UnaryNotExpr node)
	{
		node.getExpr().accept(this);
		node.setExprType("boolean");
		if(!(node.getExpr().getExprType().equals("boolean"))){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Attempting Unary Not on something other than boolean");
			return null;
		}
		return null;
	}
			
	public Object visit(InstanceofExpr node)
	{
		node.getExpr().accept(this);
		if(node.getExpr().getExprType().equals("int") 
		||node.getExpr().getExprType().equals("boolean") 
		||node.getExpr().getExprType().equals("String"))
		{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Cannot perform instanceof comparison on "+node.getExpr().getExprType());
			return null;
		}
		if(node.getType().equals(node.getExpr().getExprType()))
			node.setExprType(node.getType());
		else{
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Left-hand type"+node.getExpr().getExprType()+" does not match right-hand type "+
			node.getType());
			return null;
		}
		
		return null;
	}	
	public Object visit(CastExpr node)
	{
		node.getExpr().accept(this);
		node.setExprType(node.getType());
		checkValidity(node.getType());
		return null;
	}
	
	public Object visit(ConstIntExpr node)
	{
		node.setExprType("int");
		return null;
	}
	
	public Object visit(ConstBooleanExpr node)
	{
		node.setExprType("boolean");
		return null;
	}
	
	public Object visit(ConstStringExpr node)
	{
		node.setExprType("String");
		return null;
	}
	
	public Object visit(AssignExpr node)
	{
		
		String name = node.getName();
		if(variables.lookup(name) == null){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(), "Variable "+node.getName()+
			" not found.");
			return null;
		}
		node.getExpr().accept(this);
		node.setExprType((String)(variables.lookup(name)));
		checkValidity((String)(variables.lookup(name)));
		if(!(node.getExpr().getExprType().equals(variables.lookup(name)))) {
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Lefthand expression type "+variables.lookup(name)+" and righthand expression type "
			+node.getExpr().getExprType()+" do not match");
		}
		
		return null;
	}
	
	public Object visit(DispatchExpr node)
	{
		
		String methodName = node.getMethodName();
		node.getRefExpr().accept(this);
		//lookup to see if method exists
		String type = node.getRefExpr().getExprType();
		if(node.getRefExpr().getExprType() == null)
			type = "this";
		if(type != null)
		{
			Method m = (Method)(methods.lookup(methodName));
			if(m == null)
			{
				error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
				"Method "+methodName+" not found");		
				node.setExprType("N/A");
				return null;
			}
			String mType = m.getReturnType();
			if(mType != null)
				node.getRefExpr().setExprType(mType);
			else
				node.getRefExpr().setExprType("void");
		}
		node.getActualList().accept(this);
		ArrayList<String> expectedParams = new ArrayList<String>();	
		ArrayList<String> givenParams = new ArrayList<String>();
		for (Iterator it = node.getActualList().getIterator(); it.hasNext(); )
		{
			Expr e = (Expr)(it.next());
			e.accept(this);
			String theType = e.getExprType();
			givenParams.add(theType);
		}
		Method desiredMethod = (Method)(methods.lookup(methodName));
		if(desiredMethod == null){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
				"Method "+methodName+" not found");
			node.setExprType("N/A");
			return null;
		}
		for(Iterator it = desiredMethod.getFormalList().getIterator(); it.hasNext();)
		{
			Formal f = (Formal)(it.next());
			f.accept(this);
			String theType = f.getType();
			expectedParams.add(theType);
		}
		
		if(expectedParams.size() != givenParams.size()){
			error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
			"Expected Parameters do not match");
		}
		else{
		for(int i = 0; i < expectedParams.size(); i++)
		{
			if(!(givenParams.get(i).equals(expectedParams.get(i))))
				error.register(error.SEMANT_ERROR, currNode.getASTNode().getFilename(), node.getLineNum(),
				"Expected parameter type "+expectedParams.get(i) +" does not match given"
				+" parameter type "+givenParams.get(i));
		}
		}
		node.setExprType(desiredMethod.getReturnType());
		checkValidity(desiredMethod.getReturnType());
			return null;
	}
	//Checks to see if the type is valid (Used mainly for custom-built objects)
	boolean checkValidity(String type)
	{
		if(type.equals("int") || type.equals("boolean") || type.equals("String") || type.equals("void"))
			return true;
		ClassTreeNode desiredObject = currNode.lookupClass(type);
		if(desiredObject == null)
		{
			error.register(error.SEMANT_ERROR, "Attempting to manipulate/create an undefined "
			+"Object "+type);
			return false;
		}
		
		else return true;
	}
	
	
	
}