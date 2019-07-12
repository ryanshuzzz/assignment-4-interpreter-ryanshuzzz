assignment-4-interpreter-ryanshuzzz
Ryan Shu (916850524)
GitHub Repo
(https://github.com/sfsu-csc-413-spring-2019/assignment-4-interpreter-ryanshuzzz)
CSC 413-05

### Overview

### Project Introduction
	This repository contains the interpreter portion of the x language compiler. The purpose of this repository is to continue the work done on the large x compiler code base and create a functioning Interpreter. Initially given is the Interpreter.java class. From here we must create and implement the following classes:
1. CodeTable
2. ByteCodeLoader
3. Program
4. RuntimeStack
5. VirtualMachine
Furthermore, we must create a ByteCode package which would contain all of the various bytecodes that we would need in order to properly interpret the give bytecode file.

### Summary of Technical Work
	After creating these classes each of these classes will function as follows.

	The CodeTable class will provide a map of all of the possible ByteCode names in a regular string. This can be used to look up and create the ByteCode objects when read from the ByteCode file to creating the ByteCode objects.
	The ByteCodeLoader class will open the file and load all of the ByteCode commands into a Map of the given ByteCode file. Then, this class will create a Program with this Map.
	The Program class will take the given bytecode map from ByteCodeLoader.java and create all of the bytecode objects and initialize them using reflection. During this, it will load all of these bytecode objects into a Vector of bytecodes. After this, it finds all the addresses for the “LABEL” bytecode, then associates the “CALL”, “GOTO”, and “FALSEBRANCH” bytecodes with those addresses.
	The RuntimeStack class will contain the RunTimeStack, and manage the Stack. Also, the RuntimeStack will contain all of the methods to execute the ByteCodes. For example, for the BOP bytecode, the class must be able to do all of the BOP commands, like Addition, Multiplication, Greater Than, etc..
	The VirtualMachine class will manage all of the ByteCodes actions. The ByteCodes will call a method in this class, which will then call a method in the RuntimeStack class that will execute the ByteCode’s function.
	The ByteCode package will contain all the ByteCode classes for all the possible ByteCodes. These bytecodes will contain the values, assignments, and depending on the Bytecode, the address. These classes will have an Init(), getString(), and execute() methods that will help manage the Bytecode’s functionality.

### Execution and Development Environment Described

I developed from this code base using IntelliJ IDEA Community v. 2018.3.5. This Java application was compiled using Java JDK version 12.0.1 which is the most up to date version of the JDK.

### Scope of Work Described
    • Implement CodeTable.java
        ◦ Have it read from a file all of the possible bytecodes
        ◦ Create a Hashmap<String, String> of those read ByteCodes, and class names
        ◦ Handle all File I/O exceptions
    • Implement ByteCodeLoader.java
        ◦ Have it read the inputted ByteCode file and populate a HashMap<Integer, String[]>
            ▪ Use SPACE as a delimiter
            ▪ This will contain the line number and arguments for easy management
        ◦ Create a new Program object and pass through this HashMap.
    • Implement Program.java
        ◦ Create a HashMap<Integer, String[]> and Vector<ByteCode> to manage the objects and the input File
        ◦ Creates ByteCode objects for each of the Bytecodes in the HashMap
        ◦ Initializes the bytecodes by using reflection and the bytecode.init(args[]) method
        ◦ Handles all Class, reflection exceptions
        ◦ Has method addAddresses() that will store all the “LABEL” Addresses and link this to the appropriate “CALL”, “GOTO”, or “FALSEBRANCH” lines.
        ◦ Create getCode(int) method to pass the ByteCode object from the vector
        ◦ Create a getSize() method that returns the number of lines in the ByteCode file for the Virtual Machine knows how long it needs to be running
    • Implement RuntimeStack.java
        ◦ Create a Vector<Integer> that will act as the runStack
        ◦ Create a Stack<Integer> that will manage the frames of the runStack
        ◦ Create dump() method that returns RuntimeStack that is correctly separated
        ◦ Create push() and pop() methods to be able to treat runStack as a stack
        ◦ Create pushNewFrame(), binaryOP(String), loadAtOffset(int), storeAtOffset(int), popN(int), popFramePointers(), write(), for Virtual Machine to execute the bytecodes instructions
    • Implement VirtualMachine.java
        ◦ Create executeProgram() method that will allow Interpreter to execute program
        ◦ Create makeFrame(int), binaryOp(String), getReturn(), haltProgram(), loadAtOffset(int), storeAtOffser(int), popN(int), popRunStack(), popFramePointers(), pushRunStack(int), savePC(), setDumpState(), setPC(int), write().
            ▪ These will allow the bytecode’s function to be execute by the Virtual Machine
    • Implement ByteCode package
        ◦ There will be the classes ArgsByteCode, BopByteCode, CallByteCode, DumpByteCode, FalseBranchByteCode, GoToByteCode, HaltByteCode, LabelByteCode, LitByteCode, LoadByteCode, PopByteCode, ReadByteCode, ReturnByteCode, StoreByteCode, WriteByteCode
            ▪ There will be abstract methods init(String[] args), getString(), getBytecode(), and execute()
### Instructions to Compile and Execute
Dependencies:
Java JDK 12.0.1
Java JRE 12.0.1

    1. Clone this repository
    2. Navigate into the repository folder(assignment-4-interpreter-ryanshuzzz)
    3. Compile using
    ```
       javac bytecode/*.java;javac interpreter/*.java
       ```
    4. Run using
    ```
       java interpreter.Interpreter [test file location]
       ```
        ◦ given test files are in /sample_files/

### Assumptions Made

No assumptions were made for this project

### Implementation Discussion

### Implementation Decisions
	To implement these classes in somewhat of a neat fashion, I decided to create a ByteCode package that contains all of the ByteCodes that we would need. We will be able to create and manage these ByteCodes in the Program class.
	For the CodeTable class, I decided that I would read from a static text file to create the HashMap of the bytecodes. This way, if someone were to want to create additional ByteCodes, they would simply have to add this to the text file and create the ByteCode class.

### Code Organization
As previously mentioned the ByteCodes have been separated into their own separate package. Furthermore, every class has its methods generally in the order that they will be executed when the objects are made and the methods are executed.

### Results/Conclusion
The Interpreter works as described, and has been tested to dump the correct data.

### Sample Output(with DUMP ON):
interpreter.Interpreter sample_files/simple.x.cod
GOTO start<<1>>
[][]
LIT 0 i				inti
[0][0]
LIT 0 j				intj
[0, 0][0, 0]
LOAD 0 i			<load i>
[0, 0, 0][0, 0, 0]
LOAD 1 j			<load j>
[0, 0, 0, 0][0, 0, 0, 0]
BOP +
[0, 0, 0][0, 0, 0]
LIT 7
[0, 0, 0, 7][0, 0, 0, 7]
BOP +
[0, 0, 7][0, 0, 7]
STORE 0 i
[7, 0][7, 0]
LOAD 0 i			<load i>
[7, 0, 7][7, 0, 7]
ARGS 1
[7, 0][7][7, 0][7]
CALL Write
[7, 0][7][7, 0][7]
LOAD 0 dummyFormal			<load dummyFormal>
[7, 0][7, 7][7, 0][7, 7]
Writing: 7
WRITE
[7, 0][7, 7][7, 0][7, 7]
RETURN
[7, 0, 7][7, 0, 7]
STORE 1 j
[7, 7][7, 7]
POP 2
[][]