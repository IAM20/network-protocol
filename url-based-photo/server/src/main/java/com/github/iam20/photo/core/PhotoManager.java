package com.github.iam20.photo.core;

import static com.github.iam20.photo.msg.DbSqls.*;

import javax.sql.DataSource;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.github.iam20.photo.model.Photo;
import com.github.iam20.photo.model.PhotoGroup;

@Component
public class PhotoManager {
	private NamedParameterJdbcTemplate jdbcTemplate;
	private MapSqlParameterSource namedParameters;
	private RowMapper<Photo> photoRowMapper = BeanPropertyRowMapper.newInstance(Photo.class);
	private RowMapper<PhotoGroup> photoGroupRowMapper = BeanPropertyRowMapper.newInstance(PhotoGroup.class);

	@Autowired
	public PhotoManager(DataSource dataSource) {
		this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		namedParameters = new MapSqlParameterSource();
	}

	public List<PhotoGroup> getAllPhotoGroup() {
		return jdbcTemplate.query(GET_ALL_PHOTO_GROUP, photoGroupRowMapper);
	}

	public PhotoGroup getPhotoGroupById(long id) {
		namedParameters.addValue("groupId", id);
		return jdbcTemplate.queryForObject(GET_PHOTO_GROUP_BY_ID, namedParameters, photoGroupRowMapper);
	}

	public Photo getPhotoById(long id) {
		namedParameters.addValue("photoId", id);
		return jdbcTemplate.queryForObject(GET_PHOTO_BY_ID, namedParameters, photoRowMapper);
	}

	public List<Photo> getPhotoByGroupId(long groupId) {
		namedParameters.addValue("groupId", groupId);
		return jdbcTemplate.query(GET_PHOTO_BY_GROUP, namedParameters, photoRowMapper);
	}

	public PhotoGroup getPhotoByGroupName(String name) {
		namedParameters.addValue("groupName", name);
		return jdbcTemplate.queryForObject(GET_PHOTO_GROUP_BY_NAME, namedParameters, photoGroupRowMapper);
	}

	public void insertPhoto (PhotoGroup group, Photo photo) {
		namedParameters.addValue("name", photo.getName());
		namedParameters.addValue("photoGroupId", group.getId());
		namedParameters.addValue("mimeType", photo.getMimeType());
		namedParameters.addValue("url", photo.getUrl());
		namedParameters.addValue("comment", photo.getComment());
		jdbcTemplate.update(INSERT_PHOTO_FULL_ARGS, namedParameters);
	}

	public void insertPhotoGroup (PhotoGroup group) {
		namedParameters.addValue("groupName", group.getName());
		jdbcTemplate.update(INSERT_PHOTO_GROUP, namedParameters);
	}

	public void updatePhotoGroup (PhotoGroup group) {
		namedParameters.addValue("name", group.getName());
		namedParameters.addValue("groupId", group.getId());
		jdbcTemplate.update(UPDATE_PHOTO_GROUP_BY_ID, namedParameters);
	}

	public void updatePhoto(Photo photo) {
		namedParameters.addValue("name", photo.getName());
		namedParameters.addValue("groupId", photo.getPhotoGroupId());
		namedParameters.addValue("mimeType", photo.getMimeType());
		namedParameters.addValue("url", photo.getUrl());
		namedParameters.addValue("comment", photo.getComment());
		namedParameters.addValue("photoId", photo.getId());
		jdbcTemplate.update(UPDATE_PHOTO_BY_ID, namedParameters);
	}

	public int deletePhoto(Photo photo) {
		namedParameters.addValue("photoId", photo.getId());
		return jdbcTemplate.update(DELETE_PHOTO_BY_ID, namedParameters);
	}

	public int deletePhotoGroup (PhotoGroup group) {
		namedParameters.addValue("groupId", group.getId());
		return jdbcTemplate.update(DELETE_PHOTO_GROUP_BY_ID, namedParameters);
	}

}
