package kr.or.ddit.web;

public class test {
	public static void main(String[] args) {
		String mime = "text/plain;charset=UTF-8";
		
		System.out.println(mime.indexOf("/"));
		System.out.println(mime.indexOf(";"));
		System.out.println(mime.substring(mime.indexOf("/")+1, mime.indexOf(";")));
	}
}
