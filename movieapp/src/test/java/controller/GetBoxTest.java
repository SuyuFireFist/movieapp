package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import service.GetBoxService;

public class GetBoxTest {

	@Test
	public void testGetBox() {
		GetBoxService glist = new GetBoxService();
		System.out.println(glist.getBoxOffice());
	}
	
	@Test
	public void testDate() {
		SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dformat.format(new Date());
		System.out.println(today);
	}
}
