.source Vector.btm
.class public VectorElement
.super java/lang/Object
.implements java/lang/Cloneable

.field protected element Ljava/lang/Object;
.field protected next LVectorElement;

; VectorElement constructor
.method public <init>()V
	.limit stack 3
	.limit locals 1
	aload_0
	dup
	invokespecial java/lang/Object/<init>()V 

	; field initialization: element
	aload_0
	aconst_null
	putfield VectorElement/element Ljava/lang/Object; 

	; field initialization: next
	aload_0
	aconst_null
	putfield VectorElement/next LVectorElement; 
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



.method public getElement()Ljava/lang/Object;
	.throws java/lang/CloneNotSupportedException

	.limit stack 1
	.limit locals 1
	aload_0
	getfield VectorElement/element Ljava/lang/Object; 
	areturn
.end method

.method public setElement(Ljava/lang/Object;)LVectorElement;
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 2

	; variable assignment: element
	aload_0
	aload 1 
	dup_x1
	putfield VectorElement/element Ljava/lang/Object; 
	pop
	aload_0
	areturn
.end method

.method public getNext()LVectorElement;
	.throws java/lang/CloneNotSupportedException

	.limit stack 1
	.limit locals 1
	aload_0
	getfield VectorElement/next LVectorElement; 
	areturn
.end method

.method public setNext(LVectorElement;)LVectorElement;
	.throws java/lang/CloneNotSupportedException

	.limit stack 3
	.limit locals 2

	; variable assignment: next
	aload_0
	aload 1 
	dup_x1
	putfield VectorElement/next LVectorElement; 
	pop
	aload_0
	areturn
.end method
