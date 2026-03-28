package com.harrypotter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatureResponseDto {
    private Integer id;
  @NotBlank(message = "raze is required")
  @Size(max = 100, message = "raze must not exceed 100 characters")
    private String raze;
  @NotBlank(message = "resume is required")
    private String resume;
  @NotBlank(message = "phrase is required")
    private String phrase;
  @NotBlank(message = "classification is required")
  @Size(max = 45, message = "classification must not exceed 45 characters")
    private String classification;
  @NotBlank(message = "danger is required")
  @Pattern(regexp = "yes|no", message = "danger must be either 'yes' or 'no'")
    private String danger;
  @NotBlank(message = "picture is required")
  @Size(max = 100, message = "picture must not exceed 100 characters")
    private String picture;
    private Set<CreatureTagResponseDto> tags;
}

