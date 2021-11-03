package com.meli.SpringbootBuildingblocks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.SpringbootBuildingblocks.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
