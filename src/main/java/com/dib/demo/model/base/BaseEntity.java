package com.dib.demo.model.base;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Base entity datebase model
 */

@Getter
@MappedSuperclass
@ToString(of = {"id"})
public abstract class BaseEntity implements Serializable {

    /**
     * Unique identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * Version
     */
    @Version
    private Long version;
}
