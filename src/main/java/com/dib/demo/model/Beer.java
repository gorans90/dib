package com.dib.demo.model;

import com.dib.demo.model.base.BaseEntityAudit;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Beer model class
 */

@Entity
@Table(name = "beer")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beer extends BaseEntityAudit {

    /**
     * External id
     */
    @Column(name = "external_id", nullable = false, unique = true)
    private Long externalId;

    /**
     * Beer name
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Beer description
     */
    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "mash_temp", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> mashTemp;

}
