package org.example.userservice.services;

import org.example.models.dto.AppDto;
import org.example.userservice.models.entities.BaseEntity;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * @param <DTO>
 * @author Tribushko Danil
 */
public interface EntityService<DTO> {
    DTO findById(Long id);

    void deleteById(Long id);

    DTO update(DTO entity);
}
