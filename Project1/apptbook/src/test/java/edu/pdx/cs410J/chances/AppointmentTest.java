package edu.pdx.cs410J.chances;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

  @Test
  public void getBeginTimeStringNeedsToBeImplemented() {
    Appointment appointment = new Appointment();
    appointment.getBeginTimeString();
  }

  @Test
  public void initiallyAllAppointmentsHaveNullDescription() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getDescription(), is(nullValue()));
  }

  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

}
