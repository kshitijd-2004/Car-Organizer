run: runBD runFD runIF
  java Backend

runBD: Backend.java
  javac Backend.java

runFD: Frontend.java
  javac Frontend.java

runIF:
  javac CarBackend.java
  javac CarInterface.java

clean:
  rm -rf *.class
