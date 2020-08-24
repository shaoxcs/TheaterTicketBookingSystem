package model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class TheaterTest {

  Theater theater;

  @Before
  public void setUp() throws Exception {
    theater = new Theater("Roxy", 3, 3, new HashSet<>(Arrays.asList(1)));
  }

  @Test(expected = InvalidInputException.class)
  public void invalidRow() throws Exception {
    theater = new Theater("Roxy", -1, 3, new HashSet<>(Arrays.asList(1)));
  }

  @Test(expected = InvalidInputException.class)
  public void invalidSeats() throws Exception {
    theater = new Theater("Roxy", 3, -1, new HashSet<>(Arrays.asList(1)));
  }

  @Test(expected = AccessibleSettingException.class)
  public void invalidASeats() throws Exception {
    theater = new Theater("Roxy", 3, 3, new HashSet<>(Arrays.asList(10)));
  }

  @Test(expected = AccessibleSettingException.class)
  public void noASeats() throws Exception {
    theater = new Theater("Roxy", 3, 3, new HashSet<>(Arrays.asList()));
  }

  @Test
  public void getSeatsPerRow() {
    assertEquals(3, theater.getSeatsPerRow());
  }

  @Test
  public void getName() {
    assertEquals("Roxy", theater.getName());
  }

  @Test
  public void getNumberOfRows() {
    assertEquals(3, theater.getNumberOfRows());
  }

  @Test
  public void testEquals() throws AccessibleSettingException, InvalidInputException {
    assertFalse(theater.equals(null));
    assertFalse(theater.equals(new LinkedList<>()));
    assertTrue(theater.equals(theater));
    Theater same = new Theater("Roxy", 3, 3,
        new HashSet<>(Arrays.asList(1)));
    assertTrue(theater.equals(same));
    Theater diff = new Theater("Roxy", 3, 3,
        new HashSet<>(Arrays.asList(2)));
    assertFalse(theater.equals(diff));

  }

  @Test
  public void testHashCode() throws AccessibleSettingException, InvalidInputException {
    Theater same = new Theater("Roxy", 3, 3,
        new HashSet<>(Arrays.asList(1)));
    assertEquals(theater.hashCode(), same.hashCode());
    Theater diff = new Theater("Roxy", 3, 3,
        new HashSet<>(Arrays.asList(2)));
    assertNotEquals(theater.hashCode(), diff.hashCode());
  }
}