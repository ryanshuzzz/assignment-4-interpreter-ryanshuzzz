package bytecode;

import interpreter.VirtualMachine;

public class LoadByteCode extends ByteCode {
    private String byteCode;
    private Integer loadOffset;
    private String loadID = "";
    @Override
    public void init(String [] inputArgs) {
        this.byteCode = inputArgs[0];
        this.loadOffset = Integer.parseInt(inputArgs[1]);
        if (inputArgs.length == 3){
            loadID = inputArgs[2];
        }
    }

    @Override
    public String getString() {
            return byteCode + " " + loadOffset + " " + loadID + "\t\t\t<load " + loadID +">";

    }

    @Override
    public String getByteCode() {
        return byteCode;
    }


    public void execute(VirtualMachine vm) {
        vm.loadAtOffset(loadOffset);
    }
}
