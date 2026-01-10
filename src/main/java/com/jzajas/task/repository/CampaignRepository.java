package com.jzajas.task.repository;

import com.jzajas.task.model.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    Page<Campaign> findByStatusTrue(Pageable pageable);
}
