.source GCTest.btm
.class public Main
.super java/lang/Object
.implements java/lang/Cloneable


; Main constructor
.method public <init>()V
	.limit stack 2
	.limit locals 1
	aload_0
	dup
	invokespecial java/lang/Object/<init>()V 
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



.method public original_main()V
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 5

	; local variable declaration: io at index 1
	; object instantiation: TextIO
	new TextIO 
	dup
	invokespecial TextIO/<init>()V 
	astore 1 

	; local variable declaration: v at index 2
	; object instantiation: Vector
	new Vector 
	dup
	invokespecial Vector/<init>()V 
	astore 2 

	; local variable declaration: i at index 3
	iconst_0
	istore 3 

	; while statement: boolean expression
   L0:
	iload 3 
	ldc 1000 
	isub
	ifge L1 

	; while statement: body

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-1\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; if statement: boolean expression
	iload 3 
	ldc 100 
	irem
	iconst_0
	isub
	ifeq L2 

	; else block
	goto L3 

	; then block
   L2:

	; method call: addElement
	aload 2 
	; object instantiation: A
	new A 
	dup
	invokespecial A/<init>()V 
	invokevirtual Vector/addElement(Ljava/lang/Object;)LVector; 
	pop
   L3:

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-2\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString

	; method call: putInt
	; object instantiation: TextIO
	new TextIO 
	dup
	invokespecial TextIO/<init>()V 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-3\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; local variable declaration: s at index 4
	ldc "abcdefhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789" 
	astore 4 

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-4\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop
	; object instantiation: Sys
	new Sys 
	dup
	invokespecial Sys/<init>()V 
	pop

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-5\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop
	; object instantiation: A
	new A 
	dup
	invokespecial A/<init>()V 
	pop

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-6\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: addElement
	aload 2 
	; object instantiation: TextIO
	new TextIO 
	dup
	invokespecial TextIO/<init>()V 
	invokevirtual Vector/addElement(Ljava/lang/Object;)LVector; 
	pop

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-7\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: putString

	; method call: putInt
	; object instantiation: TextIO
	new TextIO 
	dup
	invokespecial TextIO/<init>()V 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-8\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop
	; object instantiation: Sys
	new Sys 
	dup
	invokespecial Sys/<init>()V 
	pop

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-9\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: addElement
	aload 2 
	; object instantiation: Sys
	new Sys 
	dup
	invokespecial Sys/<init>()V 
	invokevirtual Vector/addElement(Ljava/lang/Object;)LVector; 
	pop

	; method call: putString

	; method call: putInt
	aload 1 
	iload 3 
	invokevirtual TextIO/putInt(I)LTextIO; 
	ldc "-10\n" 
	invokevirtual TextIO/putString(Ljava/lang/String;)LTextIO; 
	pop

	; method call: addElement
	aload 2 
	ldc "abc" 
	invokevirtual Vector/addElement(Ljava/lang/Object;)LVector; 
	pop

	; variable assignment: i
	iload 3 
	iconst_1
	iadd
	dup
	istore 3 
	pop
	goto L0 
   L1:
	return
.end method
