package com.heavenstar.zandi.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserVO {
	
	public String username;
	public String password;
	public String nickname;
	public String email;
	
	List<GitCommitVO> gitList;

}
