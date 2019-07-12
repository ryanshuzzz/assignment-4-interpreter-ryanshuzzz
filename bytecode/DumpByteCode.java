package bytecode;

import interpreter.VirtualMachine;

public class DumpByteCode extends ByteCode {
    private String byteCode;
    private String theArg;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.theArg = inputArgs[1];
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
        if (this.theArg.equals("ON")) {
            vm.setDumpState(true);
        }else if(this.theArg.equals("OFF"))
            vm.setDumpState(false);
    }

}
