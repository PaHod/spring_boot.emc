package com.spring.boot.emc.mapper;

import com.spring.boot.emc.dto.PatientDTO;
import com.spring.boot.emc.dto.PatientSimpleDTO;
import com.spring.boot.emc.entity.Doctor;
import com.spring.boot.emc.entity.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;

@Component
public class PatientMapper {

    private DoctorMapper doctorMapper;

    @Autowired
    public void setDoctorMapper(DoctorMapper doctorMapper) {
        this.doctorMapper = doctorMapper;
    }

    /**
     * skip id and doctors fields
     *
     * @return new Patient entity
     */
    public Patient toNewEntity(PatientDTO patientDTO) {
        return updateEntityFromDTO(new Patient(), patientDTO);
    }

    public Patient updateEntityFromDTO(Patient patient, PatientDTO patientDTO) {
        patient.setFirstName(ofNullable(patientDTO.getFirstName()).orElse(patient.getFirstName()));
        patient.setLastName(ofNullable(patientDTO.getLastName()).orElse(patient.getLastName()));
        patient.setSex(ofNullable(patientDTO.getSex()).orElse(patient.getSex()));
        patient.setAddress(ofNullable(patientDTO.getAddress()).orElse(patient.getAddress()));
        patient.setPhoneNumber(ofNullable(patientDTO.getPhoneNumber()).orElse(patient.getPhoneNumber()));
        return patient;
    }

    public PatientDTO toDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setId(patient.getId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setSex(patient.getSex());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setPhoneNumber(patient.getPhoneNumber());

        List<Doctor> doctors = ofNullable(patient.getDoctors()).orElse(Collections.emptyList());
        patientDTO.setDoctors(doctors.stream()
                .map(doctorMapper::toSimpleDTO)
                .collect(toList()));
        return patientDTO;
    }

    public PatientSimpleDTO toSimpleDTO(Patient patient) {
        PatientSimpleDTO patientSimpleDTO = new PatientSimpleDTO();
        patientSimpleDTO.setId(patient.getId());
        patientSimpleDTO.setFirstName(patient.getFirstName());
        patientSimpleDTO.setLastName(patient.getLastName());
        return patientSimpleDTO;
    }
}
