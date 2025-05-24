package com.limitedtimedeal.repository;

import com.limitedtimedeal.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
