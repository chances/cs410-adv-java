package edu.pdx.cs410J.chances;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit tests for the {@link Appointment} class.
 */
public class AppointmentTest {

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

  @Test
  public void canCreateAppointmentWithDescription() {
    final String DESCRIPTION = "Foobar";
    Appointment appointment = new Appointment(DESCRIPTION);
    assertThat(appointment.getDescription(), is(equalTo(DESCRIPTION)));
  }

  @Test
  public void setBeginTimeAcceptsADateObject() {
    Appointment appointment = new Appointment();
    appointment.setBeginTime(new Date());
  }

  @Test
  public void setEndTimeAcceptsADateObject() {
    Appointment appointment = new Appointment();
    appointment.setEndTime(new Date());
  }
}
