import java.util.Collection;

public class CollectionException extends Exception{
    String message;
    String info = "";
    public CollectionException(String info){
        this.message = "Collection is empty";
        this.info = this.info + "\n " + info;
    }
    public String getMessage(){
        return this.message;
    }
    public String getInfo(){
        return this.info;
    }

}
