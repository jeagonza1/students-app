package com.example.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class LogEmailResponse {

	private long id;

	private String fullName;

	private String email;
}
