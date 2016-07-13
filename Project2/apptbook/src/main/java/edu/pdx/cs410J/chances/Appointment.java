package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointment;

import java.util.Date;

public class Appointment extends AbstractAppointment {
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
   * Set the time this appointment begins.
   */
  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
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
    return beginTime.toString();
  }

  /**
   * Get the time this appointment ends.
   *
   * @return String representation of appointment end time
   */
  @Override
  public String getEndTimeString() {
    return endTime.toString();
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
}
