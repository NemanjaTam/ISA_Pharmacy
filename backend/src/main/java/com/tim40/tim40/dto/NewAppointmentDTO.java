package com.tim40.tim40.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import com.tim40.tim40.model.Medication;
import com.tim40.tim40.model.MedicationRating;
import com.tim40.tim40.model.enums.MedicationForm;
import com.tim40.tim40.model.enums.PrescriptionRegime;
import com.tim40.tim40.model.enums.TypeOfMedication;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewAppointmentDTO {
Long dermatologistId;
Long pharmacyId;
LocalDateTime startTime;
int duration;
int hours;
int price;
}
