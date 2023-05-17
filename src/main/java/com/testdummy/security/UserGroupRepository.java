package com.testdummy.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

    // Add any additional custom queries if needed
    @Query("SELECT ug FROM UserGroup ug JOIN ug.users u WHERE u.id = :userId")
    List<UserGroup> findByUserId(@Param("userId") Long userId);

}
