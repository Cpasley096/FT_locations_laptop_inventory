package com.FT_locations.Repositories;

import com.FT_locations.Models.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LaptopRepo extends JpaRepository<Laptop, Integer> {
}
