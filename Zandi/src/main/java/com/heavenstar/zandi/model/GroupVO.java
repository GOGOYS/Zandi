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
public class GroupVO {
	
	public long g_seq;
	public String g_name;
	public int g_people;
	public String g_enddate;
	
	private String j_seq;
	private String j_gname;
	private String j_username;
}
