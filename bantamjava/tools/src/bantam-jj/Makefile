# set this path to the directory where your project resides
# and the rest of the makefile should work
PROJPATH=~/bantamjava

SRC=	Main.java \
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
LSRC=${LPKG}/lexer.jj
LGEN=	${LPKG}/Lexer.java ${LPKG}/LexerConstants.java \
	${LPKG}/Token.java ${LPKG}/LexerTokenManager.java \
	${LPKG}/TokenMgrError.java ${LPKG}/ParseException.java \
	${LPKG}/JavaCharStream.java
PPKG=parser
PSRC=${PPKG}/parser.jj
PGEN=	${PPKG}/Parser.java ${PPKG}/ParserConstants.java
JFILES=${SRC} ${LGEN} ${PGEN}
CLSFILES=${JFILES:.java=.class}

JAVAC := javac
JAVACC := ${PROJPATH}/bin/javacc
CLASSPATH := ${PWD}

BTMC=../../bin/bantamc

all: 		${BTMC}

ref:		${BTMC}
		for REFFILE in $$(find -L . -name \*.ref); do /bin/cp $$REFFILE $$(echo $$REFFILE|sed -e 's/.ref//'); done

${BTMC}:	Makefile ${CLSFILES}
		@rm -f ${BTMC}
		echo '#!/bin/sh' >> ${BTMC}
		echo 'java -classpath ${CLASSPATH}:${PROJPATH}/src/bantam-jj Main $$*' >> ${BTMC}
		chmod 755 ${BTMC}

${LGEN}:	${LSRC}
		${JAVACC} -OUTPUT_DIRECTORY=${LPKG} $<

${PGEN}:	${PSRC}
		${JAVACC} -OUTPUT_DIRECTORY=${PPKG} $<

${CLSFILES}:	${JFILES}
		echo "JFILES=${JFILES}"
		${JAVAC} -classpath ${CLASSPATH} ${JFILES}

clean :
	-rm -f *.s *.class *~ ${BTMC}
	-rm -f lexer/*.class lexer/*.java lexer/*~
	-rm -f parser/*.class parser/*.java parser/*~
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
