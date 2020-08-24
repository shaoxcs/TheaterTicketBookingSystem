package model;

/**
 * Constants used in the model
 */
public class ModelConstants {

  /**
   * A private constructor to prevent user from initialization
   */
  private ModelConstants() {
  }

  protected static final String CREATE_WRONG_NUMBER_OF_ROWS_OR_SEATS = "The number of "
      + "rows and the number of seats per prow should be larger than 0";
  protected static final String CREATE_NOT_ENOUGH_ACCESSIBLE = "Please provide more accessible rows.";
  protected static final String CREATE_WRONG_ACCESSIBLE_NUMBER = "The number of "
      + "accessible row should be among 1 ~ numberOfRows";
}
