public class CarList {
    private CarListNode head;
    private CarListNode tail;
    private CarListNode cursor;

    public CarList(){
        this.head = null;
        this.tail = null;
        this.cursor = null;
    }

    public  int numCars(){
        int counter = 0;
        cursor = head;
        while (cursor != null){
            cursor = cursor.getNext();
            counter ++;
        }
        return counter;
    }


    public Car getCursorCar(){
        if (cursor == null){
            return null;
        }
        else {
            return cursor.getData();
        }
    }

    public void resetCursorToHead(){
        if (head != null){
            cursor = head;
        }
        else {
            cursor = null;
            System.out.println("The list is empty");
        }
    }

    public void cursorToTail(){
        if (tail != null){
            cursor = tail;
        }
        else {
            cursor = null;
            System.out.println("The list is empty");
        }
    }

    public void cursorForward() throws EndOfListException{
        if (cursor == tail){
            throw new EndOfListException("The cursor is at the tail of the list");
        }
        else {
            cursor = cursor.getNext();
        }
    }

    public void cursorBackward() throws EndOfListException{
        if(cursor == head){
            throw new EndOfListException("The cursor is at the tail of the list!");
        }
        else {
            cursor = cursor.getPrev();
        }
    }


    public void display(){
        CarListNode shifter;
        if (head == null){
            System.out.println("[empty]");
        }
        else {
            shifter = head;
            while (shifter != null){
                if (shifter == cursor){
                    System.out.println("-> "+shifter.getData().getMake()+"   "+shifter.getData().getOwner());
                }
                else {
                    System.out.println(shifter.getData().getMake()+ "   "+ shifter.getData().getOwner());
                }
                shifter = shifter.getNext();

            }
        }
    }

    public void insertBeforeCursor(Car newCar){
        if (newCar != null){
            CarListNode newCarListNode = new CarListNode(newCar);
            if (cursor == null){
                appendToTail(newCar);
            }
            else if(cursor == head){
                insertBeforeHead(newCar);
            }
            else{
                CarListNode prevCarListNode = cursor.getPrev();
                prevCarListNode.setNext(newCarListNode);
                newCarListNode.setPrev(prevCarListNode);
                newCarListNode.setNext(cursor);
                cursor.setPrev(newCarListNode);
            }
        }
    }

    public Car removeCursor(){
        if (head == null || cursor == null)
            return null;
        Car removedCar;
        if (cursor == head) {
            removedCar = head.getData();
            head = head.getNext();
            if (head == null)
                tail = null;
            cursor = null;
        }
        else {
            CarListNode prevNode = head;
            while (prevNode.getNext() != cursor) {
                prevNode = prevNode.getNext();
            }
            removedCar = cursor.getData();
            prevNode.setNext(cursor.getNext());
            cursor = prevNode;
        }
        return removedCar;

    }

    public Car cutCarAtCursor(){
        return this.removeCarAtCursor();
    }

    public void pasteCarBeforeCursor(Car car){
        if (car == null){
            System.out.println("Nothing to paste");
        }
        else {
            insertBeforeCursor(car);

        }
    }

    public Car removeCarAtCursor(){
        return this.removeCursor();
    }

    public boolean merge(CarList other){
        boolean isMerged;
        if (other == null || other.head == null) {
            System.out.println("Empty lists");
            isMerged = false;
        }
        else {
            if (this.head == null) {
                this.head = other.head;
                this.tail = other.tail;
            }
            else {
                CarListNode thisCurrent = this.head;
                CarListNode otherCurrent = other.head;
                while (thisCurrent != null && otherCurrent != null) {
                    CarListNode thisNext = thisCurrent.getNext();
                    CarListNode otherNext = otherCurrent.getNext();
                    thisCurrent.setNext(otherCurrent);
                    otherCurrent.setNext(thisNext);
                    thisCurrent = thisNext;
                    otherCurrent = otherNext;
                }
                if (otherCurrent != null) {
                    this.tail.setNext(otherCurrent);
                    this.tail = other.tail;
                }
            }
            other.head = null;
            other.tail = null;
            other.cursor = null;
            isMerged = true;
        }
        return isMerged;
    }

    public void insertBeforeHead(Car newCar){
        CarListNode newCarListNode = new CarListNode(newCar);
        if (head == null){
            head = newCarListNode;
            tail = newCarListNode;
            cursor = newCarListNode;
        }
        else {
            head.setPrev(newCarListNode);
            newCarListNode.setNext(head);
            head = newCarListNode;
        }
    }

    public void appendToTail(Car newCar){
        if (newCar != null){
            CarListNode newCarListNode = new CarListNode(newCar);
            if (head == null){
                insertBeforeHead(newCar);
            }
            else{
                tail.setNext(newCarListNode);
                newCarListNode.setPrev(tail);
                tail = newCarListNode;
            }
        }
    }
}
