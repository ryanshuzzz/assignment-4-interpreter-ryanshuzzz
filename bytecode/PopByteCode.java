package bytecode;

import interpreter.VirtualMachine;

public class PopByteCode extends ByteCode {
    private String byteCode;
    private String theArg;
    private int count;
    @Override
    public void init(String [] inputArgs) {
        count =0;
        this.byteCode = inputArgs[0];
        this.theArg = inputArgs[1];
        count = Integer.parseInt(this.theArg);
    }

    @Override
    public String getString() {
        return byteCode + " " + theArg;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        vm.popN(count);
    }
}
