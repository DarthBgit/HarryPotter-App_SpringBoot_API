package com.harrypotter.entities;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Wizard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  @NotBlank(message = "name is required")
  @Size(max = 100, message = "name must not exceed 100 characters")
    private String name;
  @Min(value = 0, message = "age must be greater than or equal to 0")
    private Integer age;
  @NotBlank(message = "rol is required")
  @Size(max = 100, message = "rol must not exceed 100 characters")
    private String rol;
  @NotBlank(message = "actor is required")
  @Size(max = 100, message = "actor must not exceed 100 characters")
    private String actor;
  @NotBlank(message = "housename is required")
  @Size(max = 100, message = "housename must not exceed 100 characters")
    private String housename;
  @NotBlank(message = "picture is required")
  @Size(max = 100, message = "picture must not exceed 100 characters")
    private String picture;
  @NotBlank(message = "bibliography is required")
    private String bibliography;
  @NotBlank(message = "facemember is required")
  @Size(max = 100, message = "facemember must not exceed 100 characters")
    private String facemember;
  @NotBlank(message = "color is required")
  @Size(max = 100, message = "color must not exceed 100 characters")
    private String color;
  @NotBlank(message = "familyname is required")
  @Size(max = 100, message = "familyname must not exceed 100 characters")
    private String familyname;
  @NotBlank(message = "blood is required")
  @Size(max = 45, message = "blood must not exceed 45 characters")
    private String blood;
  @NotBlank(message = "patronus is required")
  @Size(max = 45, message = "patronus must not exceed 45 characters")
    private String patronus;
    @Column(name = "wander_info")
    private String wanderInfo;
    private boolean mortifago;
    @Column(name = "animals_fantastics")
    private boolean animalsFantastics;
    private boolean student;
    private boolean others;
    private boolean teacher;

    @ManyToOne
    @JoinColumn(name = "wizards_wanders")//Name of column (Remember to insert this column in sql)
    private MagicObjects mw;

    @ManyToOne
    @JoinColumn(name = "wizards_objects")//Name of column (Remember to insert this column in sql)
    private MagicObjects mo;
}
