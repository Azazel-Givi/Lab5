public class TypeException extends Exception{
    String message;
    public TypeException(String type){
        this.message = "Please, enter a " + type + " value";
    }
    public String getMessage(){
        return this.message;
    }
}
