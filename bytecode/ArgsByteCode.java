package bytecode;

import interpreter.VirtualMachine;

public class ArgsByteCode extends ByteCode {
    private int argCount;
    private String byteCode;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.argCount = Integer.parseInt(inputArgs[1]);

    }

     @Override
    public String getString() {
        return byteCode + " " + argCount;
    }


    public String getByteCode() {
        return byteCode;
    }

    public void execute(VirtualMachine vm) {
        vm.makeFrame(argCount);
    }


}
