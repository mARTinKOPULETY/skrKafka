package com.MartIN.skrkafka.repository;

import com.MartIN.skrkafka.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface DatabaseRepo extends JpaRepository<WikimediaData, Long> {
}
