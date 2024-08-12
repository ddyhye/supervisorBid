package my.api.project.dto;

import org.apache.ibatis.type.Alias;

@Alias(value="filter")
public class FilterDTO {
	private String supervisorType;
	private String keyword;
	private String strDate;
	private String endDate;
	private String dateLimit;
	private String regionLimit;
	private String togetherLimit;
	private String array;
	
	
	public String getSupervisorType() {
		return supervisorType;
	}
	public String getKeyword() {
		return keyword;
	}
	public String getStrDate() {
		return strDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getDateLimit() {
		return dateLimit;
	}
	public String getRegionLimit() {
		return regionLimit;
	}
	public String getTogetherLimit() {
		return togetherLimit;
	}
	public String getArray() {
		return array;
	}
	public void setSupervisorType(String supervisorType) {
		this.supervisorType = supervisorType;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setDateLimit(String dateLimit) {
		this.dateLimit = dateLimit;
	}
	public void setRegionLimit(String regionLimit) {
		this.regionLimit = regionLimit;
	}
	public void setTogetherLimit(String togetherLimit) {
		this.togetherLimit = togetherLimit;
	}
	public void setArray(String array) {
		this.array = array;
	}
}
