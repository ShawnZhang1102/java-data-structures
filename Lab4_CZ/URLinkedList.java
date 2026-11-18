import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class URLinkedList<E> implements URList<E> {
    private URNode<E> head;
    private URNode<E> tail;
    private int size;

    public URLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Appends the specified element to the end of this list
    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    // Inserts the specified element at the specified position in this list
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == size) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            URNode<E> current = getNode(index);
            URNode<E> newNode = new URNode<>(element, current.prev(), current);
            current.prev().setNext(newNode);
            current.setPrev(newNode);
            size++;
        }
    }

    // Appends all of the elements in the specified collection to the end of this list,
    // in the order that they are returned by the specified collection's iterator
    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(element);
        }
        return !c.isEmpty();
    }

    // Inserts all of the elements in the specified collection into this list
    // at the specified position
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        int i = index;
        for (E element : c) {
            add(i++, element);
        }
        return !c.isEmpty();
    }

    // Removes all of the elements from this list
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Returns true if this list contains the specified element
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    // Returns true if this list contains all of the elements of the specified collection
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    // Compares the specified object with this list for equality.
    // Returns true if both contain the same elements. Ignore capacity
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof URList)) return false;
        URList<?> that = (URList<?>) o;
        if (size != that.size()) return false;
        Iterator<E> it1 = iterator();
        Iterator<?> it2 = that.iterator();
        while (it1.hasNext()) {
            if (!it1.next().equals(it2.next())) return false;
        }
        return true;
    }

    // Returns the element at the specified position in this list
    @Override
    public E get(int index) {
        return getNode(index).element();
    }

    // Returns the index of the first occurrence of the specified element in this list,
    // or -1 if this list does not contain the element
    @Override
    public int indexOf(Object o) {
        int index = 0;
        for (URNode<E> x = head; x != null; x = x.next()) {
            if (o == null ? x.element() == null : o.equals(x.element())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    // Returns true if this list contains no elements
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Returns an iterator over the elements in this list in proper sequence
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private URNode<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E element = current.element();
                current = current.next();
                return element;
            }
        };
    }

    // Removes the element at the specified position in this list
    @Override
    public E remove(int index) {
        URNode<E> node = getNode(index);
        E element = node.element();
        removeNode(node);
        return element;
    }

    // Removes the first occurrence of the specified element from this list,
    // if it is present
    @Override
    public boolean remove(Object o) {
        for (URNode<E> x = head; x != null; x = x.next()) {
            if (o == null ? x.element() == null : o.equals(x.element())) {
                removeNode(x);
                return true;
            }
        }
        return false;
    }

    // Removes from this list all of its elements that are contained
    // in the specified collection
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            modified |= remove(o);
        }
        return modified;
    }

    // Replaces the element at the specified position in this list
    // with the specified element
    @Override
    public E set(int index, E element) {
        URNode<E> node = getNode(index);
        E oldElement = node.element();
        node.setElement(element);
        return oldElement;
    }

    // Returns the number of elements in this list
    @Override
    public int size() {
        return size;
    }

    // Returns a view of the portion of this list
    // between the specified fromIndex, inclusive, and toIndex, exclusive
    @Override
    public URList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", Size: " + size);
        }
        URLinkedList<E> subList = new URLinkedList<>();
        URNode<E> current = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.element());
            current = current.next();
        }
        return subList;
    }

    // Returns an array containing all of the elements in this list
    // in proper sequence (from first to the last element)
    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (URNode<E> x = head; x != null; x = x.next()) {
            result[i++] = x.element();
        }
        return result;
    }

    


    // URLinkedList specific methods

    // Inserts the specified element at the beginning of this list
    public void addFirst(E e) {
        URNode<E> newNode = new URNode<>(e, null, head);
        if (head != null) {
            head.setPrev(newNode);
        } else {
            tail = newNode;
        }
        head = newNode;
        size++;
    }

    // Appends the specified element to the end of this list
    public void addLast(E e) {
        URNode<E> newNode = new URNode<>(e, tail, null);
        if (tail != null) {
            tail.setNext(newNode);
        } else {
            head = newNode;
        }
        tail = newNode;
        size++;
    }

    // Retrieves, but does not remove, the first element of this list, or returns null if
    // this list is empty
    public E peekFirst() {
        return isEmpty() ? null : head.element();
    }

    // Retrieves, but does not remove, the last element of this list, or returns null if
    // this list is empty
    public E peekLast() {
        return isEmpty() ? null : tail.element();
    }

    // Retrieves and removes the first element of this list, or returns null if this list
    // is empty
    public E pollFirst() {
        if (isEmpty()) return null;
        E element = head.element();
        removeNode(head);
        return element;
    }

    // Retrieves and removes the last element of this list, or returns null if this list
    // is empty
    public E pollLast() {
        if (isEmpty()) return null;
        E element = tail.element();
        removeNode(tail);
        return element;
    }


    private URNode<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        URNode<E> x;
        if (index < (size >> 1)) {
            x = head;
            for (int i = 0; i < index; i++) {
                x = x.next();
            }
        } else {
            x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev();
            }
        }
        return x;
    }

    private void removeNode(URNode<E> x) {
        if (x.prev() == null) {
            head = x.next();
        } else {
            x.prev().setNext(x.next());
        }
        if (x.next() == null) {
            tail = x.prev();
        } else {
            x.next().setPrev(x.prev());
        }
        size--;
    }
}