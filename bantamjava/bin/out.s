	.data
	.globl	gc_flag
	.globl	class_name_table
	.globl	Main_template
	.globl	String_template
	.globl	String_dispatch_table
gc_flag:
	.word	0
String_const_6:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	6
	.ascii	"Animal"
	.byte	0
	.align	2
String_const_12:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	6
	.ascii	"TextIO"
	.byte	0
	.align	2
String_const_10:
	.word	1
	.word	40
	.word	String_dispatch_table
	.word	21
	.ascii	"../tests/GoodTest.btm"
	.byte	0
	.align	2
String_const_5:
	.word	1
	.word	36
	.word	String_dispatch_table
	.word	19
	.ascii	"../tests/Animal.btm"
	.byte	0
	.align	2
String_const_9:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	5
	.ascii	"Hello"
	.byte	0
	.align	2
String_const_8:
	.word	1
	.word	20
	.word	String_dispatch_table
	.word	3
	.ascii	"Bat"
	.byte	0
	.align	2
String_const_1:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	6
	.ascii	"Object"
	.byte	0
	.align	2
String_const_11:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	4
	.ascii	"Main"
	.byte	0
	.align	2
String_const_0:
	.word	1
	.word	36
	.word	String_dispatch_table
	.word	16
	.ascii	"<built-in class>"
	.byte	0
	.align	2
String_const_7:
	.word	1
	.word	36
	.word	String_dispatch_table
	.word	16
	.ascii	"../tests/Bat.btm"
	.byte	0
	.align	2
String_const_3:
	.word	1
	.word	24
	.word	String_dispatch_table
	.word	6
	.ascii	"String"
	.byte	0
	.align	2
String_const_4:
	.word	1
	.word	20
	.word	String_dispatch_table
	.word	3
	.ascii	"Sys"
	.byte	0
	.align	2
String_const_2:
	.word	1
	.word	20
	.word	String_dispatch_table
	.word	0
	.ascii	""
	.byte	0
	.align	2
class_name_table:
	.word	String_const_1
	.word	String_const_3
	.word	String_const_4
	.word	String_const_6
	.word	String_const_8
	.word	String_const_11
	.word	String_const_12
Object_template:
	.word	0
	.word	12
	.word	Object_dispatch_table
String_template:
	.word	1
	.word	20
	.word	String_dispatch_table
	.word	0
	.word	0
Sys_template:
	.word	2
	.word	12
	.word	Sys_dispatch_table
Animal_template:
	.word	3
	.word	28
	.word	Animal_dispatch_table
	.word	0
	.word	0
	.word	0
	.word	0
Bat_template:
	.word	4
	.word	32
	.word	Bat_dispatch_table
	.word	0
	.word	0
	.word	0
	.word	0
	.word	0
Main_template:
	.word	5
	.word	20
	.word	Main_dispatch_table
	.word	0
	.word	0
TextIO_template:
	.word	6
	.word	20
	.word	TextIO_dispatch_table
	.word	0
	.word	0
Object_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
String_dispatch_table:
	.word	Object.clone
	.word	String.equals
	.word	String.toString
	.word	String.length
	.word	String.substring
	.word	String.concat
Sys_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
	.word	Sys.exit
	.word	Sys.time
	.word	Sys.random
Animal_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
	.word	Animal.changeStrength
	.word	Animal.getStrength
Bat_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
	.word	Animal.changeStrength
	.word	Animal.getStrength
Main_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
	.word	Main.main
	.word	Main.method3
	.word	Main.method1
	.word	Main.method4
	.word	Main.method2
TextIO_dispatch_table:
	.word	Object.clone
	.word	Object.equals
	.word	Object.toString
	.word	TextIO.readStdin
	.word	TextIO.readFile
	.word	TextIO.writeStdout
	.word	TextIO.writeStderr
	.word	TextIO.writeFile
	.word	TextIO.getString
	.word	TextIO.getInt
	.word	TextIO.putString
	.word	TextIO.putInt
	.text
	.globl	main
	.globl	Main_init
	.globl	Main.main
main:
	jal __start
Object_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
String_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	jal Object_init
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
Sys_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	jal Object_init
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
Animal_init:
	add $sp $sp -16
	sw $ra 12($sp)
	sw $fp 8($sp)
	sw $s4 4($sp)
	move $fp $sp
	move $s4 $a0
	jal Object_init
	li $v0 4
	sw $v0 12($s4)
	li $v0 -1
	sw $v0 16($s4)
	la $a0 Animal_template
	li $a1 4
	la $a2 String_const_5
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	sw $v0 20($s4)
	lw $v0 12($s4)
	sw $v0 0($fp)
	li $v0 10
	lw $t0 0($fp)
	mul $v0 $t0 $v0
	sw $v0 0($fp)
	li $v0 50
	lw $t0 0($fp)
	add $v0 $t0 $v0
	sw $v0 24($s4)
	move $v0 $s4
	lw $s4 4($sp)
	lw $fp 8($sp)
	lw $ra 12($sp)
	add $sp $sp 16
	jr $ra
Bat_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	jal Animal_init
	li $v0 -1
	sw $v0 28($s4)
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
Main_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	jal Object_init
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
TextIO_init:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	jal Object_init
	li $v0 1
	sw $v0 16($s4)
	move $v0 $s4
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
Animal.changeStrength:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	lw $v0 12($fp)
	sw $v0 24($s4)
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 16
	jr $ra
Animal.getStrength:
	add $sp $sp -12
	sw $ra 8($sp)
	sw $fp 4($sp)
	sw $s4 0($sp)
	move $fp $sp
	move $s4 $a0
	lw $v0 24($s4)
	lw $s4 0($sp)
	lw $fp 4($sp)
	lw $ra 8($sp)
	add $sp $sp 12
	jr $ra
Main.main:
	add $sp $sp -72
	sw $ra 68($sp)
	sw $fp 64($sp)
	sw $s4 60($sp)
	move $fp $sp
	move $s4 $a0
	li $v0 0
	sw $v0 0($fp)
	lw $v0 0($fp)
	sw $v0 4($fp)
	li $v0 7
	sw $v0 8($fp)
	la $v0 String_const_9
	sw $v0 12($fp)
	lw $v0 12($fp)
	sw $v0 16($fp)
	la $a0 Animal_template
	li $a1 19
	la $a2 String_const_10
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	sw $v0 20($fp)
	lw $v0 20($fp)
	sw $v0 24($fp)
	li $a1 20
	la $a2 String_const_10
	lw $a0 24($fp)
	bne $a0 $zero label0
	jal _null_pointer_error
label0:
	lw $t0 8($a0)
	lw $t0 16($t0)
	jalr $t0
	sw $v0 24($fp)
	lw $v0 20($fp)
	sw $v0 28($fp)
	li $v0 5
	sw $v0 -4($sp)
	sub $sp $sp 4
	li $a1 21
	la $a2 String_const_10
	lw $a0 28($fp)
	bne $a0 $zero label1
	jal _null_pointer_error
label1:
	lw $t0 8($a0)
	lw $t0 12($t0)
	jalr $t0
	lw $v0 20($fp)
	sw $v0 28($fp)
	li $v0 8
	sw $v0 0($fp)
	lw $v0 0($fp)
	sw $v0 32($fp)
	lw $v0 8($fp)
	lw $t0 32($fp)
	bne $v0 $zero label2
	li $a1 24
	la $a2 String_const_10
	jal _divide_zero_error
label2:
	div $v0 $t0 $v0
	sw $v0 0($fp)
	li $v0 5
	sw $v0 32($fp)
	li $v0 2
	lw $t0 32($fp)
	add $v0 $t0 $v0
	sw $v0 32($fp)
	lw $v0 8($fp)
	sw $v0 36($fp)
	li $v0 2
	lw $t0 36($fp)
	mul $v0 $t0 $v0
	sw $v0 8($fp)
	lw $v0 8($fp)
	sw $v0 36($fp)
	lw $v0 0($fp)
	lw $t0 36($fp)
	bne $v0 $zero label3
	li $a1 27
	la $a2 String_const_10
	jal _divide_zero_error
label3:
	rem $v0 $t0 $v0
	sw $v0 8($fp)
	li $v0 9
	sw $v0 0($fp)
	li $v0 0
	sw $v0 36($fp)
	li $v0 -1
	sw $v0 40($fp)
	lw $v0 0($fp)
	sw $v0 44($fp)
	lw $v0 8($fp)
	lw $t0 44($fp)
	beq $t0 $v0 label4
	li $v0 0
	b label5
label4:
	li $v0 -1
label5:
	sw $v0 44($fp)
	lw $v0 0($fp)
	sw $v0 48($fp)
	li $v0 5
	lw $t0 48($fp)
	beq $t0 $v0 label8
	li $v0 0
	b label9
label8:
	li $v0 -1
label9:
	beq $v0 $zero label6
	b label7
label6:
label7:
	li $v0 -1
	sw $v0 48($fp)
	lw $v0 48($fp)
	sw $v0 52($fp)
	lw $s4 60($sp)
	lw $fp 64($sp)
	lw $ra 68($sp)
	add $sp $sp 72
	jr $ra
Main.method3:
	add $sp $sp -20
	sw $ra 16($sp)
	sw $fp 12($sp)
	sw $s4 8($sp)
	move $fp $sp
	move $s4 $a0
	la $a0 Animal_template
	li $a1 61
	la $a2 String_const_10
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	sw $v0 0($fp)
	li $v0 6
	sw $v0 4($fp)
	la $a0 Animal_template
	li $a1 63
	la $a2 String_const_10
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	lw $s4 8($sp)
	lw $fp 12($sp)
	lw $ra 16($sp)
	add $sp $sp 20
	jr $ra
Main.method1:
	add $sp $sp -16
	sw $ra 12($sp)
	sw $fp 8($sp)
	sw $s4 4($sp)
	move $fp $sp
	move $s4 $a0
	li $v0 7
	sw $v0 0($fp)
	lw $v0 0($fp)
	lw $s4 4($sp)
	lw $fp 8($sp)
	lw $ra 12($sp)
	add $sp $sp 20
	jr $ra
Main.method4:
	add $sp $sp -16
	sw $ra 12($sp)
	sw $fp 8($sp)
	sw $s4 4($sp)
	move $fp $sp
	move $s4 $a0
	la $a0 Animal_template
	li $a1 73
	la $a2 String_const_10
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	sw $v0 0($fp)
	lw $v0 0($fp)
	beq $v0 $zero label11
	lw $t0 0($v0)
	li $t1 4
	bgt $t0 $t1 label10
	li $t1 4
	blt $t0 $t1 label10
	b label11
label10:
	li $a1 74
	la $a2 String_const_10
	jal _class_cast_error
label11:
	lw $s4 4($sp)
	lw $fp 8($sp)
	lw $ra 12($sp)
	add $sp $sp 16
	jr $ra
Main.method2:
	add $sp $sp -24
	sw $ra 20($sp)
	sw $fp 16($sp)
	sw $s4 12($sp)
	move $fp $sp
	move $s4 $a0
	la $a0 Animal_template
	li $a1 79
	la $a2 String_const_10
	jal Object.clone
	move $a0 $v0
	jal Animal_init
	sw $v0 0($fp)
	li $v0 5
	sw $v0 4($fp)
	li $v0 0
	sw $v0 8($fp)
	lw $v0 8($fp)
	lw $s4 12($sp)
	lw $fp 16($sp)
	lw $ra 20($sp)
	add $sp $sp 24
	jr $ra
