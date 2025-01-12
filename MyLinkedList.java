/**
 * custom linked list implementation
 * to satisfy the "at least one custom data structure" requirement
 */
public class MyLinkedList<DataType> {

    private static class Node<DataType> {
        DataType data;
        Node<DataType> next;

        Node(DataType data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<DataType> head;
    private int size;

    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // Add null check in add method
    public void add(DataType element) {
        if (element == null) {
            throw new IllegalArgumentException("can't add null element");
        }
        Node<DataType> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<DataType> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        size++;
    }

    /**
     * remove the first occurrence of the element from the list
     */
    public boolean remove(DataType element) {
        if (head == null)
            return false;

        // if the head is the node to remove
        if (head.data.equals(element)) {
            head = head.next;
            size--;
            return true;
        }

        // otherwise search the list
        Node<DataType> current = head;
        while (current.next != null) {
            if (current.next.data.equals(element)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Add null check in contains method
    public boolean contains(DataType element) {
        if (element == null) return false;
        Node<DataType> current = head;
        while (current != null) {
            if (element.equals(current.data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // get the element at the specified index
    public DataType get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
        }
        Node<DataType> current = head;
        int i = 0;
        while (i < index) {
            current = current.next;
            i++;
        }
        return current.data;
    }

    // return the size of the list
    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    // toString method needed for display methods
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<DataType> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        return sb.toString();
    }

    // print all elements in the list
    public void printAll() {
        Node<DataType> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}