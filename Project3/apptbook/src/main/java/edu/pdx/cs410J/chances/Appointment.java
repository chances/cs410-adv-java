package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment extends AbstractAppointment implements Comparable<Appointment> {
  public static final SimpleDateFormat DATE_FORMAT =
          new SimpleDateFormat("MM/dd/yyyy kk:mm");
  public static final DateFormat SHORT_DATE_FORMAT =
          DateFormat.getTimeInstance(DateFormat.SHORT);

  private Date beginTime;
  private Date endTime;
  private String description;

  /**
   * Construct a new appointment given a description.
   *
   * @param description Description of the new appointment
     */
  public Appointment(String description) {
    this.description = description;
  }

  /**
   * Get the time this appointment begins.
   */
  @Override
  public Date getBeginTime()
  {
    return beginTime;
  }

  /**
   * Set the time this appointment begins.
   */
  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  /**
   * Get the time this appointment ends.
   */
  @Override
  public Date getEndTime()
  {
    return endTime;
  }

  /**
   * Set the time this appointment ends.
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * Get the time this appointment begins.
   *
   * @return String representation of appointment begin time
     */
  @Override
  public String getBeginTimeString() {
    return SHORT_DATE_FORMAT.format(beginTime);
  }

  /**
   * Get the time this appointment ends.
   *
   * @return String representation of appointment end time
   */
  @Override
  public String getEndTimeString() {
    return SHORT_DATE_FORMAT.format(endTime);
  }

  /**
   * Get the description of this appointment.
   *
   * @return Description of this appointment
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Compares this appointment to another.
   *
   * Appointments should be sorted chronologically by their beginning time. If
   * two appointment begin at the same time, they should be sorted
   * chronologically by their ending time. Otherwise, the two appointments
   * should be sorted lexicographically by their descriptions.
   *
   * @param other Appointment to compare to
   * @return Ordinal difference between Appointments, zero if identical
     */
  @Override
  public int compareTo(Appointment other) {
    int difference = 0;

    // Compare begin times
    difference = this.beginTime.compareTo(other.beginTime);

    // Compare end times if begin times are identical
    difference = (difference == 0) ?
            this.endTime.compareTo(other.endTime) : difference;

    // Compare descriptions if begin and end times are identical
    difference = (difference == 0) ?
            this.description.compareTo(other.description) : difference;

    return difference;
  }
}
