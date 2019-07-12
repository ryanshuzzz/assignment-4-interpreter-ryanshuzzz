package bytecode;

import interpreter.VirtualMachine;

public class BopByteCode extends ByteCode {
    private String byteCode;
    private String theOperator;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.theOperator =inputArgs[1];
    }

    @Override
    public String getString() {
        return byteCode + " " + theOperator;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }

    public void execute(VirtualMachine vm) {
        vm.binaryOp(this.theOperator);
    }
}
