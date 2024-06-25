public class CarListNode {
    private Car data;
    private CarListNode next;
    private CarListNode prev;
    public CarListNode(Car initData){
        if (initData == null){
            throw new IllegalArgumentException();
        }
        else {
            this.data = initData;
        }
        this.next = null;
        this.prev = null;
    }

    public Car getData() {
        return this.data;
    }

    public void setData(Car data) {
        this.data = data;
    }

    public CarListNode getNext() {
        return this.next;
    }

    public void setNext(CarListNode next) {
        this.next = next;
    }

    public CarListNode getPrev() {
        return this.prev;
    }

    public void setPrev(CarListNode prev) {
        this.prev = prev;
    }


}