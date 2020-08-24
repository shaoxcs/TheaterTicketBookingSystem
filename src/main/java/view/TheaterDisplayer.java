package view;

import model.Theater;

/**
 * Displayer used when display theater
 */
public class TheaterDisplayer {

  /**
   * A private constructor to prevent user from initialization
   */
  private TheaterDisplayer() {
  }

  public static void display(Theater theater) {
    System.out.println(theater);
  }
}
