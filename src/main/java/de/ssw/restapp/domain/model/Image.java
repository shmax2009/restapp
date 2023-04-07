package de.ssw.restapp.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "image_table")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    public String getName() {
        return "#" + id.toString() + "." + type;
    }
}
