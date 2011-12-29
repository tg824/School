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


/** The <tt>VarExpr</tt> class represents variable expressions.
  * It contains the name of the variable.  Note: these may actually
  * be 'this', 'super', or 'null'.  Also, these may or may not 
  * include a reference object name (if the variable is a field).
  * Because fields are 'protected' in Bantam Java, the reference
  * object name must always be either 'this' or 'super'.
  * @see ASTNode
  * @see Expr
  * */
public class VarExpr extends Expr {
    /** The optional name of the reference (must be 'this' or 'super') */
    protected String refName;

    /** The name of the variable (possibly 'this', 'super', or 'null') */
    protected String name;
    
    /** VarExpr constructor
      * @param lineNum source line number corresponding to this AST node
      * @param refName the refName optional name of the reference (must be 'this' or 'super')
      * @param name the name of the variable
      * */
    public VarExpr(int lineNum, String refName, String name) {
	super(lineNum);
	this.refName = refName;
	this.name = name;
    }
    
    /** Get the name of the reference object 
      * Only applicable if variable is a field (otherwise this returns null)
      * @return reference name
      * */
    public String getRefName() { return refName; }
    
    /** Get the name of the variable
      * @return name
      * */
    public String getName() { return name; }
    
    /** Visitor method
      * @param v visitor object
      * @return result of visiting this node
      * @see visitor.Visitor
      * */
    public Object accept(Visitor v) {
	return v.visit(this);
    }
}
