package com.college.medicare.services;

import com.college.medicare.Utils.Utils;
import com.college.medicare.domin.Doctor;
import com.college.medicare.domin.Patient;
import com.college.medicare.repository.DoctorRepository;
import com.college.medicare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
public class Appointment {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    void bookAppointment() {
        Patient patient = new Patient();
        System.out.println("First Name: ");
        patient.setFullName(Utils.readString(null));

        System.out.println("Surname: ");
        patient.setSurname(Utils.readString(null));

        System.out.println("Mobile: ");
        patient.setMobile(String.valueOf(Utils.readMobile()));

        System.out.println("Date of birth(dd/MM/yyyy): ");
        patient.setDateOfBirth(Utils.readDate());

        System.out.println("Country of origin: ");
        patient.setCountryOfOrigin(Utils.readString(null));
        showDoctorsList(patient);

    }

    private void showDoctorsList(Patient patient) {
        List<Doctor> doctorList = doctorRepository.findAll();
        printDoctorsList(doctorList);
        patient.setDoctorId(doctorList.get(Utils.readInt(doctorList.size())).getId());
        patientRepository.save(patient);
        System.out.println("*******Booked********");
    }

    void cancelAppointment() {
        System.out.println("Enter name of patient to cancel :");
        patientRepository.deleteByFullName(Utils.readString(null));
        System.out.println("**Appointment canceled");
    }


    void viewDoctorSchedules() {
        List<Doctor> doctorList = doctorRepository.findAll();
        printDoctorsList(doctorList);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Patient> patientList = patientRepository.findByDocterId(doctorList.get(Utils.readInt(doctorList.size())).getId());
        if (patientList != null && patientList.size() > 0) {
            System.out.println("****Schedules******");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%5s %30s %20s %20s %20s", "ID", "First Name", "Surname", "Date of birth", "Country of origin");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");
            int i = 1;
            for (Patient patient : patientList) {
                System.out.format("%5d %30s %20s %20s %20s",
                        i++, patient.getFullName(), patient.getSurname(), dateFormat.format(patient.getDateOfBirth()), patient.getCountryOfOrigin());
                System.out.println();
                System.out.println(patient.getFullName());

            }
        } else {
            System.out.println("No appointment");
        }
    }

    private void printDoctorsList(List<Doctor> doctorList) {
        int i = 0;
        System.out.println("Available doctors: ");
        for (Doctor doctor : doctorList) {
            System.out.println(i++ + ": " + doctor.getName());
        }
        System.out.println("Choose doctor: ");
    }

    void viewPatientAppointment() {
        List<Patient> patientList = patientRepository.findAll();
        printPatientList(patientList);
        Optional<Patient> patient = patientRepository.findById(patientList.get(Utils.readInt(patientList.size())).getId());
        if (patient.isPresent()) {
            Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctorId());
            doctor.ifPresent(doctor1 -> System.out.println(doctor1.getName()));
        } else {
            System.out.println("No appointment");
        }
    }

    private void printPatientList(List<Patient> patientList) {
        int i = 0;
        if(patientList!=null && patientList.size()>0) {
            for (Patient patient : patientList) {
                System.out.println(i++ + ": " + patient.getFullName());
            }
            System.out.println("Choose patient: ");
        }else {
            System.out.println("No patient today");
        }
    }
}
