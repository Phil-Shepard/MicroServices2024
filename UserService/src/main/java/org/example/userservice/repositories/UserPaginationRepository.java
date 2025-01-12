package org.example.userservice.repositories;

import org.example.userservice.models.entities.Role;
import org.example.userservice.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tribushko Danil
 * @since 12.12.2024
 * Репозиторий для работы с пользователями с пагинацией
 */
@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<User, Long> {
    Page<User> findAllByRolesContains(Pageable pageable, Role role);
}
