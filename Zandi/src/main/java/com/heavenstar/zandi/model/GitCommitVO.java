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
@Builder
@ToString
public class GitCommitVO {
	
	private String reponame;
	
	private String message;
	private String url;
	private String comment_count;
	
	public Author author;                                                               

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Author{
		public String name;
		public String email;
		public String date;
	}
	
	public Committer committer;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Committer{
		public String name;
		public String email;
		public String date;
	}
	
	public Tree tree;

	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Tree{
		public String sha;
		public String url;
	}
	
	public Verification verification;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Verification{
		public String verified;
		public String reason;
		public String signature;
		public String payload;
	}
}

//locadatatime을 사용해서 날짜 한국시간으로 바꾸기
