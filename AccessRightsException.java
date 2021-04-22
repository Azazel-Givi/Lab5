public class AccessRightsException extends Exception{
        String message;
    public AccessRightsException(String str) {
        this.message = str;
    }
    public String getMessage() {
        return "Access rights exception. Unable to " + this.message + ". \n:(";
    }
}
