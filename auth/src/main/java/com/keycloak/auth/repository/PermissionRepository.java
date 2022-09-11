package com.keycloak.auth.repository;

import com.keycloak.auth.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {

    @Query("select p.name from Permission p where p.id in :ids")
    List<String> findPermissionsByIdIn(@Param("ids") List<Long> ids);
}
