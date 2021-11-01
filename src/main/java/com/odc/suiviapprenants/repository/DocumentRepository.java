package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document,Long> {
}
