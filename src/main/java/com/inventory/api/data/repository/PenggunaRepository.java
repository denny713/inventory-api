package com.inventory.api.data.repository;

import com.inventory.api.data.entity.core.Pengguna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PenggunaRepository extends JpaRepository<Pengguna, Long>, JpaSpecificationExecutor<Pengguna> {
}
