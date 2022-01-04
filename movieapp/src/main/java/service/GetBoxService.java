package service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import vo.GetBoxVO;

@Service
public class GetBoxService {
	URL url;
	URLConnection urlConn;
	XmlPullParserFactory factory;
	XmlPullParser parser;
	
	public List<GetBoxVO> getBoxOffice() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyymmdd");
//		String today = dformat.format(new Date());
		String today = "20211216";
		System.out.println(today);
		List<GetBoxVO> list = new ArrayList<>();
		try {
			url = new URL(
					"http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=34086a8581fb3ac660101820bfe9fbb4&targetDt="
							+ today);
			urlConn = url.openConnection();
			factory = XmlPullParserFactory.newInstance();
			parser = factory.newPullParser();
			parser.setInput(new InputStreamReader(urlConn.getInputStream()));
			int eventType;
			eventType = parser.getEventType();
			GetBoxVO kmovie = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.END_DOCUMENT:
					break;
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.END_TAG: {
					String tag = parser.getName();
					if (tag.equals("dailyBoxOffice")) {
						list.add(kmovie);
						kmovie = null;
					}
				}
				case XmlPullParser.START_TAG: {
					String tag = parser.getName();
					if ("dailyBoxOffice".equals(tag)) {
						kmovie = new GetBoxVO();
						break;
					} else if ("rnum".equals(tag)) {
						if (kmovie != null)
							kmovie.setRnum(parser.nextText());
						break;
					} else if ("rank".equals(tag)) {
						if (kmovie != null)
							kmovie.setRank(parser.nextText());
						break;
					} else if ("rankInten".equals(tag)) {
						if (kmovie != null) {
							kmovie.setRankInten(parser.nextText());
						}
						break;
					} else if ("rankOldAndNew".equals(tag)) {
						if (kmovie != null)
							kmovie.setRankOldAndNew(parser.nextText());
						break;
					} else if ("movieCd".equals(tag)) {
						if (kmovie != null)
							kmovie.setMovieCd(parser.nextText());
						break;
					} else if ("movieNm".equals(tag)) {
						if (kmovie != null)
							kmovie.setMovieNm(parser.nextText());
						break;
					} else if ("openDt".equals(tag)) {
						if (kmovie != null)
							kmovie.setOpenDt(parser.nextText());
						break;
					} else if ("salesAmt".equals(tag)) {
						if (kmovie != null) {
							kmovie.setSalesAmt(parser.nextText());
						}
						break;
					} else if ("salesShare".equals(tag)) {
						if (kmovie != null) {
							kmovie.setSalesShare(parser.nextText());
						}
						break;
					} else if ("salesInten".equals(tag)) {
						if (kmovie != null) {
							kmovie.setSalesInten(parser.nextText());
						}
						break;
					} else if ("salesChange".equals(tag)) {
						if (kmovie != null) {
							kmovie.setSalesChange(parser.nextText());
						}
						break;
					} else if ("audiCnt".equals(tag)) {
						if (kmovie != null) {
							kmovie.setAudiCnt(parser.nextText());
						}
						break;
					} else if ("audiInten".equals(tag)) {
						if (kmovie != null) {
							kmovie.setAudiInten(parser.nextText());
						}
						break;
					} else if ("audiChange".equals(tag)) {
						if (kmovie != null) {
							kmovie.setAudiChange(parser.nextText());
						}
						break;
					} else if ("audiAcc".equals(tag)) {
						if (kmovie != null) {
							kmovie.setAudiAcc(parser.nextText());
						}
						break;
					} else if ("scrnCnt".equals(tag)) {
						if (kmovie != null) {
							kmovie.setScrnCnt(parser.nextText());
						}
						break;
					} else if ("showCnt".equals(tag)) {
						if (kmovie != null) {
							kmovie.setShowCnt(parser.nextText());
						}
						break;
					}

				}
				}
				eventType = parser.next();
			}
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

		return list;
	}
}
