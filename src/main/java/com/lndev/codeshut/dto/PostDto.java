package com.lndev.codeshut.dto;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
