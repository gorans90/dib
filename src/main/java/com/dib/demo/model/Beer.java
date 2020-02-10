package com.dib.demo.model;

import com.dib.demo.model.base.BaseEntityAudit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    /**
     * Collection of integer values for mash temp
     */
    @Column(name = "mash_temp", nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "beer_mash_temp",
            joinColumns = @JoinColumn(name = "beer_id")
    )
    private List<Integer> mashTemp;

}
