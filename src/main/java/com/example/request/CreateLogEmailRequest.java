package com.example.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class CreateLogEmailRequest {

	private String fullName;

	private String email;

}
