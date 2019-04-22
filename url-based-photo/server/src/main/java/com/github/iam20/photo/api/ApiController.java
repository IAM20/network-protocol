package com.github.iam20.photo.api;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

import com.github.iam20.photo.core.PhotoManager;
import com.github.iam20.photo.model.Photo;
import com.github.iam20.photo.model.PhotoGroup;

@Slf4j
@RestController
public class ApiController {
	private PhotoManager photoManager;

	@Autowired
	public ApiController(PhotoManager manager) {
		photoManager = manager;
	}

	@GetMapping("group/{photoGroupName}")
	public PhotoGroup getPhotoByGroupName(@PathVariable String photoGroupName) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);

		if (group == null) {
			throw new NotFoundException("Group " + photoGroupName + " does not exists");
		}
		List<Photo> photoList = photoManager.getPhotoByGroupId(group.getId());
		group.setPhotoList(photoList);
		return group;
	}

	@GetMapping("group/all/photo/{photoId}")
	public Photo getPhotoById(@PathVariable String photoId) {
		return returnPhotoById(photoId);
	}

	@PostMapping("group/{photoGroupName}")
	public PhotoGroup postPhotoGroupByName(@PathVariable String photoGroupName) {
		PhotoGroup group;
		try {
			group = photoManager.getPhotoByGroupName(photoGroupName);
		} catch (EmptyResultDataAccessException e) {
			group = null;
		}
		if (group != null) {
			// 400 BAD REQUEST
			throw new BadRequestException("Group " + photoGroupName + " does exist");
		}

		group = PhotoGroup.builder()
					.name(photoGroupName)
					.build();

		photoManager.insertPhotoGroup(group);
		return group;
	}

	/**
	 * Minimum requirements
	 * {
	 *     "url" : "https://github.com/iam20/0ea-ffff-f038"
	 *     "mimeType" : "jpg"
	 * }
	 */
	@PostMapping("group/{photoGroupName}/photo")
	public Photo postPhotoIntoGroupName(@PathVariable String photoGroupName, @RequestBody Photo photo) {
		PhotoGroup group;
		try {
			group = photoManager.getPhotoByGroupName(photoGroupName);
		} catch (EmptyResultDataAccessException e) {
			group = null;
		}
		if (group == null) {
			throw new BadRequestException("Group " + photoGroupName + " does not exist");
		} else if (!isPhotoRequestBodyCorrect(photo)) {
			log.error("{}", photo.getMimeType());
			throw new BadRequestException("Request body must have url and mime-type member");
		}
		photoManager.insertPhoto(group, photo);
		return photo;
	}

	@PutMapping("group/{photoGroupName}")
	public PhotoGroup modifyPhotoGroup(@PathVariable String photoGroupName, @RequestBody PhotoGroup group) {
		PhotoGroup photoGroup = photoManager.getPhotoByGroupName(photoGroupName);
		if (photoGroup == null || photoGroup.getName().equals("all")) {
			throw new BadRequestException();
		}
		photoGroup.setName(group.getName());
		photoManager.updatePhotoGroup(photoGroup);
		return photoGroup;
	}

	/**
	 * Minimum requirements
	 * {
	 *     "url" : "https://github.com/iam20/image/0ea-8883-daf"
	 *     "mimeType" : "jpg"
	 * }
	 */
	@PutMapping("group/all/photo/{photoId}")
	public Photo modifyPhoto(@PathVariable String photoId, @RequestBody Photo receivedPhoto) {
		if (photoId == null || "".equals(photoId)) {
			// 404 PAGE NOT FOUND
			throw new NotFoundException();
		} else if (!isPhotoRequestBodyCorrect(receivedPhoto)) {
			throw new BadRequestException("Request type must have url and mime-type member");
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

	@DeleteMapping("group/{photoGroupName}")
	public void deletePhotoGroup(@PathVariable String photoGroupName) {
		PhotoGroup group = photoManager.getPhotoByGroupName(photoGroupName);
		if (group == null) {
			throw new BadRequestException();
		}
		photoManager.deletePhotoGroup(group);
	}

	@DeleteMapping("group/all/photo/{photoId}")
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

	private boolean isPhotoRequestBodyCorrect(Photo photo) {
		return !(photo == null || photo.getUrl() == null || photo.getMimeType() == null);
	}
}
