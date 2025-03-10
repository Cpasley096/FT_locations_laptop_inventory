package com.FT_locations.Repositories;

import com.FT_locations.Models.Laptop;
import com.FT_locations.Models.Status;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Integer> {
    List<Laptop> findByStatus(Status status, Sort sort);
}
