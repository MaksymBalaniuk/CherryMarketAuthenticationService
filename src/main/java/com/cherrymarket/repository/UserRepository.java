package com.cherrymarket.repository;

import com.cherrymarket.entity.UserEntity;
import jakarta.annotation.Nonnull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByMarketIdAndUsername(UUID marketId, String username);

    Boolean existsByMarketIdAndUsername(UUID marketId, String username);

    Boolean existsByMarketIdAndEmail(UUID marketId, String email);

    @Nonnull
    Page<UserEntity> findAllByMarketId(UUID marketId, @Nonnull Pageable pageable);

}
