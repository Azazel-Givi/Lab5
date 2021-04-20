public class SizeException extends Exception{
    String message;
    public SizeException(int value){
        this.message = "Please enter a value less than  " + value;
    }
    public String getMessage(){
        return this.message;
    }
}
