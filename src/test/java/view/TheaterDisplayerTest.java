package view;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashSet;
import model.Theater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TheaterDisplayerTest {

  Theater theater;
  ByteArrayOutputStream outStream;

  @Before
  public void setUp() throws Exception {
    theater = new Theater("Roxy", 15, 10,
        new HashSet<>(Arrays.asList(1, 4)));
    this.outStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outStream));
  }

  @After
  public void tearDown() throws Exception {
    System.setIn(System.in);
    System.setOut(System.out);
  }

  @Test
  public void display() {
    outStream.reset();
    TheaterDisplayer.display(theater);
    String expected = "1  = = = = = = = = = = \n" + "2  _ _ _ _ _ _ _ _ _ _ \n"
        + "3  _ _ _ _ _ _ _ _ _ _ \n" + "4  = = = = = = = = = = \n"
        + "5  _ _ _ _ _ _ _ _ _ _ \n" + "6  _ _ _ _ _ _ _ _ _ _ \n"
        + "7  _ _ _ _ _ _ _ _ _ _ \n" + "8  _ _ _ _ _ _ _ _ _ _ \n"
        + "9  _ _ _ _ _ _ _ _ _ _ \n" + "10 _ _ _ _ _ _ _ _ _ _ \n"
        + "11 _ _ _ _ _ _ _ _ _ _ \n" + "12 _ _ _ _ _ _ _ _ _ _ \n"
        + "13 _ _ _ _ _ _ _ _ _ _ \n" + "14 _ _ _ _ _ _ _ _ _ _ \n"
        + "15 _ _ _ _ _ _ _ _ _ _ \n\n";
    assertEquals(expected, outStream.toString());
  }
}