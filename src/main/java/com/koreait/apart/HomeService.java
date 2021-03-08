package com.koreait.apart;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koreait.apart.model.ApartmentInfoDTO;
import com.koreait.apart.model.ApartmentInfoDomain;
import com.koreait.apart.model.ApartmentInfoEntity;
import com.koreait.apart.model.LocationCodeEntity;
import com.koreait.apart.model.ResponseDomain;

@Service
public class HomeService {
	
	@Autowired
	private HomeMapper mapper;

	public List<LocationCodeEntity> locationSearch() {
		return mapper.locationSearch();
	}
	
	public List<ApartmentInfoDomain> getData(ApartmentInfoEntity p) {
		// 세팅 부분
		
		Map<String, Integer> map = new HashMap();
		map.put("27110", 1);
		map.put("27140", 2);
		map.put("27170", 3);
		map.put("27200", 4);
		map.put("27230", 5);
		map.put("27260", 6);
		map.put("27290", 7);
		map.put("27710", 8);
		
		
		final String url = "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev";
		String decodeServiceKey = "Y2UOCkD8Ilv2gViPGV33ddNTTQfRi92i8mRzUeQX+NgSiNTO3gp9hJZX4J6u8uXucMM6RdRBoGxMn6XHfsEzNA=="; // serviceKey 디코딩
		
		final String lawd_cd = p.getRegional_code();
		
		String mon = "0" + p.getDeal_month();
		mon = mon.substring(mon.length() -2); // month를 두자리로 만들어 주기 위한 구문
		final String deal_ym = p.getDeal_year() + mon;
		
		final int location_cd = map.get(lawd_cd);
		p.setLocation_cd(location_cd);
		
		// DB에 저장된 자료가 있는지 확인 
		// (자료가 있으면 있는 자료 return , 없으면 openApi 통신을 하여 자료를 가져온 뒤 DB에 저장하고 자료를 return)
		List<ApartmentInfoDomain> dbData = mapper.selApart(p);
		if(dbData.size() > 0) {
			return dbData;
		}
		
		
		final HttpHeaders headers = new HttpHeaders(); // header에 요청 했을 때 header에 박을 내용
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML)); 
		final HttpEntity entity = new HttpEntity(headers);
		UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
								.queryParam("LAWD_CD", lawd_cd) // 쿼리 스트링 날리는 것과 같음
								.queryParam("DEAL_YMD", deal_ym) 
								.queryParam("serviceKey", decodeServiceKey)
								.build(false); // build는 완성시킨다고 보면 됨 , 완료가 되면 builder가 됨
		
		
		// 통신 부분
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		
		ResponseEntity respEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
		
		String result = (String)respEntity.getBody();
		
		// XmlMapper를 할 때 () 안에 있는 설정을 줌
		ObjectMapper om = new XmlMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	
		ResponseDomain domain = null;
		try {
			domain = om.readValue(result, ResponseDomain.class);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		List<ApartmentInfoEntity> list = domain.getBody().getItems();
		if(list != null && list.size() > 0) {
			mapper.insApart(new ApartmentInfoDTO(location_cd, list));
		} 
		
		return mapper.selApart(p);
	}
}
