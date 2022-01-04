package service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import vo.NmovieVO;

@Service
public class NmovieService {
	private static String clientID = "Y95vtmMJWmmcnYykJRNb";
	private static String clientSecret = "ZvnTUnvDYW";
	URL url;
	URLConnection urlConn;
	XmlPullParserFactory factory;
	XmlPullParser parser;
	
	public void con(String keyword, int yearfrom, int yearto, int display, int start) {
		try {
			url = new URL("https://openapi.naver.com/v1/search/movie.xml?query=" + URLEncoder.encode(keyword, "UTF-8")
					+ (yearfrom != 0 ? "&yearfrom=" + yearfrom : "") + (yearto != 0 ? "&yearto=" + yearto : "")
					+ (display != 0 ? "&display=" + display : "") + (start != 0 ? "&start=" + start : ""));
			urlConn = url.openConnection();
			urlConn.setRequestProperty("X-Naver-Client-Id", clientID);
			urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(urlConn.getInputStream()));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<NmovieVO> searchMovie(String keyword, int display, int start) {
		List<NmovieVO> list = null;
		con(keyword, 0, 0, display, start);
		int eventType;
		try {
			eventType = parser.getEventType();

			NmovieVO nmovie = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<NmovieVO>();
					break;
				case XmlPullParser.END_TAG: {
					String tag = parser.getName();
					if (tag.equals("item")) {
						list.add(nmovie);
						nmovie = null;
					}
				}
				case XmlPullParser.START_TAG: {
					String tag = parser.getName();
					if ("item".equals(tag)) {
						nmovie = new NmovieVO();
						break;
					} else if ("title".equals(tag)) {
						if (nmovie != null)
							nmovie.setTitle(parser.nextText());
						break;
					} else if ("link".equals(tag)) {
						if (nmovie != null)
							nmovie.setLink(parser.nextText());
						break;
					} else if ("image".equals(tag)) {
						if (nmovie != null) {
							nmovie.setImage(parser.nextText());
						}
						break;
					} else if ("subtitle".equals(tag)) {
						if (nmovie != null)
							nmovie.setSubtitle(parser.nextText());
						break;
					} else if ("pubDate".equals(tag)) {
						if (nmovie != null)
							nmovie.setPubDate(parser.nextText());
						break;
					} else if ("director".equals(tag)) {
						if (nmovie != null)
							nmovie.setDirector(parser.nextText());
						break;
					} else if ("actor".equals(tag)) {
						if (nmovie != null)
							nmovie.setActor(parser.nextText());
						break;
					} else if ("userRating".equals(tag)) {
						if (nmovie != null) {
							nmovie.setUseRating(parser.nextText());
						}
						break;
					}
				}
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public NmovieVO getMovieDetail(String keyword, int year) {
		keyword = keyword.replaceAll("&amp;", "&");
		// System.out.println(keyword + "(" + year + ")");
		con(keyword, year, year, 1, 1);
		int eventType;
		try {
			eventType = parser.getEventType();
			NmovieVO nmovie = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_TAG: {
					String tag = parser.getName();
					if (tag.equals("item")) {
						return nmovie;
					}
				}
				case XmlPullParser.START_TAG: {
					String tag = parser.getName();
					if ("item".equals(tag)) {
						nmovie = new NmovieVO();
						break;
					} else if ("title".equals(tag)) {
						if (nmovie != null)
							nmovie.setTitle(parser.nextText());
						break;
					} else if ("link".equals(tag)) {
						if (nmovie != null)
							nmovie.setLink(parser.nextText());
						break;
					} else if ("image".equals(tag)) {
						if (nmovie != null) {
							nmovie.setImage(parser.nextText());
						}
						break;
					} else if ("subtitle".equals(tag)) {
						if (nmovie != null)
							nmovie.setSubtitle(parser.nextText());
						break;
					} else if ("pubDate".equals(tag)) {
						if (nmovie != null)
							nmovie.setPubDate(parser.nextText());
						break;
					} else if ("director".equals(tag)) {
						if (nmovie != null)
							nmovie.setDirector(parser.nextText());
						break;
					} else if ("actor".equals(tag)) {
						if (nmovie != null)
							nmovie.setActor(parser.nextText());
						break;
					} else if ("userRating".equals(tag)) {
						if (nmovie != null) {
							nmovie.setUseRating(parser.nextText());
						}
						break;
					}
				}
				}
				eventType = parser.next();
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
