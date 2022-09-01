package com.heavenstar.zandi.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
	public int g_inpeople;
	public String g_create_date;
	public String g_end_date;
	
	//form에서 지정한 end_date 받아오는것
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date end_date;
	
	private long j_seq;
	private String j_gname;
	private String j_username;
	private String j_userrepo;
	private int j_percent;

}
