package bytecode;

import interpreter.VirtualMachine;

public class HaltByteCode extends ByteCode {
    private String byteCode;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
    }
    @Override
    public String getString() {
        return byteCode ;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        vm.haltProgram();

    }
}
