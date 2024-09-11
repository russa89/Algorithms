import java.util.Arrays;

public class StringListImpl implements StringList {

    private final String[] list;
    private int size;

    public StringListImpl() {
        this(5);
    }

    public StringListImpl(int initSize) {
        list = new String[initSize];
    }

    @Override
    public String add(String item) {

        checkForNull(item);

        list[size++] = item;

        return item;
    }

    @Override
    public String add(int index, String item) {

        checkForNull(item);
        checkSizeIsAcceptable();
        checkIndexIsAcceptable(index);

        if (index == size) {
            list[size++] = item;
            return item;
        }
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = item;
            size++;

            return item;
        }

    @Override
    public String set(int index, String item) {

        checkForNull(item);
        checkIndexIsAcceptable(index);

        String oldItem = list[index];
        list[index] = item;
        size++;

        return oldItem;
    }

    @Override
    public String remove(String item) {

        checkForNull(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                String oldItem = list[i];
                list[i] = null;
                size--;
                System.arraycopy(list, i, list, i - 1, list.length -1);
                return oldItem;
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String remove(int index) {

        checkIndexIsAcceptable(index);

        String oldItem = list[index];
        list[index] = null;
        size--;
        System.arraycopy(list, index, list, index - 1, list.length - 1);
        return oldItem;

    }

    @Override
    public boolean contains(String item) {

        checkForNull(item);

        //  return indexOf(item) != -1;
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {

        checkForNull(item);

        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {

        checkForNull(item);

        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {

        checkIndexIsAcceptable(index);
        return list[index];
    }


    @Override
    public boolean equals(StringList otherList) {

        if (otherList == this) {
            return true;
        }

        if (otherList == null || otherList.getClass() != this.getClass()) {
            return false;
        }

        StringListImpl temp = (StringListImpl) otherList;
        if (size != temp.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!list[i].equals(temp.get(i))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public void clear() {

        Arrays.fill(list, null);
    }

    @Override
    public String[] toArray() {

        return Arrays.copyOf(list, size);
    }

    private void checkForNull(String item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null");
        }
    }

    private void checkSizeIsAcceptable() {
        if (list.length == size) {
            throw new IllegalArgumentException("Storage is full");
        }
    }

    private void checkIndexIsAcceptable(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}
