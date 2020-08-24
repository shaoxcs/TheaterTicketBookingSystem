package model;

import static org.junit.Assert.*;

import java.util.LinkedList;
import org.junit.Before;
import org.junit.Test;

public class SeatTest {

  Seat seat;

  @Before
  public void setUp() throws Exception {
    seat = new Seat("Z", true);
  }

  @Test
  public void getSeatName() {
    assertEquals("Z", seat.getSeatName());
  }

  @Test
  public void setSeatName() {
    seat.setSeatName("A");
    assertEquals("A", seat.getSeatName());
  }

  @Test
  public void testEquals() {
    Seat same = new Seat("Z", true);
    Seat diff = new Seat("Z", false);
    assertTrue(seat.equals(same));
    assertTrue(seat.equals(seat));
    assertFalse(seat.equals(null));
    assertFalse(seat.equals(new LinkedList<>()));
    assertFalse(seat.equals(diff));
    same.setReservedFor("shao");
    seat.setReservedFor("shao");
    assertEquals(same, seat);
  }

  @Test
  public void testHashCode() {
    Seat same = new Seat("Z", true);
    assertEquals(same.hashCode(), seat.hashCode());
  }
}