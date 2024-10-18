package com.kimyeontae.history_info.users.repository;

import com.kimyeontae.history_info.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}
