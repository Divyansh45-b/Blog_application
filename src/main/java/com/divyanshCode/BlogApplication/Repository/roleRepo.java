package com.divyanshCode.BlogApplication.Repository;
import com.divyanshCode.BlogApplication.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface roleRepo extends JpaRepository<Role,Integer> {

   Optional<Role> findByRoleName(String roleName);
}
