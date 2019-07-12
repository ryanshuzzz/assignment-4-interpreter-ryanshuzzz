package bytecode;

import interpreter.VirtualMachine;

public class LabelByteCode extends ByteCode {
    private String byteCode;
    private String theArg;
    private int lineNO;

    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.theArg = inputArgs[1];
    }

    public void setAddrs(int addrs){
        this.lineNO = addrs;
    }
    @Override
    public String getString() {
        return byteCode + " " + theArg;
    }

    @Override
    public String getInfo() {
        return theArg;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }

    public void execute(VirtualMachine vm) {

    }
}
