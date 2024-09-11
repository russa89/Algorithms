import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class StringListImplTest {

    private final StringList out = new StringListImpl(5);


    @BeforeEach
    public void setUp() {
        out.add("Item1");
        out.add("Item2");
        out.add("Item3");
    }

    @AfterEach
    void tearDown() {
        out.clear();
    }

    @Test
    void addItem() {

        String newItem = "item";
        out.add(newItem);
        out.add(newItem);
        assertEquals(5, out.size());
    }

    @Test
    void addItemWithIndex() {

        int prevSize = out.size();
        String newItem = "item";
        out.add(2, newItem);
        assertEquals(out.size(), prevSize + 1);
    }

    @Test
    void addItemByWrongIndex() {
        String newItem = "wrong item";
        assertThrows(IllegalArgumentException.class, () -> out.add(10, newItem));
    }

    @Test
    void addItemOverTheLimit() {
        out.add("item");
        out.add("extra item");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.add("Storage is full"));
    }

    @Test
    void addItemByIndexOverTheLimit() {
        out.add("item");
        out.add("extra item");
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> out.add("Storage is full"));
    }

    @Test
    void setItem() {

        String actual = out.get(2);
        String expected = out.set(2, "fake");
        assertEquals(actual, expected);
    }

    @Test
    void setItem_WhenWrongIndex_ThenThrowAnException() {

        assertThrows(IllegalArgumentException.class, () -> out.set(10, "fake"));
    }

    @Test
    void removeByItem() {

        String actual = out.remove("Item2");
        String expected = "Item2";

        assertEquals(expected, actual);
    }


    @Test
    void removeByIndex() {

        String actual = out.remove(1);
        String expected = "Item2";

        assertEquals(expected, actual);
    }

    @Test
    void removeItemByWrongIndex() {
        assertThrows(IllegalArgumentException.class, () -> out.remove(10));
    }

    @Test
    void containsItem() {
        assertTrue(out.contains("Item1"));
    }

    @Test
    void doesNotContainItem() {
        assertFalse(out.contains("Item7"));
    }

    @Test
    void indexOf() {

        out.add("Item4");
        assertEquals(3, out.indexOf("Item4"));
    }

    @Test
    void lastIndexOf() {

        out.add("Item4");
        assertEquals(3, out.indexOf("Item4"));
    }

    @Test
    void get() {

        assertEquals(out.get(0), "Item1");
    }

    @Test
    void getByWrongIndex() {
        assertThrows(IllegalArgumentException.class, () -> out.get(10));
    }

    @Test
    void testEquals() {

        StringList test = new StringListImpl(5);
        test.add("Item1");
        test.add("Item2");
        test.add("Item3");

        assertTrue(out.equals(test));
    }

    @Test
    void size() {

        assertEquals(out.size(), 3);
    }

    @Test
    void isEmpty() {

        StringList test = new StringListImpl(0);
        assertEquals(test.size(), 0);
    }

    @Test
    void clear() {

        out.clear();
        assertEquals(out.size(), 3);
    }

    @Test
    void toArray() {

        String [] test = {"Item1", "Item2", "Item3"};
        assertArrayEquals(test, out.toArray());
    }
}