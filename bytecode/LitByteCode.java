package bytecode;

import interpreter.VirtualMachine;

public class LitByteCode extends ByteCode {
    private String byteCode;
    private Integer litValue;
    private String litID;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.litValue = Integer.parseInt(inputArgs[1]);
        if (inputArgs.length == 3){
            litID = inputArgs[2];
        }
    }

    @Override
    public String getString() {
        if(litID != null) {
            return byteCode + " " + litValue + " " + litID + "\t\t\t\tint" + litID;
        }
        return byteCode + " " + litValue;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        vm.pushRunStack(litValue);
    }
}
