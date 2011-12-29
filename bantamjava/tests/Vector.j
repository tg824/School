.source Vector.btm
.class public Vector
.super java/lang/Object
.implements java/lang/Cloneable

.field protected io LTextIO;
.field protected first LVectorElement;
.field protected last LVectorElement;
.field protected size I

; Vector constructor
.method public <init>()V
	.limit stack 4
	.limit locals 1
	aload_0
	dup
	invokespecial java/lang/Object/<init>()V 

	; field initialization: io
	aload_0
	; object instantiation: TextIO
	new TextIO 
	dup
	invokespecial TextIO/<init>()V 
	putfield Vector/io LTextIO; 

	; field initialization: first
	aload_0
	aconst_null
	putfield Vector/first LVectorElement; 

	; field initialization: last
	aload_0
	aconst_null
	putfield Vector/last LVectorElement; 

	; field initialization: size
	aload_0
	iconst_0
	putfield Vector/size I 
	return
.end method

.method static public main([Ljava.lang.String;)V
	.limit stack 2
	.limit locals 1
	new Main
	dup
	invokespecial Main/<init>()V
	invokevirtual Main/original_main()V
	return
.end method



.method public error(Ljava/lang/String;)V
	.throws java/lang/CloneNotSupportedException

	.limit stack 2
	.limit locals 2

	; method call: putString

	; method call: putString

	; method call: putString
	aload_0
	getfield Vector/io LTextIO; 
	ldc "Vector error: " 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	aload 1 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	ldc "\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: exit
	; object instantiation: Sys
	new Sys 
	dup
	invokespecial Sys/<init>()V 
	iconst_1
	invokevirtual Sys/exit(I)V 
	return
.end method

.method public checkIndex(ILjava/lang/String;Z)V
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 4

	; if statement: boolean expression
	iconst_1
	iload 3 
	isub
	ifne L0 

	; else block

	; if statement: boolean expression
	iload 1 
	iconst_0
	if_icmplt L7 
	iconst_0
	goto L8 
   L7:
	iconst_1
   L8:
	ifne L4 
	iload 1 
	aload_0
	getfield Vector/size I 
	if_icmpgt L9 
	iconst_0
	goto L10 
   L9:
	iconst_1
   L10:
	ifne L4 
	goto L5 
   L4:
	iconst_1
	goto L6 
   L5:
	iconst_0
   L6:
	ifne L2 

	; else block
	goto L3 

	; then block
   L2:

	; method call: putString

	; method call: putInt

	; method call: putString
	aload_0
	getfield Vector/io LTextIO; 
	ldc "Bad index '" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	iload 1 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "'\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString
	aload_0
	getfield Vector/io LTextIO; 
	ldc "Index must be >= 0 and <= size '" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString

	; method call: putInt
	aload_0
	getfield Vector/io LTextIO; 
	aload_0
	getfield Vector/size I 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "'\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: error
	aload_0

	; method call: concat
	ldc "index out of bounds in method " 
	aload 2 
	invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String; 
	invokevirtual Vector/error(Ljava/lang/String;)V 
   L3:
	goto L1 

	; then block
   L0:

	; if statement: boolean expression
	iload 1 
	iconst_0
	if_icmplt L16 
	iconst_0
	goto L17 
   L16:
	iconst_1
   L17:
	ifne L13 
	iload 1 
	aload_0
	getfield Vector/size I 
	if_icmpge L18 
	iconst_0
	goto L19 
   L18:
	iconst_1
   L19:
	ifne L13 
	goto L14 
   L13:
	iconst_1
	goto L15 
   L14:
	iconst_0
   L15:
	ifne L11 

	; else block
	goto L12 

	; then block
   L11:

	; method call: putString

	; method call: putInt

	; method call: putString
	aload_0
	getfield Vector/io LTextIO; 
	ldc "Bad index '" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	iload 1 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "'\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString
	aload_0
	getfield Vector/io LTextIO; 
	ldc "Index must be >= 0 and < size '" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString

	; method call: putInt
	aload_0
	getfield Vector/io LTextIO; 
	aload_0
	getfield Vector/size I 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "'\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: error
	aload_0

	; method call: concat
	ldc "index out of bounds in method " 
	aload 2 
	invokevirtual java/lang/String/concat(Ljava/lang/String;)Ljava/lang/String; 
	invokevirtual Vector/error(Ljava/lang/String;)V 
   L12:
   L1:
	return
.end method

.method public size()I
	.throws java/lang/CloneNotSupportedException

	.limit stack 1
	.limit locals 1
	aload_0
	getfield Vector/size I 
	ireturn
.end method

.method public contains(Ljava/lang/Object;)Z
	.throws java/lang/CloneNotSupportedException

	.limit stack 2
	.limit locals 4

	; local variable declaration: e at index 2
	aload_0
	getfield Vector/first LVectorElement; 
	astore 2 

	; local variable declaration: found at index 3
	iconst_0
	istore 3 

	; while statement: boolean expression
   L20:
	aload 2 
	aconst_null
	if_acmpne L25 
	iconst_0
	goto L26 
   L25:
	iconst_1
   L26:
	ifeq L23 
	iconst_1
	iload 3 
	isub
	ifeq L23 
	goto L22 
   L22:
	iconst_1
	goto L24 
   L23:
	iconst_0
   L24:
	ifeq L21 

	; while statement: body

	; if statement: boolean expression

	; method call: getElement
	aload 2 
	invokevirtual VectorElement/getElement()Ljava/lang/Object; 
	aload 1 
	if_acmpeq L27 

	; else block
	goto L28 

	; then block
   L27:

	; variable assignment: found
	iconst_1
	dup
	istore 3 
	pop
   L28:

	; variable assignment: e

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup
	astore 2 
	pop
	goto L20 
   L21:
	iload 3 
	ireturn
.end method

.method public addElement(Ljava/lang/Object;)LVector;
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 3

	; local variable declaration: newLast at index 2

	; method call: setElement
	; object instantiation: VectorElement
	new VectorElement 
	dup
	invokespecial VectorElement/<init>()V 
	aload 1 
	invokevirtual VectorElement/setElement(Ljava/lang/Object;)LVectorElement; 
	astore 2 

	; if statement: boolean expression
	aload_0
	getfield Vector/first LVectorElement; 
	aconst_null
	if_acmpeq L29 

	; else block

	; method call: setNext
	aload_0
	getfield Vector/last LVectorElement; 
	aload 2 
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
	goto L30 

	; then block
   L29:

	; variable assignment: first
	aload_0
	aload 2 
	dup_x1
	putfield Vector/first LVectorElement; 
	pop
   L30:

	; variable assignment: last
	aload_0
	aload 2 
	dup_x1
	putfield Vector/last LVectorElement; 
	pop

	; variable assignment: size
	aload_0
	aload_0
	getfield Vector/size I 
	iconst_1
	iadd
	dup_x1
	putfield Vector/size I 
	pop
	aload_0
	areturn
.end method

.method public removeElement(Ljava/lang/Object;)LVector;
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 5

	; local variable declaration: e at index 2
	aload_0
	getfield Vector/first LVectorElement; 
	astore 2 

	; local variable declaration: prev at index 3
	aconst_null
	astore 3 

	; local variable declaration: removed at index 4
	iconst_0
	istore 4 

	; while statement: boolean expression
   L31:
	aload 2 
	aconst_null
	if_acmpne L36 
	iconst_0
	goto L37 
   L36:
	iconst_1
   L37:
	ifeq L34 
	iconst_1
	iload 4 
	isub
	ifeq L34 
	goto L33 
   L33:
	iconst_1
	goto L35 
   L34:
	iconst_0
   L35:
	ifeq L32 

	; while statement: body

	; if statement: boolean expression

	; method call: getElement
	aload 2 
	invokevirtual VectorElement/getElement()Ljava/lang/Object; 
	aload 1 
	if_acmpeq L38 

	; else block
	goto L39 

	; then block
   L38:

	; if statement: boolean expression
	aload 3 
	aconst_null
	if_acmpeq L40 

	; else block

	; if statement: boolean expression
	aload 2 
	aload_0
	getfield Vector/last LVectorElement; 
	if_acmpeq L42 

	; else block

	; method call: setNext
	aload 3 

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
	goto L43 

	; then block
   L42:

	; method call: setNext
	aload 3 
	aconst_null
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
   L43:
	goto L41 

	; then block
   L40:

	; variable assignment: first
	aload_0

	; method call: getNext
	aload_0
	getfield Vector/first LVectorElement; 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup_x1
	putfield Vector/first LVectorElement; 
	pop
   L41:

	; if statement: boolean expression
	aload 2 
	aload_0
	getfield Vector/last LVectorElement; 
	if_acmpeq L44 

	; else block
	goto L45 

	; then block
   L44:

	; variable assignment: last
	aload_0
	aload 3 
	dup_x1
	putfield Vector/last LVectorElement; 
	pop
   L45:

	; variable assignment: removed
	iconst_1
	dup
	istore 4 
	pop

	; variable assignment: size
	aload_0
	aload_0
	getfield Vector/size I 
	iconst_1
	isub
	dup_x1
	putfield Vector/size I 
	pop
   L39:

	; variable assignment: prev
	aload 2 
	dup
	astore 3 
	pop

	; variable assignment: e

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup
	astore 2 
	pop
	goto L31 
   L32:

	; if statement: boolean expression
	iconst_1
	iload 4 
	isub
	ifne L46 

	; else block
	goto L47 

	; then block
   L46:

	; method call: error
	aload_0
	ldc "element not found in vector" 
	invokevirtual Vector/error(Ljava/lang/String;)V 
   L47:
	aload_0
	areturn
.end method

.method public elementAt(I)Ljava/lang/Object;
	.throws java/lang/CloneNotSupportedException

	.limit stack 4
	.limit locals 4

	; local variable declaration: e at index 2
	aload_0
	getfield Vector/first LVectorElement; 
	astore 2 

	; method call: checkIndex
	aload_0
	iload 1 
	ldc "elementAt" 
	iconst_0
	invokevirtual Vector/checkIndex(ILjava/lang/String;Z)V 

	; local variable declaration: j at index 3
	iconst_0
	istore 3 

	; while statement: boolean expression
   L48:
	iload 3 
	iload 1 
	isub
	ifge L49 

	; while statement: body

	; variable assignment: e

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup
	astore 2 
	pop

	; variable assignment: j
	iload 3 
	iconst_1
	iadd
	dup
	istore 3 
	pop
	goto L48 
   L49:

	; method call: getElement
	aload 2 
	invokevirtual VectorElement/getElement()Ljava/lang/Object; 
	areturn
.end method

.method public addElementAt(Ljava/lang/Object;I)LVector;
	.throws java/lang/CloneNotSupportedException

	.limit stack 4
	.limit locals 7

	; method call: checkIndex
	aload_0
	iload 2 
	ldc "addElementAt" 
	iconst_1
	invokevirtual Vector/checkIndex(ILjava/lang/String;Z)V 

	; if statement: boolean expression
	iload 2 
	aload_0
	getfield Vector/size I 
	isub
	ifeq L50 

	; else block

	; local variable declaration: newElement at index 3

	; method call: setElement
	; object instantiation: VectorElement
	new VectorElement 
	dup
	invokespecial VectorElement/<init>()V 
	aload 1 
	invokevirtual VectorElement/setElement(Ljava/lang/Object;)LVectorElement; 
	astore 3 

	; local variable declaration: e at index 4
	aload_0
	getfield Vector/first LVectorElement; 
	astore 4 

	; local variable declaration: prev at index 5
	aconst_null
	astore 5 

	; local variable declaration: j at index 6
	iconst_0
	istore 6 

	; while statement: boolean expression
   L52:
	iload 6 
	iload 2 
	isub
	ifge L53 

	; while statement: body

	; variable assignment: prev
	aload 4 
	dup
	astore 5 
	pop

	; variable assignment: e

	; method call: getNext
	aload 4 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup
	astore 4 
	pop

	; variable assignment: j
	iload 6 
	iconst_1
	iadd
	dup
	istore 6 
	pop
	goto L52 
   L53:

	; if statement: boolean expression
	aload 5 
	aconst_null
	if_acmpeq L54 

	; else block

	; method call: setNext
	aload 5 
	aload 3 
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
	goto L55 

	; then block
   L54:

	; variable assignment: first
	aload_0
	aload 3 
	dup_x1
	putfield Vector/first LVectorElement; 
	pop
   L55:

	; if statement: boolean expression
	aload_0
	getfield Vector/size I 
	iconst_0
	isub
	ifeq L56 

	; else block
	goto L57 

	; then block
   L56:

	; variable assignment: last
	aload_0
	aload_0
	getfield Vector/first LVectorElement; 
	dup_x1
	putfield Vector/last LVectorElement; 
	pop
   L57:

	; method call: setNext
	aload 3 
	aload 4 
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop

	; variable assignment: size
	aload_0
	aload_0
	getfield Vector/size I 
	iconst_1
	iadd
	dup_x1
	putfield Vector/size I 
	pop
	goto L51 

	; then block
   L50:

	; method call: addElement
	aload_0
	aload 1 
	invokevirtual Vector/addElement(Ljava/lang/Object;)LVector; 
	pop
   L51:
	aload_0
	areturn
.end method

.method public setElementAt(Ljava/lang/Object;I)LVector;
	.throws java/lang/CloneNotSupportedException

	.limit stack 4
	.limit locals 3

	; method call: checkIndex
	aload_0
	iload 2 
	ldc "setElementAt" 
	iconst_0
	invokevirtual Vector/checkIndex(ILjava/lang/String;Z)V 

	; method call: addElementAt
	aload_0
	aload 1 
	iload 2 
	invokevirtual Vector/addElementAt(Ljava/lang/Object;I)LVector; 
	pop

	; method call: removeElementAt
	aload_0
	iload 2 
	iconst_1
	iadd
	invokevirtual Vector/removeElementAt(I)LVector; 
	pop
	aload_0
	areturn
.end method

.method public removeElementAt(I)LVector;
	.throws java/lang/CloneNotSupportedException

	.limit stack 4
	.limit locals 5

	; method call: checkIndex
	aload_0
	iload 1 
	ldc "removeElementAt" 
	iconst_0
	invokevirtual Vector/checkIndex(ILjava/lang/String;Z)V 

	; local variable declaration: e at index 2
	aload_0
	getfield Vector/first LVectorElement; 
	astore 2 

	; local variable declaration: prev at index 3
	aconst_null
	astore 3 

	; local variable declaration: j at index 4
	iconst_0
	istore 4 

	; while statement: boolean expression
   L58:
	iload 4 
	iload 1 
	isub
	ifge L59 

	; while statement: body

	; variable assignment: prev
	aload 2 
	dup
	astore 3 
	pop

	; variable assignment: e

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup
	astore 2 
	pop

	; variable assignment: j
	iload 4 
	iconst_1
	iadd
	dup
	istore 4 
	pop
	goto L58 
   L59:

	; if statement: boolean expression
	aload 3 
	aconst_null
	if_acmpeq L60 

	; else block

	; if statement: boolean expression
	aload 2 
	aload_0
	getfield Vector/last LVectorElement; 
	if_acmpeq L62 

	; else block

	; method call: setNext
	aload 3 

	; method call: getNext
	aload 2 
	invokevirtual VectorElement/getNext()LVectorElement; 
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
	goto L63 

	; then block
   L62:

	; method call: setNext
	aload 3 
	aconst_null
	invokevirtual VectorElement/setNext(LVectorElement;)LVectorElement; 
	pop
   L63:
	goto L61 

	; then block
   L60:

	; variable assignment: first
	aload_0

	; method call: getNext
	aload_0
	getfield Vector/first LVectorElement; 
	invokevirtual VectorElement/getNext()LVectorElement; 
	dup_x1
	putfield Vector/first LVectorElement; 
	pop
   L61:

	; if statement: boolean expression
	aload 2 
	aload_0
	getfield Vector/last LVectorElement; 
	if_acmpeq L64 

	; else block
	goto L65 

	; then block
   L64:

	; variable assignment: last
	aload_0
	aload 3 
	dup_x1
	putfield Vector/last LVectorElement; 
	pop
   L65:

	; variable assignment: size
	aload_0
	aload_0
	getfield Vector/size I 
	iconst_1
	isub
	dup_x1
	putfield Vector/size I 
	pop
	aload_0
	areturn
.end method
