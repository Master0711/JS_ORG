package com.jsorg.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;

public class ReptileUtil {
	public Map<String, String> getRecruitmentDetailed(String httpurlString) {
		Console.log(httpurlString);
		String listContent = HttpUtil.get(httpurlString);
		List<String> titles = ReUtil.findAll("<div class=\"content1 clearfix\">(.*?)<p> <b><font size=3 color=\"#FF0000\">主办", listContent, 1);
		List<String> title1 = ReUtil.findAll("<h1>(.*?)</h1>", (CharSequence) titles.get(0), 1);
		String time = ReUtil.findAll("<p> <b><font size=3 color=\"#FF0000\">举办时间：(.*?)</font></b></p>", (CharSequence) titles.get(0), 1).get(0);
		String location = ReUtil.findAll("<p><b><font size=3 color=\"#FF0000\">举办地点：(.*?)</font></b></p>", (CharSequence) titles.get(0), 1).get(0);
		String content = ReUtil.findAll("<div class=\"news-entry\">(.*?)详情请点击", (CharSequence) titles.get(0), 1).get(0);
		String url = ReUtil.findAll("<a href=\"(.*?)\"><font color", (CharSequence) titles.get(0), 1).get(0);
		Pattern p = Pattern.compile("\t|\r|\n");
		Pattern pl = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = pl.matcher(title1.get(0));
        String title = m.replaceAll("");
        m = p.matcher(time);
        time = m.replaceAll("");
        m = pl.matcher(location);
        location = m.replaceAll("");
        m = pl.matcher(content);
        content = m.replaceAll("");
        m = pl.matcher(url);
        url = m.replaceAll("");
        Map<String, String> map = new HashMap<String, String>();
        map.put("title", title);
        map.put("time", time);
        map.put("location", location);
        map.put("content", content);
        map.put("url", url);
		Console.log(title);
		Console.log(time);
		Console.log(location);
		Console.log(content);
		Console.log(url);
		return map;
	}
	public List<String> getRecruitmentList() {
		String url = "http://www.fjrclh.com/";
		String listContent = HttpUtil.get(url);
		String allcontent = ReUtil.findAll("<div class=\"undis\" id=\"cgpsub1\">(.*?)</div>", listContent, 1).get(0);
		List<String> list = ReUtil.findAll("<td width=\"300\">·<a href=\"(.*?)\" target=\"_blank\">", allcontent, 1);
		List<String> urList = new ArrayList<String>();
		for (String string : list) {
			string = string.replace("meeting", "");
			string = "www.fjrclh.com"+string;
			urList.add(string);
		}
		
		return urList;
	}
}
