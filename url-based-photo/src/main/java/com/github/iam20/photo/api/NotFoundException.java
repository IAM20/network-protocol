package com.github.iam20.photo.api;

class NotFoundException extends RuntimeException {
	NotFoundException() {
		super("Could not find the page");
	}
}
