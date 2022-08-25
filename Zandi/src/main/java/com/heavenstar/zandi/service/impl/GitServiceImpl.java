package com.heavenstar.zandi.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.heavenstar.zandi.config.Config;
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
		
		String token = Config.TOKEN;
		String url = "https://api.github.com/repos/" + id + "/" + repo + "/commits";
		
		URI restURI = null;
		try {
			restURI = new URI(url);
		} catch (URISyntaxException e) {
			// 연결이 되지 않음
			return 0;
		}

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "token "+ token);
		
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);
		
		
		RestTemplate restTemp = new RestTemplate();
		
		

			ResponseEntity<JSONArray> resData = null;
			
			resData = restTemp.exchange(
						restURI, 
						HttpMethod.GET, 
						entity,
						new ParameterizedTypeReference<JSONArray>(){}
						);
		
		String json = resData.getBody().get(0).toString();
		
		ObjectMapper mapper = new ObjectMapper();
		String respData = mapper.writeValueAsString(resData.getBody().get(0));
	    GitCommitVO gitVO = mapper.readValue(respData, GitCommitVO.class);
		
        // 날짜 변환
        String transDate = dataTransate(gitVO.commit.author.getDate());
        gitVO.commit.author.setDate(transDate);
                
        int result = todayOk(transDate);

		return result;
	
	}


	@Override
	public List<GitCommitVO> allCommit(String id, String repo) throws IOException, ParseException {

		String token = Config.TOKEN;
		String url = "https://api.github.com/repos/" + id + "/" + repo + "/commits";
		
		URI restURI = null;
		try {
			restURI = new URI(url);
		} catch (URISyntaxException e) {
			return null;
		}

		//Http 프로토콜에 보안정보를 세팅하여
		//Naver로 전송할 준비
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "token "+ token);
		
		//XML 데이터 type으로 받겠다.
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		// headers에 추가된 정보를 Entity type의 객체로 변환하기
		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);
		
		/*
		 * 
		 * NaverParent는 List<VO> 타입의 items 변수를 가지는데
		 * 여기에서 VO type을 BookVO로 확정지어 준다.
		 */
		
		RestTemplate restTemp = new RestTemplate();
		
		
		//VO가 아닌 String형으로 수신할것이다.
		//문자열 타입으로 그대로 수신한다
			ResponseEntity<JSONArray> resData = null;
			
			resData = restTemp.exchange(
						restURI, 
						HttpMethod.GET, 
						entity,
						new ParameterizedTypeReference<JSONArray>(){}
						);
			
			List<GitCommitVO> gitList = new ArrayList<>();

        for(int i =0; i < resData.getBody().size(); i++) {
        	
        	ObjectMapper mapper = new ObjectMapper();
        	String respData = mapper.writeValueAsString(resData.getBody().get(i));
        	GitCommitVO gitVO = mapper.readValue(respData, GitCommitVO.class);
	        String transDate = dataTransate(gitVO.commit.author.getDate());
	        gitVO.commit.author.setDate(transDate);
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
	public List<RepoListVO> getRepoList(String u_github_id) throws IOException, ParseException{
		
		String token = Config.TOKEN;
		String url = "https://api.github.com/users/" +u_github_id + "/repos?per_page=100";
		URI restURI = null;
		try {
			restURI = new URI(url);
		} catch (URISyntaxException e) {
			return null;
		}

		//Http 프로토콜에 보안정보를 세팅하여
		//Naver로 전송할 준비
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "token "+ token);
		
		//XML 데이터 type으로 받겠다.
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		// headers에 추가된 정보를 Entity type의 객체로 변환하기
		HttpEntity<String> entity = new HttpEntity<String>("parameter", headers);
		
		/*
		 * 
		 * NaverParent는 List<VO> 타입의 items 변수를 가지는데
		 * 여기에서 VO type을 BookVO로 확정지어 준다.
		 */
		
		RestTemplate restTemp = new RestTemplate();
		
		
		//VO가 아닌 String형으로 수신할것이다.
		//문자열 타입으로 그대로 수신한다
			ResponseEntity<List<RepoListVO>> resData = null;
			
			resData = restTemp.exchange(
						restURI, 
						HttpMethod.GET, 
						entity,
						new ParameterizedTypeReference<List<RepoListVO>>(){}
						);
			
			
			List<RepoListVO> repoList = resData.getBody();
			
			for(int i =0; i< repoList.size(); i++) {
				
				if(repoList.get(i).name == null) {
					repoList.remove(i);
				}
			}
			return repoList;
		
	}
	
	
	
	
	

}
