package kr.or.ddit.web.calculate;

public enum Operator {

	//RealOperator interface를 @FunctionalInterface 지정후
	// 객체를 람다식으로 생성한뒤 operate 메소드 오버라이드 
	ADD("+",(realnum1, realnum2) -> {
		return realnum1 + realnum2;
	}),
	
	MINUS("-",(realnum1, realnum2) -> {
		return realnum1 - realnum2;
	}), 
	
	MULTIPLY("*",(realnum1, realnum2) -> {
		return realnum1 * realnum2;
	}), 
	
	DIVIDE("/",(realnum1, realnum2) -> {
		return realnum1 / realnum2;
	});
	
	private String sign;
	private RealOperator realOperator;
	Operator(String sign, RealOperator realOperator) {
		this.sign = sign;
		this.realOperator = realOperator;
	}
	
	public String getSign() {
		return this.sign;
	}
	
	public int operate(int realnum1, int realnum2) {
		return realOperator.operate(realnum1, realnum2);
	}
}
