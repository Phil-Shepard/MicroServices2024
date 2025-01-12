package org.example.taskservice.services;

/**
 * @param <DTO>
 * @author Tribushko Danil
 */
public interface EntityService<DTO> {
    DTO findById(Long id);
    DTO save(DTO dto);
    void deleteById(Long id);
    DTO update(DTO entity);
}
