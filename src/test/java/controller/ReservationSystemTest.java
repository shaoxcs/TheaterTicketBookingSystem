package controller;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import model.Theater;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReservationSystemTest {

  ByteArrayInputStream inputStream;
  ByteArrayOutputStream outStream;
  Theater theater;

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
  public void testNotFound() {
    inputStream = new ByteArrayInputStream((
        "walawala" + System.lineSeparator() +
            "reserve po" + System.lineSeparator() +
            "reserve 30" + System.lineSeparator() +
            "reserve 7" + System.lineSeparator() +
            "walawala" + System.lineSeparator() +
            "no" + System.lineSeparator() +
            "shao" + System.lineSeparator() +
            "reserve 7" + System.lineSeparator() +
            "yes" + System.lineSeparator() +
            "xue" + System.lineSeparator() +
            "done"
    ).getBytes());
    System.setIn(inputStream);
    outStream.reset();

    Object[] reserveInfos = new Object[]{String.valueOf(7), theater.getName(), String.valueOf(8),
        "shao"};
    String SHAO_NO_7 = MessageFormat
        .format(ControllerConstants.SUCCESS_RESERVE_PATTERN, reserveInfos);
    Object[] reserveInfos1 = new Object[]{String.valueOf(7), theater.getName(), String.valueOf(4),
        "xue"};
    String XUE_YES_3 = MessageFormat
        .format(ControllerConstants.SUCCESS_RESERVE_PATTERN, reserveInfos1);

    ReservationsService.serve(theater);
    String expected = ControllerConstants.SAY_WELCOME + System.lineSeparator() +
        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.SAY_NOT_KNOW + System.lineSeparator() +

        "reserve <number>  - reserve that number of seats" + System.lineSeparator() +
        "show              - display the current available seating in the theater" + System
        .lineSeparator() +
        "done              - shut down the system" + System.lineSeparator() +

        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.SAY_NO_NUM_SEATS + System.lineSeparator() +

        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.SAY_NO_ENOUGH_SEATS + System.lineSeparator() +

        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.ASK_NEED_ACCESSIBLE + System.lineSeparator() +
        ControllerConstants.SAY_NEED_YESNO + System.lineSeparator() +
        ControllerConstants.ASK_NEED_ACCESSIBLE + System.lineSeparator() +
        ControllerConstants.ASK_NAME + System.lineSeparator() +
        SHAO_NO_7 + System.lineSeparator() +

        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.ASK_NEED_ACCESSIBLE + System.lineSeparator() +
        ControllerConstants.ASK_NAME + System.lineSeparator() +
        XUE_YES_3 + System.lineSeparator() +

        ControllerConstants.ASK_ACTION + System.lineSeparator() +
        ControllerConstants.SAY_GOOD_BY + System.lineSeparator();
    assertEquals(expected, outStream.toString());
  }
}