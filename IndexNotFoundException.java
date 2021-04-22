public class IndexNotFoundException extends Exception {
    String message;
    public IndexNotFoundException(){
        this.message = "Item not found";
    }
    public String getMessage(){
        return this.message;
    }
}
