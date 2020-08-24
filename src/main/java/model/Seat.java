package model;

import java.util.Objects;
import view.ViewConstants;

/**
 * Represents a seat in the row
 */
public class Seat {

  private String seatName;
  private String reservedFor;
  private boolean isAccessible;

  /**
   * Creates a seat object with specific name "a-z" and whether is accessible
   *
   * @param seatName     the name of the seat
   * @param isAccessible whether the seat is accessible
   */
  protected Seat(String seatName, boolean isAccessible) {
    this.seatName = seatName;
    this.reservedFor = null;
    this.isAccessible = isAccessible;
  }

  /**
   * Return the name of the seat
   *
   * @return the name of the seat
   */
  protected String getSeatName() {
    return this.seatName;
  }

  /**
   * Return the name of the customer
   *
   * @return the name of the customer
   */
  protected String getReservedFor() {
    return this.reservedFor;
  }

  /**
   * Set the name of the seat
   *
   * @param seatName the name of the seat to set
   */
  protected void setSeatName(String seatName) {
    this.seatName = seatName;
  }

  /**
   * Reserve the seat by set the name of the customer
   *
   * @param reservedFor the name of the customer
   */
  protected void setReservedFor(String reservedFor) {
    this.reservedFor = reservedFor;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Seat)) {
      return false;
    }
    Seat seat = (Seat) o;
    if (seat.isAccessible != this.isAccessible) {
      return false;
    }
    if (this.reservedFor == null) {
      return seat.reservedFor == null && seat.seatName.equals(this.seatName);
    }
    return seat.seatName.equals(this.seatName) && seat.reservedFor.equals(this.reservedFor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.seatName, this.reservedFor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return this.reservedFor != null ? ViewConstants.SEAT_RESERVED :
        this.isAccessible ? ViewConstants.SEAT_EMPTY_ACCESSIBLE :
            ViewConstants.SEAT_EMPTY_NON_ACCESSIBLE;
  }
}
