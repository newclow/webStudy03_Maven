package kr.or.ddit.tags;

import java.io.IOException;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MakeTimeZoneSelectTag extends SimpleTagSupport {
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
//		StringBuffer selectTag = new StringBuffer();
//		String pattern = "<option value='%s'>%s</option>\n";
//		selectTag.append("<select name='"+name+"' >\n");
//		selectTag.append(String.format(pattern, "", "타임존선택"));
//		String[] timeZone = TimeZone.getAvailableIDs();
//		for (String str : timeZone) {
//			selectTag.append(String.format(pattern, str, str.))
//		}
//		-
//		for (Locale locale : locales) {
//			if (locale.getDisplayName() != null && locale.getDisplayName().trim().length() > 0) {
//				selectTag.append(String.format(pattern, locale.toLanguageTag(), locale.getDisplayName()));
//			}
//		}
//		selectTag.append("</select>\n");
//		getJspContext().getOut().println(selectTag);
	}
}
