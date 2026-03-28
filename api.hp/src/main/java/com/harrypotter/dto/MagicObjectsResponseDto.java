package com.harrypotter.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MagicObjectsResponseDto {
    private Integer id;
  @NotBlank(message = "name is required")
  @Size(max = 45, message = "name must not exceed 45 characters")
    private String name;
  @NotBlank(message = "info is required")
    private String info;
  @NotBlank(message = "picture is required")
  @Size(max = 100, message = "picture must not exceed 100 characters")
    private String picture;
  @NotNull(message = "isHorocruxe is required")
    private Boolean isHorocruxe;
  @NotNull(message = "isDeathHollow is required")
    private Boolean isDeathHollow;
  @NotNull(message = "isWander is required")
    private Boolean isWander;
  @NotNull(message = "isOtherMagicObject is required")
    private Boolean isOtherMagicObject;
  @NotNull(message = "isQuiddich is required")
    private Boolean isQuiddich;
}

