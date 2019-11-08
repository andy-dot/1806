package cn.tedu.fallback;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
@Component  //包扫描，controller/service
public class ZuulFallback implements ZuulFallbackProvider{

	@Override //获取路由，application.name
	public String getRoute() {
		return "provider-user";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		// TODO Auto-generated method stub
		return new ClientHttpResponse() {
			
			@Override //请求响应头信息，contentType和字符类型
			public HttpHeaders getHeaders() {
				//返回类型为json,字符集为u8
				HttpHeaders header = new HttpHeaders();
				header.setContentType(MediaType.APPLICATION_JSON_UTF8);
				return header;
			}
			
			@Override //响应体，具体返回内容
			public InputStream getBody() throws IOException {
				String returnValue = "tony8";	//返回默认值
				return new ByteArrayInputStream(returnValue.getBytes());
			}
			
			@Override //返回文字描述
			public String getStatusText() throws IOException {
				return HttpStatus.BAD_REQUEST.getReasonPhrase();
			}
			
			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.BAD_REQUEST;
			}
			
			@Override
			public int getRawStatusCode() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	
	
	
}
