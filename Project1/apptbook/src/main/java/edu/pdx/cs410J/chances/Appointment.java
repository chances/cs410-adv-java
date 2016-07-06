package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointment;

public class Appointment extends AbstractAppointment {
  private String beginTime;
  private String endTime;
  private String description;

  public Appointment() {}

  public Appointment(String description) {
    this.description = description;
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
