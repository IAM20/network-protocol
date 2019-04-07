package com.github.iam20.photo.api;

import com.github.iam20.photo.core.PhotoManager;
import com.github.iam20.photo.model.Photo;
import com.github.iam20.photo.model.PhotoGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class ApiController {
	private PhotoManager photoManager;

	@Autowired
	public ApiController(PhotoManager manager) {
		photoManager = manager;
	}

	@GetMapping("/{photoGroupName}")
	public PhotoGroup getPhotoByGroupName(@PathVariable String photoGroupName) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);
		List<Photo> photoList = photoManager.getPhotoByGroupId(group.getId());
		group.setPhotoList(photoList);
		return group;
	}

	@GetMapping("/all/photo/{photoId}")
	public Photo getPhotoById(@PathVariable String photoId) {
		return returnPhotoById(photoId);
	}

	@PostMapping("/{photoGroupName}")
	public PhotoGroup postPhotoGroupByName(@PathVariable String photoGroupName) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);
		if (group != null) {
			// 400 BAD REQUEST
			throw new BadRequestException();
		}

		group = PhotoGroup.builder()
					.name(photoGroupName)
					.build();

		photoManager.insertPhotoGroup(group);
		return group;
	}

	@PostMapping("/{photoGroupName}/photo")
	public Photo postPhotoIntoGroupName(@PathVariable String photoGroupName, @RequestBody Photo photo) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);
		if (group == null) {
			throw new BadRequestException();
		}
		photoManager.insertPhoto(group, photo);
		return photo;
	}

	@PutMapping("/{photoGroupName}")
	public PhotoGroup modifyPhotoGroup(@PathVariable String photoGroupName, @RequestBody PhotoGroup group) {
		PhotoGroup photoGroup = photoManager.getPhotoByGroupName(photoGroupName);
		if (photoGroup == null) {
			throw new BadRequestException();
		}
		photoGroup.setName(group.getName());
		photoManager.updatePhotoGroup(photoGroup);
		return photoGroup;
	}

	@PutMapping("/all/photo/{photoId}")
	public Photo modifyPhoto(@PathVariable String photoId, @RequestBody Photo receivedPhoto) {
		if (photoId == null || "".equals(photoId)) {
			// 404 PAGE NOT FOUND
			throw new NotFoundException();
		}
		long id = Long.parseLong(photoId);
		Photo photo = photoManager.getPhotoById(id);
		if (receivedPhoto.getName() != null) {
			photo.setName(receivedPhoto.getName());
		}
		if (receivedPhoto.getComment() != null) {
			photo.setComment(receivedPhoto.getComment());
		}
		if (receivedPhoto.getMimeType() != null) {
			photo.setMimeType(receivedPhoto.getMimeType());
		}
		if (receivedPhoto.getUrl() != null) {
			photo.setUrl(receivedPhoto.getUrl());
		}
		if (receivedPhoto.getPhotoGroupId() != null) {
			photo.setPhotoGroupId(receivedPhoto.getPhotoGroupId());
		}
		photoManager.updatePhoto(photo);
		return photo;
	}

	@DeleteMapping("/{photoGroupName}")
	public void deletePhotoGroup(@PathVariable String photoGroupName) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);
		if (group == null) {
			throw new BadRequestException();
		}
		photoManager.deletePhotoGroup(group);
	}

	@DeleteMapping("/all/photo/{photoId}")
	public void deletePhotoId(@PathVariable String photoId) {
		photoManager.deletePhoto(returnPhotoById(photoId));
	}

	private Photo returnPhotoById(String photoId) {
		if (photoId == null || "".equals(photoId)) {
			throw new NotFoundException();
		}
		long id = Long.parseLong(photoId);
		return photoManager.getPhotoById(id);
	}
}
