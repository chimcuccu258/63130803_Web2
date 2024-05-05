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
public class Route {
    private Long routeID;
    private String origin;
    private String destination;
    private Double distance;
    private Date estimatedTime;
    private Date created_at;
    private Date updated_at;
}
