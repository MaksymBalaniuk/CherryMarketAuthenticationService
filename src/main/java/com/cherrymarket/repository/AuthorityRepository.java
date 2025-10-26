package com.cherrymarket.repository;

import com.cherrymarket.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, UUID> {

    List<AuthorityEntity> findAllByBasic(Boolean basic);

}
