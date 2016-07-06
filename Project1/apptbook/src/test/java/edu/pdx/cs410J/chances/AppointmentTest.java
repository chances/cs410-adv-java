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
  public void initiallyDescriptionIsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getDescription(), is(nullValue()));
  }

  @Test
  public void initiallyGetBeginTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTime(), is(nullValue()));
  }

  @Test
  public void initiallyGetEndTimeReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getEndTime(), is(nullValue()));
  }

  @Test
  public void initiallyGetBeginTimeStringReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getBeginTimeString(), is(nullValue()));
  }

  @Test
  public void initiallyGetEndTimeStringReturnsNull() {
    Appointment appointment = new Appointment();
    assertThat(appointment.getEndTimeString(), is(nullValue()));
  }
}
