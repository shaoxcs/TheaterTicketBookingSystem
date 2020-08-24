package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A row object is a row in the theater with seats
 */
public class Row extends ArrayList<Seat> {

  private int seatsRemain;
  private boolean isAccessible;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param seatsPerRow the number of seats per row
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  protected Row(int seatsPerRow, boolean isAccessible) {
    super(seatsPerRow);
    this.seatsRemain = seatsPerRow;
    this.isAccessible = isAccessible;
    for (int i = 0; i < seatsPerRow; i++) {
      char letter = (char) ('A' + i);
      this.add(new Seat(Character.toString(letter), isAccessible));
    }
  }

  /**
   * Return the number of seats remain in the row
   *
   * @return
   */
  protected int getSeatsRemain() {
    return this.seatsRemain;
  }

  /**
   * Return whether the row is accessible
   *
   * @return
   */
  protected boolean isAccessible() {
    return this.isAccessible;
  }

  /**
   * Used to reduce the seats remain when reserve seats in this row
   *
   * @param seatsRemain the number of seats remained
   */
  public void setSeatsRemain(int seatsRemain) {
    this.seatsRemain = seatsRemain;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Row)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    Row row = (Row) o;
    return this.seatsRemain == row.seatsRemain &&
        this.isAccessible == row.isAccessible;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.seatsRemain, this.isAccessible);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    StringBuilder visualized = new StringBuilder();
    for (Seat seat : this) {
      visualized.append(seat.toString()).append(" ");
    }
    return visualized.toString();
  }
}
