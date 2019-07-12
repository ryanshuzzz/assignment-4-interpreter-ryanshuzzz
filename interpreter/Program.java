package interpreter;

import bytecode.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Program {
    private static HashMap<Integer, String[]> programMap = new HashMap<>();
    private static Vector<ByteCode> byteCodeVector = new Vector<>();

    //init program, populate programMap, byteCodeVector
    public static void program(Map<Integer, String[]> codeValues){
        String currentByteCodeIn;
        for (int i = 1; i <= codeValues.size(); i++) {

            currentByteCodeIn = "bytecode."+ CodeTable.getCode(codeValues.get(i)[0]);

            try {
                ByteCode newCode = (ByteCode) (Class.forName(currentByteCodeIn).getDeclaredConstructor().newInstance());

                byteCodeVector.add(newCode);
                if(currentByteCodeIn.equals("bytecode.LabelByteCode")){
                    newCode.setAddrs(i);
                }
                newCode.init(codeValues.get(i));
                programMap.put(i, codeValues.get(i));
            }catch(ClassNotFoundException e){
                System.err.println("Class " + currentByteCodeIn+ " not found.");
                e.printStackTrace();
            }catch(InstantiationException e){
                System.err.println("Error creating " + currentByteCodeIn+ " instance.");
                e.printStackTrace();
            }catch(IllegalAccessException e){
                System.err.println("Error accessing " + currentByteCodeIn+ " instance.");
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                System.err.println("Method does not exist");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        addAddresses();

    }
    private static void addAddresses(){
        HashMap <String, Integer> addressMap = new HashMap<>();

        //populate addressMap
        for (int i=0; i < byteCodeVector.size(); i++){
            if(byteCodeVector.get(i).getByteCode().equals("LABEL")){
                String labelAddress = byteCodeVector.get(i).getInfo();
                addressMap.put(labelAddress, i);
            }
        }
        //set CALL, GOTO, &, FALSEBRANCH Line Values
        for (int i=0; i < byteCodeVector.size(); i++){
            String labelAddress;
            labelAddress = byteCodeVector.get(i).getInfo();
            if (byteCodeVector.get(i).getByteCode().equals("CALL") || byteCodeVector.get(i).getByteCode().equals("GOTO")
            || byteCodeVector.get(i).getByteCode().equals("FALSEBRANCH")){
                byteCodeVector.get(i).setAddrs(addressMap.get(labelAddress));
            }
        }
    }
    public static ByteCode getCode(int pc){
        return byteCodeVector.get(pc);
    }
    public static int getSize(){
        return byteCodeVector.size();
    }
}
