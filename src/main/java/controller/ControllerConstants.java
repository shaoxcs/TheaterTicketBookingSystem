package controller;

/**
 * Options and massage used in the reserve service
 */
public class ControllerConstants {

  /**
   * A private constructor to prevent user from initialization
   */
  private ControllerConstants() {}

  protected static final String OPTION_RESERVE = "reserve";
  protected static final String OPTION_RESERVE_NUM = "<number>";
  protected static final String OPTION_SHOW = "show";
  protected static final String OPTION_DONE = "done";
  protected static final String OPTION_RESERVE_DES = "reserve that number of seats";
  protected static final String OPTION_SHOW_DES = "display the current available seating in the theater";
  protected static final String OPTION_DONE_DES = "shut down the system";
  protected static final String OPTION_YES = "yes";
  protected static final String OPTION_NO = "no";

  protected static final String SAY_WELCOME = "Well come to Theater Reserve System!";
  protected static final String SAY_NO_ENOUGH_SEATS = "Sorry, we don’t have"
      + " that many seats together for you.";
  protected static final String SAY_GOOD_BY = "Have a nice day!";
  protected static final String SAY_NOT_KNOW = "Sorry, we don't have this function yet.";
  protected static final String SAY_NO_NUM_SEATS = "Please indicate the number of seats you need";
  protected static final String SAY_NEED_YESNO = "Please enter yes or no.";
  protected static final String ASK_NAME = "What’s your name?";
  protected static final String ASK_ACTION = "What would you like to do?";
  protected static final String ASK_NEED_ACCESSIBLE = "Do you need wheelchair accessible seats?";
  protected static final String SUCCESS_RESERVE_PATTERN = "I’ve reserved {0} seats for you at the {1} in row {2}, {3}.";
}
