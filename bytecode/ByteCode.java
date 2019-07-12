package bytecode;

import interpreter.VirtualMachine;

public abstract class ByteCode {
    public ByteCode(){}
    public String getInfo(){return null;}
    public void setAddrs(int addrs){}
    public abstract void init(String[] inputArgs);
    public abstract String getString();
    public abstract String getByteCode();
    public abstract void execute(VirtualMachine vm);
}
