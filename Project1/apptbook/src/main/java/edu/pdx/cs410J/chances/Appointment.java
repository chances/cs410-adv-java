package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointment;

import java.util.Date;

public class Appointment extends AbstractAppointment {
  private String beginTime;
  private String endTime;
  private String description;

  public Appointment() {}

  public Appointment(String description) {
    this.description = description;
  }

  /**
   * Set the time this appointment begins.
   */
  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime.toString();
  }

  /**
   * Set the time this appointment ends.
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime.toString();
  }

  /**
   * Get the time this appointment begins.
   *
   * @return String representation of appointment begin time
     */
  @Override
  public String getBeginTimeString() {
    return beginTime;
  }

  /**
   * Get the time this appointment ends.
   *
   * @return String representation of appointment end time
   */
  @Override
  public String getEndTimeString() {
    return endTime;
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
