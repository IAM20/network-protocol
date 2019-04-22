package com.github.iam20.photo.api;

class BadRequestException extends RuntimeException {
	BadRequestException() {
		super ("400 BAD REQUEST");
	}

	BadRequestException(String msg) {
		super("400 BAD REQUEST\n" + msg);
	}
}
