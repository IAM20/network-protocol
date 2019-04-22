package com.github.iam20.photo.api;

class NotFoundException extends RuntimeException {
	NotFoundException() {
		super("404 NOT FOUND");
	}

	NotFoundException(String msg) {
		super("404 NOT FOUND\n" + msg);
	}
}
