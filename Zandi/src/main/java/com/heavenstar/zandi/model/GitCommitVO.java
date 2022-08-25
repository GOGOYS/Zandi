package com.heavenstar.zandi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonRootName( namespace ="GitCommitVO", value = "GitCommitVO")
public class GitCommitVO {
	
	public String sha;
	public String node_id;
	
	public Commit commit;
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public class Commit{
		
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
		
		public String message;
		
		public Tree tree;
		
		@Getter
		@Setter
		@AllArgsConstructor
		@NoArgsConstructor
		public class Tree{
			public String sha;
			public String url;
		}
		
		public String url;
		public String comment_count;
		
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
	
	/*
	 * public String url; public String html_url; public String comments_url;
	 * 
	 * public Author author;
	 * 
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @AllArgsConstructor
	 * 
	 * @NoArgsConstructor public class Author{ public String login; public String
	 * id; public String node_id; public String avatar_url; public String
	 * gravatar_id; public String url; public String html_url; public String
	 * followers_url; public String following_url; public String gists_url; public
	 * String starred_url; public String subscriptions_url; public String
	 * organizations_url; public String repos_url; public String events_url; public
	 * String received_events_url; public String type; public String site_admin; }
	 * 
	 * public Committer committer;
	 * 
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @AllArgsConstructor
	 * 
	 * @NoArgsConstructor public class Committer{ public String login; public String
	 * id; public String node_id; public String avatar_url; public String
	 * gravatar_id; public String url; public String html_url; public String
	 * followers_url; public String following_ur; public String gists_url; public
	 * String starred_url; public String subscriptions_url; public String
	 * organizations_url; public String repos_url; public String events_url; public
	 * String received_events_url; public String type; public String site_admin; }
	 * 
	 * 
	 * public Parents parents;
	 * 
	 * @Getter
	 * 
	 * @Setter
	 * 
	 * @AllArgsConstructor
	 * 
	 * @NoArgsConstructor public class Parents{ public String sha; public String
	 * url; public String html_url; }
	 */
	
}

