package bytecode;

import java.util.Scanner;
import interpreter.VirtualMachine;

public class ReadByteCode extends ByteCode {
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
        System.out.println("Input an int:");
        Scanner in = new Scanner(System.in);
        int val = in.nextInt();
        vm.pushRunStack(val);
    }
}
