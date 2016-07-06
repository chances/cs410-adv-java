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

  @Override
  public String getBeginTimeString() {
    return beginTime;
  }

  @Override
  public String getEndTimeString() {
    return endTime;
  }

  @Override
  public String getDescription() {
    return description;
  }

  public void setBeginAndEndTimesFromCommandLineArgs(String dateTimes) {

  }
}
