package controller;

import java.util.Arrays;
import java.util.HashSet;
import model.AccessibleSettingException;
import model.InvalidInputException;
import model.Theater;

/**
 * Acts as a program trigger
 */
public class ReservationSystem {

  /**
   * A private constructor to prevent user from initialization
   */
  private ReservationSystem() {
  }

  /**
   * Creates a theater here, trigger the reservation service Print out the error message if there is
   * any exception
   */
  public static void main(String[] args) {
    try {
      Theater theater = new Theater("Roxy", 15, 10,
          new HashSet<>(Arrays.asList(1, 4)));
      ReservationsService.serve(theater);
    } catch (AccessibleSettingException | InvalidInputException aseiie) {
      System.out.println(aseiie.getMessage());
    }
  }
}
