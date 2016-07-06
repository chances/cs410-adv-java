package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointment;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
  public void setBeginTime() {
    throw new NotImplementedException();
  }

  /**
   * Set the time this appointment ends.
   */
  public void setEndTime() {
    throw new NotImplementedException();
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
}
