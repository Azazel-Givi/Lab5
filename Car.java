public class Car {
    private String name; //Поле не может быть null
    private Boolean cool; //Поле может быть null

    public void setName(String name){
        this.name = name;
    }
    public void setCool(Boolean cool){
        this.cool = cool;
    }
    public String getName(){
        return this.name;
    }
    public Boolean getCool(){
        return this.cool;
    }





}
