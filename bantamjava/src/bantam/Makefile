# set this path to the directory where your project resides
# and the rest of the makefile should work
PROJPATH=/usr/local/bantamjava

SRC=	Main.java \
	lexer/Token.java \
	semant/*.java \
	interp/*.java \
	opt/*.java \
	codegenmips/*.java \
	codegenx86/*.java \
	codegenjvm/*.java \
	ast/*.java \
	cfg/*.java \
	util/*.java \
	visitor/*.java
LPKG=lexer
LSRC=${LPKG}/lexer.jlex
LGEN=${LPKG}/Lexer.java
PPKG=parser
PSRC=${PPKG}/parser.cup
PARSERNAME=Parser
PARSER=${PPKG}/${PARSERNAME}.java
PARSEROUT=${PPKG}/parser.output
TOKENSNAME=TokenIds
TOKENS=${PPKG}/${TOKENSNAME}.java
PGEN=${PARSER} ${TOKENS}
PGENOLD=`echo ${PGEN}|sed -e 's/parser\///g'`
JFILES=${SRC} ${LGEN} ${PGEN}
CLSFILES=${JFILES:.java=.class}

JAVAC := javac
JLEX := ${PROJPATH}/bin/jlex
CUP := ${PROJPATH}/bin/java_cup
CUPCLASSPATH := ${PROJPATH}/lib:${PROJPATH}/lib/java-cup.jar
CLASSPATH := ${CUPCLASSPATH}:${PWD}

BTMC=../../bin/bantamc

all: 		${BTMC}

ref:		${BTMC}
		for REFFILE in $$(find . -name \*.ref); do /bin/cp $$REFFILE $$(echo $$REFFILE|sed -e 's/.ref//'); done

${BTMC}:	Makefile ${CLSFILES}
		@rm -f ${BTMC}
		echo '#!/bin/sh' >> ${BTMC}
		echo 'java -classpath ${CLASSPATH}:${PROJPATH}/src/bantam Main $$*' >> ${BTMC}
		chmod 755 ${BTMC}

${LGEN}:	${LSRC}
		${JLEX} $<
		mv ${LSRC}.java ${LGEN}

${PGEN}:	${PSRC}
		${CUP} -parser ${PARSERNAME} -symbols ${TOKENSNAME} -expect 100 -nopositions -dump < $< > ${PARSEROUT} 2>&1 || { grep -E '^Error at '  ${PARSEROUT}; false; }
		mv ${PGENOLD} parser/

${CLSFILES}:	${JFILES}
		echo "JFILES=${JFILES}"
		${JAVAC} -classpath ${CLASSPATH} ${JFILES}

clean:
	-rm -f *.s *.class *~ ${LGEN} ${PGEN} ${PARSEROUT} ${BTMC}
	-rm -f lexer/*.class lexer/*~
	-rm -f parser/*.class parser/*~
	-rm -f semant/*.class semant/*~
	-rm -f interp/*.class interp/*~
	-rm -f opt/*.class opt/*~
	-rm -f codegenmips/*.class codegenmips/*~
	-rm -f codegenx86/*.class codegenx86/*~
	-rm -f codegenjvm/*.class codegenjvm/*~
	-rm -f ast/*.class ast/*~
	-rm -f cfg/*.class cfg/*~
	-rm -f util/*.class util/*~
	-rm -f visitor/*.class visitor/*~
