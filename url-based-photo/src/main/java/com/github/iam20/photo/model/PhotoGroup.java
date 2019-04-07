package com.github.iam20.photo.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoGroup {
	private Long id;
	private String name;
	private List<Photo> photoList;
}
