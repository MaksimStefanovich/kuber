package com.stefanovich.bookservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookInsertOptimisticLock {
    @Id
    private Long id;

    private boolean locked;

    private boolean successed;
    @Version
    private Long version;
}
