package org.ngavm1.deliverysystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    private Long salaryID;
    private Double hourlyWage;
    private Integer workingHourPerWeek;
    private Date created_at;
    private Date updated_at;
}
