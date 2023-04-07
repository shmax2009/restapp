package de.ssw.restapp.domain.repository;

import de.ssw.restapp.domain.model.Image;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    @NonNull Optional<Image> findById(@NonNull Long id);
}
