package bytecode;

import interpreter.VirtualMachine;

public class StoreByteCode extends ByteCode {
    private String byteCode;
    private String theArg;
    private int storeValue;
    private String storeID;
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.theArg = inputArgs[1];
        this.storeValue = Integer.parseInt(this.theArg);
        if(inputArgs.length == 3){
            this.storeID = inputArgs[2];
        }
    }

    @Override
    public String getString() {
        return byteCode + " " + theArg + " " + storeID;
    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        vm.storeAtOffset(storeValue);
    }
}
