package bytecode;

import interpreter.VirtualMachine;

public class GoToByteCode extends ByteCode {
    private String byteCode;
    private String theArg;
    private int lineNO;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.theArg = inputArgs[1];
    }

      @Override
    public String getString() {
        return byteCode + " " + theArg;
    }


    public String getInfo() {
        return theArg;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }

    public void setAddrs(int n){
        this.lineNO = n;
    }

    public void execute(VirtualMachine vm) {
        vm.setPC(lineNO);

    }
}
