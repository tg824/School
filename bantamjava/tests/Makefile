# some variables that can be set on command-line

TARGET=jvm
FLAGS=

# some variables for use below -- these may need to be customized

TESTS=	empty-program hello-world simple-calc repeat repeat-num \
	io file-io find-min strings super divide-by-zero mod-by-zero class-cast-error \
	io-read-error io-write-error substring-error1 substring-error2 substring-error3 \
	count-divisors fact fib primes primes-sieves vector-test \
	nim gc-test concat assign string-equals equals clone to-string \
	instanceof-cast-test
BTMC=../bin/bantamc
#../bin/bantamc-ref

JAR=jar
JASMIN=/usr/local/bantamjava/tools/jasmin-2.4/jasmin
RUNTIME=../lib

# check that target was specified correctly and set extension of output file


EXEFILES=${ASMFILES:.s=}
JSMFILES=${ASMFILES:.s=.j}

ifeq (${TARGET},mips)
	EXT=.asm
else
ifeq (${TARGET},x86)
	EXT=.bin
else 
ifeq (${TARGET},jvm)
	EXT=.jar
else
	TARGET=mips
	EXT=.asm
endif
endif
endif

# make all the tests by default

all:				${TESTS}

# rules for making each of the three targets

mips_rule:			
				${BTMC} ${FLAGS} -t mips ${M} ${A} -o ${N}.asm

x86_rule:			
				${BTMC} ${FLAGS} -t x86 ${M} ${A} -o ${N}.s
				gcc ${N}.s ../lib/runtime.s -o ${N}.bin

jvm_rule:			
				${BTMC} ${FLAGS} -t jvm ${M} ${A}
				${JASMIN} Main.j ${J}
				-cp ${RUNTIME}/*.class .
				echo "Manifest-Version: 1.0\nCreated-By: 1.6.0 (Sun Microsystems Inc.)\nMain-Class: Main" > manifest
				echo "${JAR} cfm ${N}.jar manifest Main.class TextIO.class Sys.class ${J:.j=.class}"
				${JAR} cfm ${N}.jar manifest Main.class TextIO.class Sys.class ${J:.j=.class}
				-rm *.class *.j manifest

# rules for each specific test that makes use of the three general rules above

${TESTS}:			
				@make --no-print-directory TARGET=${TARGET} FLAGS="${FLAGS}" $@${EXT}

empty-program${EXT}:		${BTMC} EmptyProgram.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=EmptyProgram.btm A="" J="" N=empty-program

hello-world${EXT}:		${BTMC} HelloWorld.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=HelloWorld.btm A="" J="" N=hello-world

simple-calc${EXT}:		${BTMC} SimpleCalc.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=SimpleCalc.btm A="" J="" N=simple-calc

repeat${EXT}:			${BTMC} Repeat.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Repeat.btm A="" J="" N=repeat

repeat-num${EXT}:		${BTMC} RepeatNum.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=RepeatNum.btm A="" J="" N=repeat-num

io${EXT}:			${BTMC} IO.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=IO.btm A="" J="" N=io

file-io${EXT}:			${BTMC} FileIO.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=FileIO.btm A="Vector.btm" J="Vector.j VectorElement.j" N=file-io

find-min${EXT}:			${BTMC} FindMin.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=FindMin.btm A="" J="" N=find-min

strings${EXT}:			${BTMC} Strings.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Strings.btm A="" J="" N=strings

super${EXT}:			${BTMC} Super.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Super.btm A="" J="A.j B.j C.j" N=super

divide-by-zero${EXT}:		${BTMC} DivideByZero.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=DivideByZero.btm A="" J="" N=divide-by-zero

mod-by-zero${EXT}:		${BTMC} ModByZero.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=ModByZero.btm A="" J="" N=mod-by-zero

class-cast-error${EXT}:		${BTMC} ClassCastError.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=ClassCastError.btm A="" J="" N=class-cast-error

io-read-error${EXT}:		${BTMC} IOReadError.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=IOReadError.btm A="" J="" N=io-read-error

io-write-error${EXT}:		${BTMC} IOWriteError.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=IOWriteError.btm A="" J="" N=io-write-error

substring-error1${EXT}:		${BTMC} SubStringError1.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=SubStringError1.btm A="" J="" N=substring-error1

substring-error2${EXT}:		${BTMC} SubStringError2.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=SubStringError2.btm A="" J="" N=substring-error2

substring-error3${EXT}:		${BTMC} SubStringError3.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=SubStringError3.btm A="" J="" N=substring-error3

count-divisors${EXT}:		${BTMC} CountDivisors.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=CountDivisors.btm A="" J="" N=count-divisors

fact${EXT}:			${BTMC} Fact.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Fact.btm A="" J="" N=fact

fib${EXT}:			${BTMC} Fib.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Fib.btm A="" J="" N=fib

primes${EXT}:			${BTMC} Primes.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Primes.btm A="" J="" N=primes

primes-sieves${EXT}:		${BTMC} PrimesSieves.btm Vector.btm Boolean.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=PrimesSieves.btm A="Vector.btm Boolean.btm" J="Vector.j VectorElement.j Boolean.j" N=primes-sieves

vector-test${EXT}:		${BTMC} VectorTest.btm Vector.btm Integer.btm Boolean.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=VectorTest.btm A="Vector.btm Integer.btm Boolean.btm" J="Vector.j VectorElement.j Integer.j Boolean.j" N=vector-test

nim${EXT}:			${BTMC} Nim.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Nim.btm A="" J="" N=nim

gc-test${EXT}:			${BTMC} GCTest.btm Vector.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=GCTest.btm A="Vector.btm" J="Vector.j VectorElement.j A.j B.j C.j" N=gc-test

concat${EXT}:			${BTMC} Concat.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Concat.btm A="" J="" N=concat

assign${EXT}:			${BTMC} Assign.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Assign.btm A="" J="" N=assign

string-equals${EXT}:		${BTMC} StringEquals.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=StringEquals.btm A="" J="" N=string-equals

equals${EXT}:			${BTMC} Equals.btm Integer.btm Boolean.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Equals.btm A="Integer.btm Boolean.btm" J="Integer.j Boolean.j" N=equals

to-string${EXT}:		${BTMC} ToString.btm Integer.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=ToString.btm A="Integer.btm" J="Integer.j" N=to-string

clone${EXT}:			${BTMC} Clone.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=Clone.btm A="" J="" N=clone

instanceof-cast-test${EXT}:	${BTMC} InstanceofCastTest.btm
				@make --no-print-directory ${TARGET}_rule FLAGS="${FLAGS}" M=InstanceofCastTest.btm A="" J="" N=instanceof-cast-test



clean:
				/bin/rm -f *.asm *.bin *.s *.jar *.class *.j manifest *~
