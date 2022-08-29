package com.heavenstar.zandi.model;

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
@ToString
@Builder
public class CommentVO {
	
	private long c_seq;
	private long c_gseq;
	private String c_username;
	private String c_comment;

}