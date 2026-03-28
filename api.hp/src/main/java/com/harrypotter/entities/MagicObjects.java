package com.harrypotter.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "object")
public class MagicObjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	@NotBlank(message = "name is required")
	@Size(max = 45, message = "name must not exceed 45 characters")
    private String name;
	@NotBlank(message = "info is required")
    private String info;
	@NotBlank(message = "picture is required")
	@Size(max = 100, message = "picture must not exceed 100 characters")
    private String picture;
    @Column(name = "is_horocruxe")
	@NotNull(message = "isHorocruxe is required")
    private Boolean isHorocruxe;
    @Column(name = "is_hollow")
	@NotNull(message = "isDeathHollow is required")
    private Boolean isDeathHollow;
    @Column(name = "is_wander")
	@NotNull(message = "isWander is required")
    private Boolean isWander;
    @Column(name = "is_other")
	@NotNull(message = "isOtherMagicObject is required")
    private Boolean isOtherMagicObject;
    @Column(name = "is_quiddich")
	@NotNull(message = "isQuiddich is required")
    private Boolean isQuiddich;

    //Relation with Wizard
    @OneToMany(mappedBy = "mo",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Wizard> ownerWander = new HashSet<>();

    //Relation with Wizard
    @OneToMany(mappedBy = "mo",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Wizard> ownerMagicObject = new HashSet<>();
}
