public class Car {
    private Make make;
    private String owner;

    public Car(){
    }

    public Car(Make make, String owner){
        this.make = make;
        this.owner = owner;
    }

    public String getMake() {
        return String.valueOf(make);
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String toString() {
        return "Car{" +
                "make=" + make +
                ", owner='" + owner + '\'' +
                '}';
    }
}