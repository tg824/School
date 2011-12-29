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

package ast;

import visitor.*;

import java.util.*;

/** The <tt>Method</tt> class represents a method declaration within
  * a class declaration.  It contains the name of the method (<tt>name</tt>),
  * a list of formal parameters (<tt>formalList</tt>), the return type of the
  * method (<tt>returnType</tt>), a list of statements from the method body
  * (<tt>stmtList</tt>), and a return statement at the end of the method
  * body (<tt>returnStmt</tt>).
  * @see ASTNode
  * */
public class Method extends Member {
    /** The return type of the method */
    protected String returnType;
    
    /** The name of the method */
    protected String name;
    
    /** A list of formal parameters */
    protected FormalList formalList;
    
    /** A list of statements appearing in the method body */
    protected StmtList stmtList;
    
    /** A return statement appearing at the end of the method body */
    protected Return returnStmt;
    
    /** Method constructor
      * @param lineNum source line number corresponding to this AST node
      * @param returnType the return type of this method
      * @param name the name of this method
      * @param formalList a list of formal parameters
      * @param stmtList a list of statements appearing in the method body
      * @param returnStmt a return statement appearing at the end of the method body
      * */
    public Method(int lineNum, String returnType, String name, FormalList formalList, 
		  StmtList stmtList, Return returnStmt) {
	super(lineNum);
	this.returnType = returnType;
	this.name = name;
	this.formalList = formalList;
	this.stmtList = stmtList;
	this.returnStmt = returnStmt;
    }
    
    /** Get the return type of this method
      * @return return type of method
      * */
    public String getReturnType() { return returnType; }
    
    /** Get the name of this method
      * @return method name
      * */
    public String getName() { return name; }
    
    /** Get list of formal parameters
      * @return list of formal parameters
      * */
    public FormalList getFormalList() { return formalList; }
    
    /** Get list of statements from method body
      * @return list of statements
      * */
    public StmtList getStmtList() { return stmtList; }
    
    /** Get return statement from the end of the method body
      * @return return statement
      * */
    public Return getReturnStmt() { return returnStmt; }
    
    /** Visitor method
      * @param v visitor object
      * @return result of visiting this node
      * @see visitor.Visitor
      * */
    public Object accept(Visitor v) {
	return v.visit(this);
    }
}
