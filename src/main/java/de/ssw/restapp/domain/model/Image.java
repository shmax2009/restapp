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

    private String getExtension(String type) {
        if (type == null)
            return null;
        return switch (type) {
            case "image/png" -> "png";
            case "image/jpeg" -> "jpg";
            default -> null;
        };
    }

    public String getName() {
        return "#" + id.toString() + "." + getExtension(type);
    }
}
