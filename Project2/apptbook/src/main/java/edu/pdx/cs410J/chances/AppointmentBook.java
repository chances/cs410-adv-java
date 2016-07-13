package edu.pdx.cs410J.chances;

import edu.pdx.cs410J.AbstractAppointmentBook;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author chancesnow
 */
public class AppointmentBook extends AbstractAppointmentBook<Appointment> {
    private String owner;
    private ArrayList<Appointment> appointments;

    /**
     * Construct a new appointment book given its owner.
     *
     * @param owner The book's owner
     */
    public AppointmentBook(String owner)
    {
        this.owner = owner;
        this.appointments = new ArrayList<>();
    }

    /**
     * Get the owner's name.
     *
     * @return The owner's name
     */
    @Override
    public String getOwnerName() {
        return owner;
    }

    /**
     * Get the appointments added to this appointment book.
     *
     * @return Collection of appointments
     */
    @Override
    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Add an appointment to this appointment book
     *
     * @param appointment Appointment to add
     */
    @Override
    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }
}
