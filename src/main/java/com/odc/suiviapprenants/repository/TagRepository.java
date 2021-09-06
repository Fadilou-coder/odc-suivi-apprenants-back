package com.odc.suiviapprenants.repository;

import com.odc.suiviapprenants.entity.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
}
