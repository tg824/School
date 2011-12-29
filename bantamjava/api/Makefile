VERSION=bantam

all:
	/bin/rm -rf html
	javadoc -sourcepath ../src/${VERSION}:../src/${VERSION}/lexer:../src/${VERSION}/parser:../src/${VERSION}/semant:../src/${VERSION}/interp:../src/${VERSION}/opt:../src/${VERSION}/codegenmips:../src/${VERSION}/codegenx86:../src/${VERSION}/codegenjvm:../src/${VERSION}/ast:../src/${VERSION}/cfg:../src/${VERSION}/util:../src/${VERSION}/visitor \
		-classpath ../src:../lib:../lib/java-cup.jar \
		-d html \
		-use \
		-splitIndex \
		-windowtitle "Bantam Java Compiler API" \
		-doctitle "Bantam Java Compiler API" \
		-J-Xmx180m \
		-group "Core Packages" `find ../src/${VERSION} -name \*.java` \
		-group "Extension Packages" java_cup.runtime \
		../src/${VERSION}/*.java ../src/${VERSION}/ast/*.java \
		../src/${VERSION}/lexer/*.java ../src/${VERSION}/parser/*.java \
		../src/${VERSION}/semant/*.java ../src/${VERSION}/interp/*.java \
		../src/${VERSION}/opt/*.java ../src/${VERSION}/codegenmips/*.java \
		../src/${VERSION}/codegenx86/*.java ../src/${VERSION}/codegenjvm/*.java \
		../src/${VERSION}/util/*.java ../src/${VERSION}/visitor/*.java \
		../src/${VERSION}/cfg/*.java

clean:
	/bin/rm -rf html/
