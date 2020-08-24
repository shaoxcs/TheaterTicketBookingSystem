package controller;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import model.AccessibleSettingException;
import model.InvalidInputException;
import model.Theater;
import view.TheaterDisplayer;

/**
 * Operates the workflow of the reservation service Get commands and work with the model
 */
public class ReservationsService {

  /**
   * A private constructor to prevent user initialize
   */
  private ReservationsService() {
  }

  /**
   * The main work flow to get commands from user
   *
   * @param theater
   */
  protected static void serve(Theater theater) {
    System.out.println(ControllerConstants.SAY_WELCOME);
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println(ControllerConstants.ASK_ACTION);
      String commands = scanner.nextLine().toLowerCase();
      String[] args = commands.split("\\s+");
      switch (args[0]) {
        case ControllerConstants.OPTION_RESERVE:
          try {
            int seatsNeed = Integer.parseInt(args[1]);
            reserveProcess(scanner, theater, seatsNeed);
          } catch (NumberFormatException nfe) {
            System.out.println(ControllerConstants.SAY_NO_NUM_SEATS);
          }
          break;
        case ControllerConstants.OPTION_SHOW:
          TheaterDisplayer.display(theater);
          break;
        case ControllerConstants.OPTION_DONE:
          System.out.println(ControllerConstants.SAY_GOOD_BY);
          return;
        default:
          System.out.println(ControllerConstants.SAY_NOT_KNOW);
          showInstructions();
      }
    }
  }

  /**
   * The reservation process when user input reserve
   *
   * @param theater   the theater to reserve
   * @param seatsNeed the number of seats need to reserve
   */
  private static void reserveProcess(Scanner scanner, Theater theater, int seatsNeed) {
    if (seatsNeed > theater.getSeatsPerRow()) {
      System.out.println(ControllerConstants.SAY_NO_ENOUGH_SEATS);
      return;
    }
    Boolean needAccessible = null;
    while (needAccessible == null) {
      System.out.println(ControllerConstants.ASK_NEED_ACCESSIBLE);
      switch (scanner.nextLine().toLowerCase()) {
        case ControllerConstants.OPTION_YES:
          needAccessible = true;
          break;
        case ControllerConstants.OPTION_NO:
          needAccessible = false;
          break;
        default:
          System.out.println(ControllerConstants.SAY_NEED_YESNO);
      }
    }
    int rowNumFound = -1;
    if (!needAccessible) {
      rowNumFound = theater.findRow(seatsNeed, false);
    }
    if (rowNumFound == -1) {
      rowNumFound = theater.findRow(seatsNeed, true);
    }
    if (rowNumFound == -1) {
      System.out.println(ControllerConstants.SAY_NO_ENOUGH_SEATS);
      return;
    }
    System.out.println(ControllerConstants.ASK_NAME);
    String customerName = scanner.nextLine();
    theater.reserve(rowNumFound, seatsNeed, customerName);
    showBookSuccess(theater, seatsNeed, rowNumFound, customerName);
  }

  /**
   * Helper function that display massage when reserve successfully
   *
   * @param theater      the theater of this service
   * @param seatsNeed    the number of seats needed
   * @param rowNumFound  the row number of the theater found
   * @param customerName the name of the customer
   */
  private static void showBookSuccess(Theater theater, int seatsNeed, int rowNumFound,
      String customerName) {
    Object[] reserveInfos = new Object[]{String.valueOf(seatsNeed),
        theater.getName(), String.valueOf(rowNumFound), customerName};
    String reserveResult = MessageFormat
        .format(ControllerConstants.SUCCESS_RESERVE_PATTERN, reserveInfos);
    System.out.println(reserveResult);
  }

  /**
   * Show the instruction of this app.
   */
  private static void showInstructions() {
    System.out.printf("%-18s- %s\n", ControllerConstants.OPTION_RESERVE +
        " " + ControllerConstants.OPTION_RESERVE_NUM, ControllerConstants.OPTION_RESERVE_DES);
    System.out.printf("%-18s- %s\n", ControllerConstants.OPTION_SHOW,
        ControllerConstants.OPTION_SHOW_DES);
    System.out.printf("%-18s- %s\n", ControllerConstants.OPTION_DONE,
        ControllerConstants.OPTION_DONE_DES);
  }
}
