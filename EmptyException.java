public class EmptyException extends Exception{
    String message;
    public EmptyException(){
        this.message = "Collection is empty";
    }
    public String getMessage(){
        return this.message;
    }
}
