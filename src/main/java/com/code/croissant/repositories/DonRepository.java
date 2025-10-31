package com.code.croissant.repositories;

import com.code.croissant.model.Don;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonRepository extends JpaRepository<Don, Long> {
    List<Don> findByDonateur_Id(Long donnateurId);

}
