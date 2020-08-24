package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class RowTest {

  Row row;

  @Before
  public void setUp() throws Exception {
    row = new Row(10, true);
  }

  @Test
  public void testEquals() {
    assertFalse(row.equals(null));
    assertFalse(row.equals(new LinkedList<>()));
    assertTrue(row.equals(row));
    Row same = new Row(10, true);
    assertEquals(same, row);
    Row diff = new Row(10, false);
    assertFalse(row.equals(diff));
  }

  @Test
  public void testHashCode() {
    Row same = new Row(10, true);
    assertEquals(same, row);
  }
}