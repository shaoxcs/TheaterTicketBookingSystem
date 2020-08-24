package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represent a theater with a list of rows
 */
public class Theater {

  private String name;
  private int numberOfRows;
  private int seatsPerRow;
  private HashMap<Integer, Row> rowByNumber;

  /**
   * Creates a theater object with specific parameters
   *
   * @param name                 the name of the theater
   * @param numberOfRows         the number of rows in this theater
   * @param seatsPerRow          the number of seats per row
   * @param accessibleRowNumbers a set includes the number of accessible rows
   * @throws AccessibleSettingException exception when set the accessible information
   * @throws InvalidInputException      exception when set the numbers of rows and seats
   */
  public Theater(String name, int numberOfRows, int seatsPerRow, Set<Integer> accessibleRowNumbers)
      throws AccessibleSettingException, InvalidInputException {
    isValidInputCheck(numberOfRows, seatsPerRow, accessibleRowNumbers);
    this.name = name;
    this.numberOfRows = numberOfRows;
    this.seatsPerRow = seatsPerRow;
    this.rowByNumber = new HashMap<>();
    for (int i = 1; i <= numberOfRows; i++) {
      this.rowByNumber.put(i, new Row(seatsPerRow, accessibleRowNumbers.contains(i)));
    }
  }

  /**
   * Helper function that checks if the input is valid
   *
   * @param numberOfRows         the number of rows in this theater
   * @param seatsPerRow          the number of seats per row
   * @param accessibleRowNumbers a set includes the number of accessible rows
   * @throws AccessibleSettingException invalid input when set the accessible information
   * @throws InvalidInputException      invalid input when set the numbers of rows and seats
   */
  private void isValidInputCheck(int numberOfRows, int seatsPerRow,
      Set<Integer> accessibleRowNumbers)
      throws AccessibleSettingException, InvalidInputException {
    if (numberOfRows <= 0 || seatsPerRow <= 0) {
      throw new InvalidInputException(ModelConstants.CREATE_WRONG_NUMBER_OF_ROWS_OR_SEATS);
    }
    if (accessibleRowNumbers.size() < 1) {
      throw new AccessibleSettingException(ModelConstants.CREATE_NOT_ENOUGH_ACCESSIBLE);
    }
    for (int accessibleRowNumber : accessibleRowNumbers) {
      if (accessibleRowNumber <= 0 || accessibleRowNumber > numberOfRows) {
        throw new AccessibleSettingException(ModelConstants.CREATE_WRONG_ACCESSIBLE_NUMBER);
      }
    }
  }

  /**
   * Reserve seats when a valid row is found
   *
   * @param rowNumber      the number of the row to reserve
   * @param seatsNeed      the number of seats needed
   * @param reserveForName the name of the customer
   */
  public void reserve(int rowNumber, int seatsNeed, String reserveForName) {
    Row toReserve = rowByNumber.get(rowNumber);
    toReserve.setSeatsRemain(toReserve.getSeatsRemain() - seatsNeed);
    for (int i = 0; i < toReserve.size() && seatsNeed > 0; i++) {
      Seat seat = toReserve.get(i);
      if (seat.getReservedFor() == null) {
        seat.setReservedFor(reserveForName);
        seatsNeed--;
      }
    }
  }

  /**
   * Find a number of valid row to reserve
   *
   * @param seatsNeed      the number of seats needed
   * @param needAccessible whether the customer need accessible seats
   * @return the number of row that found
   */
  public int findRow(int seatsNeed, boolean needAccessible) {
    int mid = (this.numberOfRows + 1) / 2;
    for (int i = 0; mid - i > 0 || mid + i <= this.numberOfRows; i++) {
      Row rowFront = this.rowByNumber.get(mid - i);
      Row rowRear = this.rowByNumber.get(mid + i);
      if (rowFront != null && rowFront.getSeatsRemain() >= seatsNeed &&
          ((needAccessible && rowFront.isAccessible()) ||
              (!needAccessible && !rowFront.isAccessible()))) {
        return mid - i;
      }
      if (rowRear != null && rowRear.getSeatsRemain() >= seatsNeed &&
          ((needAccessible && rowRear.isAccessible()) ||
              (!needAccessible && !rowRear.isAccessible()))) {
        return mid + i;
      }
    }
    return -1;
  }

  /**
   * Return the number of seats per row
   *
   * @return the number of seats per row
   */
  public int getSeatsPerRow() {
    return this.seatsPerRow;
  }

  /**
   * Return the name of the theater
   *
   * @return the name of the theater
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the number of rows in the theater
   *
   * @return the number of rows in the theater
   */
  public int getNumberOfRows() {
    return this.numberOfRows;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder visualized = new StringBuilder();
    for (Map.Entry<Integer, Row> entry : this.rowByNumber.entrySet()) {
      String rowNumber = String.format("%-3d", entry.getKey());
      visualized.append(rowNumber).append(entry.getValue()).append(System.lineSeparator());
    }
    return visualized.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (this == o) {
      return true;
    }
    if (!(o instanceof Theater)) {
      return false;
    }
    Theater theater = (Theater) o;
    return this.numberOfRows == theater.numberOfRows &&
        this.seatsPerRow == theater.seatsPerRow &&
        Objects.equals(this.name, theater.name) &&
        Objects.equals(this.rowByNumber, theater.rowByNumber);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.name, this.numberOfRows, this.seatsPerRow, this.rowByNumber);
  }
}
