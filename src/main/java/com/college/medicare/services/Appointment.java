package com.college.medicare.services;

import com.college.medicare.domin.Doctor;
import com.college.medicare.domin.Patient;
import com.college.medicare.repository.DoctorRepository;
import com.college.medicare.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class Appointment {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    void bookAppointment() {
        Patient patient = new Patient();
        Scanner scanner = new Scanner(System.in);
        System.out.println("First Name: ");
        patient.setFullName(scanner.nextLine());

        System.out.println("Surname: ");
        patient.setSurname(scanner.nextLine());

        System.out.println("Mobile: ");
        patient.setMobile(scanner.nextLine());

        System.out.println("Date of birth(dd/MM/yyyy): ");
        try {
            patient.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println("Country of origin: ");
        patient.setCountryOfOrigin(scanner.nextLine());
        showDoctorsList(patient);

    }

    private void showDoctorsList(Patient patient) {
        Scanner scanner = new Scanner(System.in);
        List<Doctor> doctorList = doctorRepository.findAll();
        printDoctorsList(doctorList);
        patient.setDoctorId(doctorList.get(scanner.nextInt()).getId());
        patientRepository.save(patient);
        System.out.println("*******Booked********");
    }

    void cancelAppointment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of patient to cancel :");
        patientRepository.deleteByFullName(scanner.nextLine());
        System.out.println("**Appointment canceled");
    }


    void viewDoctorSchedules() {
        Scanner scanner = new Scanner(System.in);
        List<Doctor> doctorList = doctorRepository.findAll();
        printDoctorsList(doctorList);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        List<Patient> patientList = patientRepository.findByDocterId(doctorList.get(scanner.nextInt()).getId());
        if (patientList != null && patientList.size() > 0) {
            System.out.println("****Schedules******");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.printf("%5s %30s %20s %20s %20s", "ID", "First Name", "Surname", "Date of birth", "Country of origin");
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");
            int i =1;
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
        for (Doctor doctor : doctorList) {
            System.out.println(i++ + ": " + doctor.getName());
        }
        System.out.println("Choose doctor: ");
    }

    void viewPatientAppointment() {
        Scanner scanner = new Scanner(System.in);
        List<Patient> patientList = patientRepository.findAll();
        printPatientList(patientList);
        Optional<Patient> patient = patientRepository.findById(patientList.get(scanner.nextInt()).getId());
        if (patient.isPresent()) {
            Optional<Doctor> doctor = doctorRepository.findById(patient.get().getDoctorId());
            if(doctor.isPresent()){
                System.out.println(doctor.get().getName());
            }
        } else {
            System.out.println("No appointment");
        }
    }

    private void printPatientList(Iterable<Patient> patientList) {
        int i = 0;
        for (Patient patient : patientList) {
            System.out.println(i++ + ": " + patient.getFullName());
        }
        System.out.println("Choose patient: ");
    }
}
