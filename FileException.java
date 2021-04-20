public class FileException extends Exception{
    String message;
    String info = "";
    public FileException(String info){
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
