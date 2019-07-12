package interpreter;
import java.util.Stack;
import java.util.Vector;

public class RunTimeStack {

    private Vector<Integer> runStack;
    private Stack<Integer> framePointers;

    public RunTimeStack(){
        runStack = new Vector<>();
        framePointers = new Stack<>();
        framePointers.push(0);
    }
    @SuppressWarnings({"rawtypes","unchecked"})
    public String dump(){
        Vector<Integer>[] dumpVector = new Vector[framePointers.size()];
        Vector<Integer> runStackCopy = new Vector<Integer>(runStack);
        StringBuilder dumpString = new StringBuilder();


        for( int i = framePointers.size()-1; i >= 0 ; i-- ){
            dumpVector[i] = new Vector<>();
            int startIndex = framePointers.get(i);
            int stackSize = runStackCopy.size();
            for (int k=startIndex; k< stackSize; k++) {
                dumpVector[i].add(runStackCopy.remove(startIndex));
            }
        }
        for ( int i=0; i<framePointers.size(); i++){
            dumpString.append(dumpVector[i].toString());
            System.out.print(dumpVector[i].toString());
        }

        return dumpString.toString();
    }

    public void push(int value) {
        runStack.add(value);
    }

    public int pop() {
        int theReturnVal = runStack.lastElement();
        runStack.remove(runStack.size() - 1);
        return theReturnVal;
    }

    public void pushNewFrame(int offset) {
        framePointers.push(runStack.size() - offset);
    }

    public void binaryOp(String theOperator) {
        int theTop = this.pop();
        int theBot = this.pop();
        switch(theOperator){
            case "+": push(theBot + theTop);
                break;
            case"-": push(theBot - theTop);
                break;
            case"*": push(theBot * theTop);
                break;
            case"/": push(theBot / theTop);
                break;
            case"==":if (theBot == theTop) {
                        push(1);
                    } else {
                        push(0);
                    }
                break;
            case"!=": if (theBot == theTop) {
                        push(0);
                    } else {
                        push(1);
                    }
                break;
            case"<":if(theBot < theTop){
                        push(1);
                    }else{
                        push(0);
                    }
                break;
            case">": if(theBot > theTop){
                        push(1);
                    }else{
                        push(0);
                    }
                break;
            case"<=": if(theBot <= theTop){
                        push(1);
                    }else{
                        push(0);
                    }
                break;
            case">=": if(theBot >= theTop){
                        push(1);
                    }else{
                        push(0);
                    }
                    break;
            case"|": if(theBot == 0 && theTop == 0){
                        push(0);
                    }else{
                        push(1);
                    }
                    break;
            case"&": if(theBot == 1 && theTop == 1 ){
                        push(1);
                    }else if(theBot ==0 || theTop == 0){
                        push(0);
                    }
                    break;
        }


    }

    public void loadAtOffset(int n) {
        if(framePointers.isEmpty()){
            runStack.add(n, runStack.get(n));
        }else {
            n += framePointers.lastElement();
            runStack.add(runStack.get(n));
            }

    }
    public void storeAtOffset(int n) {
        int top = pop();
        int val = framePointers.peek()+n;
        runStack.add(val , top);
        runStack.removeElementAt(framePointers.peek()+n+1);
    }

    public void popN(int n) {
        for (int i =0; i < n; i++){
            this.pop();
        }
    }

    public void popFramePointers() {
        int top = this.pop();
        int firstValue = framePointers.pop();
        for ( int i=firstValue; i<runStack.size(); i++){
            runStack.remove(firstValue);
        }
        this.push(top);
    }


    public void write() {
        System.out.println("Writing: " + runStack.lastElement());
    }

}