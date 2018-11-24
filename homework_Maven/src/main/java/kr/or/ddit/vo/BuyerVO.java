package kr.or.ddit.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class BuyerVO implements Serializable{
	private String buyer_id;
	private String buyer_name;
	private String buyer_lgu;
	private String buyer_bank;
	private String buyer_bankno;
	private String buyer_bankname;
	private String buyer_zip;
	private String buyer_add1;
	private String buyer_add2;
	private String buyer_comtel;
	private String buyer_fax;
	private String buyer_mail;
	private String buyer_charger;
	private String buyer_telext;
	
	public BuyerVO() {
		super();
	}
	
	public BuyerVO(String buyer_id, String buyer_name) {
		super();
		this.buyer_id = buyer_id;
		this.buyer_name = buyer_name;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((buyer_id == null) ? 0 : buyer_id.hashCode());
		result = prime * result + ((buyer_lgu == null) ? 0 : buyer_lgu.hashCode());
		result = prime * result + ((buyer_name == null) ? 0 : buyer_name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BuyerVO other = (BuyerVO) obj;
		if (buyer_id == null) {
			if (other.buyer_id != null)
				return false;
		} else if (!buyer_id.equals(other.buyer_id))
			return false;
		if (buyer_lgu == null) {
			if (other.buyer_lgu != null)
				return false;
		} else if (!buyer_lgu.equals(other.buyer_lgu))
			return false;
		if (buyer_name == null) {
			if (other.buyer_name != null)
				return false;
		} else if (!buyer_name.equals(other.buyer_name))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "BuyerVO [buyer_id=" + buyer_id + ", buyer_name=" + buyer_name + ", buyer_lgu=" + buyer_lgu
				+ ", buyer_bank=" + buyer_bank + ", buyer_bankno=" + buyer_bankno + ", buyer_bankname=" + buyer_bankname
				+ ", buyer_zip=" + buyer_zip + ", buyer_add1=" + buyer_add1 + ", buyer_add2=" + buyer_add2
				+ ", buyer_comtel=" + buyer_comtel + ", buyer_fax=" + buyer_fax + ", buyer_mail=" + buyer_mail
				+ ", buyer_charger=" + buyer_charger + ", buyer_telext=" + buyer_telext + "]";
	}
	
	
}
