package edu.eci.cvds.project.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "Laboratory")
public class Laboratory {
    @Id
    private String id;
    private String name;
    private List<Reservation> reservations;

}