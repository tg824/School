# set the variable below to the path to the directory where your project 
# resides and the rest of the makefile should work
PROJPATH=/usr/local/bantamjava
# set the variableb below to the type of compiler you want to build:
#   bantam - JLex/Java Cup
#   bantam-jj - JavaCC
VERSION=bantam

all:	tools src api tests

# the rule names are the same as the directory names to avoid confusion
# however, this requires adding auxiliary rules to prevent make from triggering
# only when the directory has been modified

# need auxiliary rule so that make doesn't look at tools directory timestamp
# (i.e., want this rule to always trigger)
tools:	tools-aux

tools-aux:
	cd tools; make PROJPATH=${PROJPATH}; cd ../

# need auxiliary rule so that make doesn't look at src directory timestamp
# (i.e., want this rule to always trigger)
src:	src-aux

src-aux:	tools
	cd src/${VERSION}; make PROJPATH=${PROJPATH}; cd ../../

# need auxiliary rule so that make doesn't look at api directory timestamp
# (i.e., want this rule to always trigger)
api:	api-aux

api-aux:	src
	cd api/; make VERSION=${VERSION}; cd ../

# need auxiliary rule so that make doesn't look at tests directory timestamp
# (i.e., want this rule to always trigger)
tests:	tests-aux

tests-aux:	src
	cd tests; make; cd ../

clean:
	cd tools/; make clean; cd ../
	cd src/bantam; make clean; cd ../../
	cd src/bantam-jj; make clean; cd ../../
	cd api/; make clean; cd ../
	cd tests; make clean; cd ../
	`find . -name \*~ | xargs rm -f`
