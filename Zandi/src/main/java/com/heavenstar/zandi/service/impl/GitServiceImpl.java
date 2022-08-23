package com.heavenstar.zandi.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenstar.zandi.model.GitCommitVO;
import com.heavenstar.zandi.model.ReadmeVO;
import com.heavenstar.zandi.model.RepoListVO;
import com.heavenstar.zandi.service.GitService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class GitServiceImpl implements GitService{
	

	@Override
	public int CommitOk(String id, String repo) throws IOException, ParseException {
		//가장 최근 커밋
		
		
		String url = " https://api.github.com/repos/" + id + "/" + repo + "/commits";
		
		//파일 읽어들이기
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        String retString = "";
        String line;
        while((line = rd.readLine()) != null) {
        	retString += line;
        }
        rd.close();
        conn.disconnect();
        
        
        //문자열을  JSON 객체로 변환
        JSONParser parse = new JSONParser();
        JSONArray arr = (JSONArray)parse.parse(retString);
        
        JSONObject obj = (JSONObject)arr.get(0);
       
        JSONObject commit = (JSONObject)obj.get("commit");
        
        String json = commit.toString();
        
        ObjectMapper mapper = new ObjectMapper();
        GitCommitVO gitVO = mapper.readValue(json, GitCommitVO.class);
        
        String transDate = dataTransate(gitVO.committer.getDate());
        gitVO.committer.setDate(transDate);
        
        int result = todayOk(gitVO.getCommitter().getDate());

		return result;
	
	}


	@Override
	public List<GitCommitVO> allCommit(String id, String repo) throws IOException, ParseException {

		String url = " https://api.github.com/repos/" + id + "/" + repo + "/commits";
		
		//파일 읽어들이기
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        String retString = "";
        String line;
        while((line = rd.readLine()) != null) {
        	retString += line;
        }
        rd.close();
        conn.disconnect();
        
        
        //문자열을  JSON 객체로 변환
        JSONParser parse = new JSONParser();
        JSONArray arr = (JSONArray)parse.parse(retString);
        List<GitCommitVO> gitList = new ArrayList<>();
        
        for(int i =0; i < arr.size(); i++) {
        	
	        JSONObject obj = (JSONObject)arr.get(i);
	       
	        JSONObject commit = (JSONObject)obj.get("commit");
	        
	        String json = commit.toString();
	        
	        ObjectMapper mapper = new ObjectMapper();
	        GitCommitVO gitVO = mapper.readValue(json, GitCommitVO.class);
	        
	        String transDate = dataTransate(gitVO.committer.getDate());
	        gitVO.committer.setDate(transDate);
	        
	        gitList.add(gitVO);
        }
        
		return gitList;
	}
	
	@Override
	public ReadmeVO getReadme(String id, String repo) throws IOException, ParseException {
		
		
		String url = " https://api.github.com/repos/" + id + "/" + repo + "/readme";
		
		//파일 읽어들이기
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        String retString = "";
        String line;
        while((line = rd.readLine()) != null) {
        	retString += line;
        }
        rd.close();
        conn.disconnect();
        
        
        //문자열을  JSON 객체로 변환
        JSONParser parse = new JSONParser();
        JSONObject obj = (JSONObject)parse.parse(retString);
                
        String json = obj.toString();
        
        ObjectMapper mapper = new ObjectMapper();
        ReadmeVO readmeVO = mapper.readValue(json, ReadmeVO.class);
        
        String content = readmeVO.getContent();
        readmeVO.setContent(readmeTransate(content));

		return readmeVO;
	}
	
	
	@Override
	public String dataTransate(String date) {
		
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TimeZone utcZone = TimeZone.getTimeZone("UTC");
		sf.setTimeZone(utcZone);
		Date strDate = null;
		String dateTime = "";
		try {
			strDate = sf.parse(date);
			dateTime = sdf.format(strDate);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dateTime;
		
	}


	@Override
	public String readmeTransate(String content) throws UnsupportedEncodingException {
		
		String target = content;
		  byte[] targetBytes = target.getBytes("UTF-8");
		
		byte[] decoded = Base64.getDecoder().decode(targetBytes);
		String read = new String(decoded, StandardCharsets.UTF_8);
		
		return read;
	}


	@Override
	public int todayOk(String date) {
		
		String[] result = date.split(" ");
		
		Date curDate = new Date(System.currentTimeMillis());
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(curDate);
		
		if(result[0].equals(today)) {
			return 1;
		}
		
		return 0;
	}


	@Override
	public List<RepoListVO> getRepoList(String username) throws IOException, ParseException{
		
		String url = " https://api.github.com/users/" + username+ "/repos";
		
		//파일 읽어들이기
        URL realUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        String retString = "";
        String line;
        while((line = rd.readLine()) != null) {
        	retString += line;
        }
        rd.close();
        conn.disconnect();
        
        
        //문자열을  JSON 객체로 변환
        JSONParser parse = new JSONParser();
        JSONArray arr = (JSONArray)parse.parse(retString);
                
        
        List<RepoListVO> repoList = new ArrayList<>();
        
        for(int i =0; i < arr.size(); i++) {
        	
	        JSONObject obj = (JSONObject)arr.get(i);
	       
	        String json = obj.toString();
	        
	        ObjectMapper mapper = new ObjectMapper();
	        RepoListVO repoVO = mapper.readValue(json, RepoListVO.class);
	        	        
	        repoList.add(repoVO);
        }
        
		return repoList;
	}



	
	
	
	

}
