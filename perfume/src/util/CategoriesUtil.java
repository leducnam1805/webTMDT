package util;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;

import daos.CatPerfumeDao;
import models.CatPerfume;

public class CategoriesUtil {
	
	public static void showBrand(int parent,HttpServletRequest request, JspWriter out)
		throws IOException{
		CatPerfumeDao objCatPFDao = new CatPerfumeDao();
		List<CatPerfume> CatPFList = objCatPFDao.getCatParent(parent);
		if(CatPFList.size() > 0) {
			out.println("<ul>");
			for(CatPerfume objCatPerfume: CatPFList) {
				String url = request.getContextPath()+"/cat?id="+objCatPerfume.getId();
				out.println("<li><a href=\""+url+"\">"+ objCatPerfume.getCatPerfume() +"</a>");
				showBrand(objCatPerfume.getId(), request, out);
			}
			out.println("</ul>");
		} else {
			out.println("</li>");
		}
	}
}
