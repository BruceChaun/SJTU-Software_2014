package data_center; 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CookieStore;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieSpecProvider;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.message.BasicNameValuePair;

@SuppressWarnings("deprecation")
public class OfficialUploadFiller
{
	
	@SuppressWarnings({ "resource", "unused" })
	public static void login() throws Exception
	{
		//��ʼ��
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    // ��һ�б���Ҫ�ӣ�����������޷���ȡ��½״̬
	    HttpClientParams.setCookiePolicy(httpclient.getParams(),CookiePolicy.BROWSER_COMPATIBILITY);
	
	    // ��һ�η���
	    String url = "http://www.baidu.com";
	    HttpGet httpget = new HttpGet(url);
	    HttpResponse response;// = httpclient.execute(httpget);
	    //System.out.println("Length1::" + response.getEntity().getContentLength());
	    HttpEntity entity;// = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	    
	    // ��½��ʹ��POST��ʽ��¼��
	    // ���Ҫֱ��ִ�У��鷳ȥ������ٶȵ��ʺ�
	    // ������˼�����ٶ��������
	    HttpPost httpost = new HttpPost("http://passport.baidu.com/?login");
	    List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("username", "����������"));
	    nvps.add(new BasicNameValuePair("password", "wtgwst0901"));
	    httpost.setEntity(new UrlEncodedFormEntity(nvps, "gb2312"));
	    response = httpclient.execute(httpost);
	
	    // �ڶ��η���
	    System.out.println("\n----------------------------------------");
	    System.out.println(response.getStatusLine());
	    List<Cookie> cookies = httpclient.getCookieStore().getCookies();
	    entity = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	
	    System.out.println("\n----------------------------------------");
	
	    cookies = httpclient.getCookieStore().getCookies();
	    
	    System.out.println("cookies" + cookies.size());
	    httpget = new HttpGet(url);
	    // httpget.setr
	    // httpget.setHeader(name, value)
	    //response = httpclient.execute(httpget);
	    System.out.println("Length2::"
	            + response.getEntity().getContentLength());
	    entity = response.getEntity();
	    //BaiduLogin.printEntity(entity);
	}
	
	
	
	
	public static void httpGet() throws Exception
	{	
		
		
		Registry<CookieSpecProvider> r = RegistryBuilder.<CookieSpecProvider>create()
				.register(CookieSpecs.BEST_MATCH,
				new BestMatchSpecFactory())
				.register(CookieSpecs.BROWSER_COMPATIBILITY,
				new BrowserCompatSpecFactory())
				.build();
		
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	    nvps.add(new BasicNameValuePair("username", "DustInMonteriggioni"));
	    nvps.add(new BasicNameValuePair("password", "********"));
	    
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(AuthScope.ANY, 
				new UsernamePasswordCredentials("DustInMonteriggioni", "*********"));
		
		HttpClientContext context = HttpClientContext.create();
		CookieStore cookieStore = new BasicCookieStore();
		context.setCredentialsProvider(credsProvider);
		context.setCookieStore(cookieStore);
		
		CloseableHttpClient httpclient = 
				HttpClients.custom().setDefaultCookieSpecRegistry(r)
				.setDefaultCookieStore(cookieStore).build();
		
		//HttpGet httpget = new HttpGet("https://igem.org/Login2");
		HttpPost httpost = new HttpPost("http://igem.org/Login2");
		
		httpost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		
		CloseableHttpResponse response = httpclient.execute(httpost, context);
		System.out.println(response.getStatusLine());
		
		
		System.out.println("\n\n\n********\n");
		Header[] headers = response.getAllHeaders();
		for (Header header : headers)
			System.out.println(header);
		
		
		HttpEntity entity = response.getEntity();
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"));
		String line;
		while ( (line = reader.readLine()) != null )
			System.out.println(line);
		reader.close();
		
		List<Cookie> cookies = context.getCookieStore().getCookies();
		
		System.out.println("\n\n\n" + cookies.size());
		
		for (Cookie outCookie : cookies)
			System.out.println(outCookie);
		
		response.close();
	}
	
	
	/**
     * ��ָ��URL����GET����������
     * @param url ���������URL
     * @param param ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return URL ������Զ����Դ����Ӧ���
     */
    public static String sendGet()//String url, String param)
    {
        String result = "";
        BufferedReader in = null;
        try
        {	//String urlNameString = url + "?" + param;
        	String urlNameString = "http://www.baidu.com/";
            URL realUrl = new URL(urlNameString);
            // �򿪺�URL֮�������
            URLConnection connection = realUrl.openConnection();
            // ����ͨ�õ���������
            connection.setRequestProperty("accept", "*/*");
            //connection.setRequestProperty("connection", "Keep-Alive");
            //connection.setRequestProperty("user-agent",
                    //"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����ʵ�ʵ�����
            connection.connect();
            // ��ȡ������Ӧͷ�ֶ�
            Map<String, List<String>> map = connection.getHeaderFields();
            // �������е���Ӧͷ�ֶ�
            for (String key : map.keySet())
                System.out.println(key + "--->" + map.get(key));
            
            // ���� BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line;
            while ((line = in.readLine()) != null)
                result += line + "\n";
            
        } catch (Exception e) {
            System.out.println("����GET��������쳣��" + e);
            e.printStackTrace();
        }
        // ʹ��finally�����ر�������
        finally
        {	try {
                if (in != null) 
                    in.close();
            } catch (Exception e2) {e2.printStackTrace();}
        }
        return result;
    }

    /**
     * ��ָ�� URL ����POST����������
     * 
     * @param url
     *            ��������� URL
     * @param param
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
     * @return ������Զ����Դ����Ӧ���
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // �򿪺�URL֮�������
            URLConnection conn = realUrl.openConnection();
            // ����ͨ�õ���������
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            //conn.setRequestProperty("user-agent",
                    //"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // ����POST�������������������
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // ��ȡURLConnection�����Ӧ�������
            out = new PrintWriter(conn.getOutputStream());
            // �����������
            out.print(param);
            // flush������Ļ���
            out.flush();
            // ����BufferedReader����������ȡURL����Ӧ
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("���� POST ��������쳣��"+e);
            e.printStackTrace();
        }
        //ʹ��finally�����ر��������������
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    
  
}
