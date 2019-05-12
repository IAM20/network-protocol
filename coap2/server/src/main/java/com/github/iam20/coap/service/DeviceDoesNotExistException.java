package com.github.iam20.coap.service;

class DeviceDoesNotExistException extends RuntimeException {
	DeviceDoesNotExistException() {
		super("Device does not exists");
	}
}
