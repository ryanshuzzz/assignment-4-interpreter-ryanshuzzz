package bytecode;

import interpreter.VirtualMachine;

public class ReturnByteCode extends ByteCode {
    private String byteCode;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
    }

    @Override
    public String getString() {
        return byteCode;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        int returnAddrs = vm.getReturn();
        vm.popFramePointers();
        vm.setPC(returnAddrs);
    }
}
