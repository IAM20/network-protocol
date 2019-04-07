package com.github.iam20.photo.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
	private Long id;
	private Long photoGroupId;
	private String name;
	private String mimeType;
	private String url;
	private String comment;
}
