package kr.or.ddit.filter.wrapper;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

public class FileUploadRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String[]> parameterMap;
	private Map<String, List<FileItem>> fileItemMap;
	
	public FileUploadRequestWrapper(HttpServletRequest request) throws IOException {
		this(request, -1, null);
	}
	
	public FileUploadRequestWrapper(HttpServletRequest request, int sizeThreadshold, File repository) throws IOException {
		super(request);
		parameterMap = new LinkedHashMap<>();
		parameterMap.putAll(request.getParameterMap());
		fileItemMap = new LinkedHashMap<>();
		parseRequest(request, sizeThreadshold, repository);
		
	}
	
	
	private void parseRequest(HttpServletRequest req, int sizeThreadshold, File repository) throws IOException {
//		문자 Part는 parameterMap에
//		파일 Part는 fileItemMap에 누적
		//1. commons-fileupload 라이브러리추가
				//2. 파일업로드 핸들러 객체 생성
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				if (sizeThreadshold != -1) {
					fileItemFactory.setSizeThreshold(sizeThreadshold);
				}
				if (repository != null) {
					fileItemFactory.setRepository(repository);
				}
				
				ServletFileUpload handler = new ServletFileUpload(fileItemFactory);
				//3. 핸들러 객체를 이용해 현재 요청 파싱(Part -> FileItem)
				req.setCharacterEncoding("UTF-8");
				try {
					List<FileItem> fileItems = handler.parseRequest(req);
					//4. FileItem들을 대상으로 반복 실행
					if (fileItems != null) {
						for (FileItem item : fileItems) {
							String partname = item.getFieldName();
							if (item.isFormField()) {
								//5. 일반 문자열 기반의 FileItem에 대한 처리와 
								String parameterValue = null;
								if (req.getCharacterEncoding() == null) {
									parameterValue = item.getString(req.getCharacterEncoding());
								} else {
									parameterValue = item.getString();
								}
								String[] alreadyValues = parameterMap.get(partname);
								String[] values = null;
								if (alreadyValues == null) {
									values = new String[] {parameterValue};
								}else {
									values = new String[alreadyValues.length +1];
									System.arraycopy(alreadyValues, 0, values, 0, alreadyValues.length);
								}
								values[values.length-1] = parameterValue;
								parameterMap.put(partname, values);
								
							}else {
								//파일을 선택하지 않은 비어있는 part를 skip
								if (StringUtils.isBlank(item.getName())) {
									continue;
								}
								//파일기반의 FileItem에 대한 처리를 수행
								List<FileItem> alreadyItems = fileItemMap.get(partname);
								if (alreadyItems == null) {
									alreadyItems = new ArrayList<>();
								}
								alreadyItems.add(item);
								fileItemMap.put(partname, alreadyItems);				
							}
						}
					}
				} catch (FileUploadException e) {
					throw new IOException(e);
				}
		
	}


	@Override
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	@Override
	public String getParameter(String name) {
		String[] values = parameterMap.get(name);
		if (values == null) {
			return null;
		} else {
			return values[0];
		}
	}
	
	@Override
	public String[] getParameterValues(String name) {
		return parameterMap.get(name);
	}
	
	@Override
	public Enumeration<String> getParameterNames() {
		final Iterator<String> it = parameterMap.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
			}

			@Override
			public String nextElement() {
				return it.next();
			}
		};
	}
	
	public FileItem getFileItem(String partname){
		List<FileItem> itemList = fileItemMap.get(partname);
		if (itemList != null && itemList.size() > 0) {
			return itemList.get(0);
		} else {
			return null;
		}
	}
	
	public List<FileItem> getFileItems(String partname){
		return fileItemMap.get(partname);
	}
	
	public Map<String, List<FileItem>> getFileItemMap() {
		return fileItemMap;
	}
	
	public Enumeration<String> getFileItemNames(){
		final Iterator<String> it = fileItemMap.keySet().iterator();
		return new Enumeration<String>() {

			@Override
			public boolean hasMoreElements() {
				return it.hasNext();
			}

			@Override
			public String nextElement() {
				return it.next();
			}
		};
	}
	
	public void deleteAllTempFile(){ //임시 저장폴더를 관리하며 업로드가 끝난후에는 삭제한다.
		for ( Entry<String, List<FileItem>> entry : fileItemMap.entrySet()) {
			for ( FileItem tmp : entry.getValue()) {
				tmp.delete();
			}
		}
	}
	
	
}
