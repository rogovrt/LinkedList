public class LinkedList {
    private int size = 0;
    private Element first;
    private Element last;

    private static class Element {
        Object item;
        Element next;
        Element prev;

        Element(Element prev, Object item, Element next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedList() {

    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        Element x = first;
        int i = 0;
        while ((i < size) && (!x.item.equals(o))) {
            x = x.next;
            i++;
        }
        return i != size;

    }

    public boolean add(Object o) {
        linkLast(o);
        return true;
    }

    private void linkLast(Object o) {
        Element l = last;
        Element newElement = new Element(l, o, null);
        last = newElement;
        if (l == null)
            first = newElement;
        else
            l.next = newElement;
        size++;
    }

    public boolean add(int index, Object o) {
        checkIndex(index);
        if (index == size)
            linkLast(o);
        else
            linkBefore(index, o);
        return true;
    }

    private void checkIndex(int index) {
        if ((index < 0) || (index > size))
            throw new IndexOutOfBoundsException();
    }

    private void linkBefore(int index, Object o) {
        Element x = searchByIndex(index);
        Element p = x.prev;
        Element insert = new Element(p, o, x);
        x.prev = insert;
        if (p == null)
            first = insert;
        else
            p.next = insert;
        size++;
    }

    private Element searchByIndex(int index) {
        Element x = first;
        for (int i = 0; i < index; i++)
            x = x.next;
        return x;
    }

    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        Element x = first;
        int i = 0;
        while ((i < size) && (!x.item.equals(o))) {
            x = x.next;
            i++;
        }
        Element prev = x.prev;
        Element next = x.next;
        if (prev == null)
            first = next;
        else {
            prev.next = next;
            x.prev = null;
        }
        if (next == null)
            last = prev;
        else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        return true;
    }

    public Object get(int index) {
        checkIndex(index);
        return searchByIndex(index).item;
    }

    public void printList() {
        Element x = first;
        for (int i = 0; i < size; i++) {
            System.out.println(x.item);
            x = x.next;
        }
        System.out.println("EOF LIST");
    }

    public boolean containsAll(LinkedList anotherLinkedList) {
        if ((anotherLinkedList == null) || (size != anotherLinkedList.size()))
            return false;
        if (anotherLinkedList == this)
            return true;
        int i = 0;
        while ((i < size) && (get(i).equals(anotherLinkedList.get(i))))
            i++;
        return i == size;
    }
}
