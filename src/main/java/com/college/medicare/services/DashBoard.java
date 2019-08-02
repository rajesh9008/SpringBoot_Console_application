package com.college.medicare.services;

import com.college.medicare.Utils.Utils;
import com.college.medicare.domin.Doctor;
import com.college.medicare.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DashBoard {

    @Autowired
    private Appointment appointment;

    @Autowired
    private DoctorRepository doctorRepository;

    public void initDB() {
        List<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor(1L, "Dr. Todor Zhikov", true));
        doctorList.add(new Doctor(2L, "Dr. Emil Constantinescu ", true));
        doctorList.add(new Doctor(3L, "Dr. Steven Seagal", true));
        doctorList.add(new Doctor(4L, "Dr. Igor Dondon", true));
        doctorRepository.saveAll(doctorList);
    }

    public void showMenu() {

        System.out.println("\n");
        System.out.println("**********************");
        System.out.println("*      MEDICARE      *");
        System.out.println("**********************");
        System.out.println("1.Book an appointment");
        System.out.println("2.Cancel an appointment");
        System.out.println("3.View a doctor's schedule");
        System.out.println("4.View a patient's appointment");
        System.out.println("5.Exit");
        System.out.println("\n");


        System.out.println("Please enter an option in number: ");
        int option = Utils.readInt(5);
        switch (option) {
            case 1:
                appointment.bookAppointment();
                showMenu();
                break;
            case 2:
                appointment.cancelAppointment();
                showMenu();
                break;
            case 3:
                appointment.viewDoctorSchedules();
                showMenu();
                break;
            case 4:
                appointment.viewPatientAppointment();
                showMenu();
                break;
            case 5:
                System.out.println("*****Bye********");
                System.out.println("\n");
                break;
            default:
                System.out.println("Please enter valid number");
                showMenu();
        }
    }
}
