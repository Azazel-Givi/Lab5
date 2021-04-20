public class Coordinates {
    private Integer x; //Максимальное значение поля: 574, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(){

    }
    public void setCoordx(Integer x){
        this.x = x;
    }
    public void setCoordy(Float y){
        this.y = y;
    }
    public int getCoordx(){
        return this.x;
    }
    public float getCoordy(){
        return this.y;
    }
}
