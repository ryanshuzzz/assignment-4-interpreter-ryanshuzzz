package interpreter;

import java.util.Stack;
import bytecode.*;

public class VirtualMachine {


    private int pc;
    private Boolean isRunning;
    private Program newProgram;
    private RunTimeStack newRunStack = new RunTimeStack();
    private Boolean dumpState = false;
    private Stack<Integer> returnAddrs;

    public VirtualMachine(Program program) {
        newProgram = program;

    }

      public void executeProgram() {
        pc = 0;
        returnAddrs = new Stack<Integer>();
        isRunning = true;

        while (isRunning) {
            ByteCode code = Program.getCode(pc);
            code.execute(this);
            if (dumpState){
                System.out.println(code.getString());
                System.out.println(newRunStack.dump());
            }

            pc++;
            if(pc>= Program.getSize()-1){
                isRunning = false;
                break;
            }
        }
    }
    public void makeFrame(int n)        {newRunStack.pushNewFrame(n);}
    public void binaryOp(String op)     { newRunStack.binaryOp(op); }
    public int getReturn()              {return returnAddrs.pop();}
    public void haltProgram()           {isRunning = false;}
    public void loadAtOffset(int n)     {newRunStack.loadAtOffset(n);}
    public void storeAtOffset(int n)    {newRunStack.storeAtOffset(n);}
    public void popN(int n)             {newRunStack.popN(n);}
    public int popRunStack()            { return newRunStack.pop();}
    public void popFramePointers()      {newRunStack.popFramePointers();}
    public void pushRunStack(int n)     {newRunStack.push(n);}
    public void savePC()                {returnAddrs.add(pc);}
    public void setDumpState(boolean b) {this.dumpState = b;}
    public void setPC(int n)            {pc =n;}
    public void write()                 {newRunStack.write();}
}
