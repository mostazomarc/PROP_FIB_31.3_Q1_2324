CLASS_INPUT =	./Domini/*.java \
				./Drivers/*.java \
				./ControladorsDomini/*.java \
				./Dades/*.java \
				./Stubs/*.java

CLASS_OUTPUT =	../../EXE/

JAR_OUTPUT =	../../EXE/

JUNIT_JARS = ./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./JUnit/*.java



all:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

executaDriverDomini:
	java -cp ../EXE Drivers.DriverDomini


fulltest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.MasterTest

idiomatest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.IdiomaTest

alfabettest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.AlfabetTest

factoriatest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.FactoriaTest

frequenciestest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.LlistaFrequenciesTest

perfiltest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.PerfilTest

teclattest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.TeclatTest

clean:
	rm -r ../../EXE/*
