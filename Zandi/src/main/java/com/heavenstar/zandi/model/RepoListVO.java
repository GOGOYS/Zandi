package com.heavenstar.zandi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoListVO {
	
	public String id;
	public String node_id;
	public String name;
	public String full_name;
	@JsonProperty("private")
	public String pprivate;
	
	public Owner owner;
    public class Owner{
    	
    	public String login;
    	public String id;
    	public String node_id;
    	public String avatar_url;
    	public String gravatar_id;
    	public String url;
    	public String html_url;
    	public String followers_url;
    	public String following_url;
    	public String gists_url;
    	public String starred_url;
    	public String subscriptions_url;
    	public String organizations_url;
    	public String repos_url;
    	public String events_url;
    	public String received_events_url;
    	public String type;
    	public String site_admin;
    }
    public String html_url;
    public String description;
    public String fork;
    public String url;
    public String forks_url;
    public String keys_url;
    public String collaborators_url;
    public String teams_url;
    public String hooks_url;
    public String issue_events_url;
    public String events_url;
    public String assignees_url;
    public String branches_url;
    public String tags_url;
    public String blobs_url;
    public String git_tags_url;
    public String git_refs_url;
    public String trees_url;
    public String statuses_url;
    public String languages_url;
    public String stargazers_url;
    public String contributors_url;
    public String subscribers_url;
    public String subscription_url;
    public String commits_url;
    public String git_commits_url;
    public String comments_url;
    public String issue_comment_url;
    public String contents_url;
    public String compare_url;
    public String merges_url;
    public String archive_url;
    public String downloads_url;
    public String issues_url;
    public String pulls_url;
    public String milestones_url;
    public String notifications_url;
    public String labels_url;
    public String releases_url;
    public String deployments_url;
    public String created_at;
    public String updated_at;
    public String pushed_at;
    public String git_url;
    public String ssh_url;
    public String clone_url;
    public String svn_url;
    public String homepage;
    public String size;
    public String stargazers_count;
    public String watchers_count;
    public String language;
    public String has_issues;
    public String has_projects;
    public String has_downloads;
    public String has_wiki;
    public String has_pages;
    public String forks_count;
    public String mirror_url;
    public String archived;
    public String disabled;
    public String open_issues_count;
    public String license;
    public String allow_forking;
    public String is_template;
    public String web_commit_signoff_required;
    
    public Topics topics;
    public class Topics{
    	
    }
    public String visibility;
    public String forks;
    public String open_issues;
    public String watchers;
    public String default_branch;
}
