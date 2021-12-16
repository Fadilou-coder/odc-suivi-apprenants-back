package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
