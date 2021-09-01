package com.tim40.tim40.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.tim40.tim40.model.Patient;
import com.tim40.tim40.model.Pharmacist;
import com.tim40.tim40.model.Pharmacy;
import com.tim40.tim40.model.Therapy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShiftsDTO {
 LocalDateTime startTime;
 LocalDateTime endTime;
 Integer startShift;
 Integer endShift;
}
