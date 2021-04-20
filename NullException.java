public class NullException extends Exception{
    String message;
    public NullException(String var){
        this.message = "Please, enter a " + var + " value";
    }
    public String getMessage(){
        return this.message;
    }
}
