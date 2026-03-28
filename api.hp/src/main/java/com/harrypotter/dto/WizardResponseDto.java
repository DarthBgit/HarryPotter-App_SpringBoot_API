package com.harrypotter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WizardResponseDto {
	private Integer id;
	@NotBlank(message = "name is required")
	@Size(max = 100, message = "name must not exceed 100 characters")
	private String name;
	@NotNull(message = "age is required")
	@PositiveOrZero(message = "age must be greater than or equal to 0")
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
	private String wanderInfo;
	@NotNull(message = "mortifago is required")
	private Boolean mortifago;
	@NotNull(message = "animalsFantastics is required")
	private Boolean animalsFantastics;
	@NotNull(message = "student is required")
	private Boolean student;
	@NotNull(message = "others is required")
	private Boolean others;
	@NotNull(message = "teacher is required")
	private Boolean teacher;
	private Integer wandId;
	private Integer magicObjectId;
}
