package com.oauth.authserver.bo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Msg implements Serializable {

	private static final long serialVersionUID = -4396306337717549311L;

	private String title;
	private String content;
	private String extraInfo;
}
