package my.api.project.dto;

import org.apache.ibatis.type.Alias;

@Alias(value="bidInfo")
public class BidInfoDTO {
	//bidNtceSttusNm bsnsDivNm
	private double asignBdgtAmt; // 예산 금액
    private String bidBeginDate; // 입찰 시작 날짜
    private String bidBeginTm; // 입찰 시작 시간
    private String bidClseDate; // 입찰 종료 날짜
    private String bidClseTm; // 입찰 종료 시간
    private String bidNtceBgn; // 입찰 공고 시작
    private String bidNtceDate; // 입찰 공고 날짜
    private String bidNtceNm; // 입찰 공고 이름
    private String bidNtceNo; // 입찰 공고 번호
    private String bidNtceOrd; // 입찰 공고 순서
    private String bidNtceSttusNm; // 입찰 공고 상태 이름
    private String bidNtceUrl; // 입찰 공고 URL
    private String bidPrtcptQlfctRgstClseDate; // 입찰 참가 자격 등록 마감 날짜
    private String bidPrtcptQlfctRgstClseTm; // 입찰 참가 자격 등록 마감 시간
    private String bidprcPsblIndstrytyNm; // 입찰 가능 산업명
    private String bidwinrDcsnMthdNm; // 낙찰자 결정 방법명
    private String bsnsDivNm; // 사업 분류명
    private String cmmnCntrctYn; // 공동 계약 여부
    private String cmmnReciptAgrmntClseDate; // 공동 수급 협정 마감 날짜
    private String cmmnReciptAgrmntClseTm; // 공동 수급 협정 마감 시간
    private String cmmnReciptMethdNm; // 공동 수급 방법명
    private String cntrctCnclsMthdNm; // 계약 체결 방법명
    private String cntrctCnclsSttusNm; // 계약 체결 상태명
    private String dataBssDate; // 데이터 기준 날짜
    private String dmndInsttCd; // 수요 기관 코드
    private String dmndInsttNm; // 수요 기관 이름
    private String dmndInsttOfclDeptNm; // 수요 기관 부서명
    private String dmndInsttOfclEmailAdrs; // 수요 기관 이메일 주소
    private String dmndInsttOfclNm; // 수요 기관 담당자 이름
    private String dmndInsttOfclTel; // 수요 기관 담당자 전화번호
    private String elctrnBidYn; // 전자 입찰 여부
    private String indstrytyLmtYn; // 산업 제한 여부
    private String intrntnlBidYn; // 국제 입찰 여부
    private String ntceInsttCd; // 공고 기관 코드
    private String ntceInsttNm; // 공고 기관 이름
    private String ntceInsttOfclDeptNm; // 공고 기관 부서명
    private String ntceInsttOfclEmailAdrs; // 공고 기관 이메일 주소
    private String ntceInsttOfclNm; // 공고 기관 담당자 이름
    private String ntceInsttOfclTel; // 공고 기관 담당자 전화번호
    private String opengDate; // 개찰 날짜
    private String opengPlce; // 개찰 장소
    private String opengTm; // 개찰 시간
    private String ppsNtceYn; // PPS 공고 여부
    private double presmptPrce; // 추정 가격
    private String presnatnOprtnDate; // 발표 운영 날짜
    private String presnatnOprtnPlce; // 발표 운영 장소
    private String presnatnOprtnTm; // 발표 운영 시간
    private String presnatnOprtnYn; // 발표 운영 여부
    private String prtcptPsblRgnNm; // 참가 가능 지역명
    private String refNtceNo; // 참고 공고 번호
    private String refNtceOrd; // 참고 공고 순서
    private String rgnLmtYn; // 지역 제한 여부
    private String rsrvtnPrceDcsnMthdNm; // 예약 가격 결정 방법명
    private int idx; // primary key
    
    
    
    
	public String getCmmnCntrctYn() {
		return cmmnCntrctYn;
	}
	public String getElctrnBidYn() {
		return elctrnBidYn;
	}
	public String getIndstrytyLmtYn() {
		return indstrytyLmtYn;
	}
	public String getIntrntnlBidYn() {
		return intrntnlBidYn;
	}
	public String getPpsNtceYn() {
		return ppsNtceYn;
	}
	public String getPresnatnOprtnYn() {
		return presnatnOprtnYn;
	}
	public String getRgnLmtYn() {
		return rgnLmtYn;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public double getAsignBdgtAmt() {
		return asignBdgtAmt;
	}
	public String getBidBeginDate() {
		return bidBeginDate;
	}
	public String getBidBeginTm() {
		return bidBeginTm;
	}
	public String getBidClseDate() {
		return bidClseDate;
	}
	public String getBidClseTm() {
		return bidClseTm;
	}
	public String getBidNtceBgn() {
		return bidNtceBgn;
	}
	public String getBidNtceDate() {
		return bidNtceDate;
	}
	public String getBidNtceNm() {
		return bidNtceNm;
	}
	public String getBidNtceNo() {
		return bidNtceNo;
	}
	public String getBidNtceOrd() {
		return bidNtceOrd;
	}
	public String getBidNtceSttusNm() {
		String bidNtceSttusNmCut = bidNtceSttusNm.split("공고")[0];
		return bidNtceSttusNmCut;
	}
	public String getBidNtceUrl() {
		return bidNtceUrl;
	}
	public String getBidPrtcptQlfctRgstClseDate() {
		return bidPrtcptQlfctRgstClseDate;
	}
	public String getBidPrtcptQlfctRgstClseTm() {
		return bidPrtcptQlfctRgstClseTm;
	}
	public String getBidprcPsblIndstrytyNm() {
		return bidprcPsblIndstrytyNm;
	}
	public String getBidwinrDcsnMthdNm() {
		return bidwinrDcsnMthdNm;
	}
	public String getBsnsDivNm() {
		return bsnsDivNm;
	}
	public String isCmmnCntrctYn() {
		return cmmnCntrctYn;
	}
	public String getCmmnReciptAgrmntClseDate() {
		return cmmnReciptAgrmntClseDate;
	}
	public String getCmmnReciptAgrmntClseTm() {
		return cmmnReciptAgrmntClseTm;
	}
	public String getCmmnReciptMethdNm() {
		return cmmnReciptMethdNm;
	}
	public String getCntrctCnclsMthdNm() {
		return cntrctCnclsMthdNm;
	}
	public String getCntrctCnclsSttusNm() {
		return cntrctCnclsSttusNm;
	}
	public String getDataBssDate() {
		return dataBssDate;
	}
	public String getDmndInsttCd() {
		return dmndInsttCd;
	}
	public String getDmndInsttNm() {
		return dmndInsttNm;
	}
	public String getDmndInsttOfclDeptNm() {
		return dmndInsttOfclDeptNm;
	}
	public String getDmndInsttOfclEmailAdrs() {
		return dmndInsttOfclEmailAdrs;
	}
	public String getDmndInsttOfclNm() {
		return dmndInsttOfclNm;
	}
	public String getDmndInsttOfclTel() {
		return dmndInsttOfclTel;
	}
	public String isElctrnBidYn() {
		return elctrnBidYn;
	}
	public String isIndstrytyLmtYn() {
		return indstrytyLmtYn;
	}
	public String isIntrntnlBidYn() {
		return intrntnlBidYn;
	}
	public String getNtceInsttCd() {
		return ntceInsttCd;
	}
	public String getNtceInsttNm() {
		return ntceInsttNm;
	}
	public String getNtceInsttOfclDeptNm() {
		return ntceInsttOfclDeptNm;
	}
	public String getNtceInsttOfclEmailAdrs() {
		return ntceInsttOfclEmailAdrs;
	}
	public String getNtceInsttOfclNm() {
		return ntceInsttOfclNm;
	}
	public String getNtceInsttOfclTel() {
		return ntceInsttOfclTel;
	}
	public String getOpengDate() {
		return opengDate;
	}
	public String getOpengPlce() {
		return opengPlce;
	}
	public String getOpengTm() {
		return opengTm;
	}
	public String isPpsNtceYn() {
		return ppsNtceYn;
	}
	public double getPresmptPrce() {
		return presmptPrce;
	}
	public String getPresnatnOprtnDate() {
		return presnatnOprtnDate;
	}
	public String getPresnatnOprtnPlce() {
		return presnatnOprtnPlce;
	}
	public String getPresnatnOprtnTm() {
		return presnatnOprtnTm;
	}
	public String isPresnatnOprtnYn() {
		return presnatnOprtnYn;
	}
	public String getPrtcptPsblRgnNm() {
		return prtcptPsblRgnNm;
	}
	public String getRefNtceNo() {
		return refNtceNo;
	}
	public String getRefNtceOrd() {
		return refNtceOrd;
	}
	public String isRgnLmtYn() {
		return rgnLmtYn;
	}
	public String getRsrvtnPrceDcsnMthdNm() {
		return rsrvtnPrceDcsnMthdNm;
	}
	public void setAsignBdgtAmt(double asignBdgtAmt) {
		this.asignBdgtAmt = asignBdgtAmt;
	}
	public void setBidBeginDate(String bidBeginDate) {
		this.bidBeginDate = bidBeginDate;
	}
	public void setBidBeginTm(String bidBeginTm) {
		this.bidBeginTm = bidBeginTm;
	}
	public void setBidClseDate(String bidClseDate) {
		this.bidClseDate = bidClseDate;
	}
	public void setBidClseTm(String bidClseTm) {
		this.bidClseTm = bidClseTm;
	}
	public void setBidNtceBgn(String bidNtceBgn) {
		this.bidNtceBgn = bidNtceBgn;
	}
	public void setBidNtceDate(String bidNtceDate) {
		this.bidNtceDate = bidNtceDate;
	}
	public void setBidNtceNm(String bidNtceNm) {
		this.bidNtceNm = bidNtceNm;
	}
	public void setBidNtceNo(String bidNtceNo) {
		this.bidNtceNo = bidNtceNo;
	}
	public void setBidNtceOrd(String bidNtceOrd) {
		this.bidNtceOrd = bidNtceOrd;
	}
	public void setBidNtceSttusNm(String bidNtceSttusNm) {
		this.bidNtceSttusNm = bidNtceSttusNm;
	}
	public void setBidNtceUrl(String bidNtceUrl) {
		this.bidNtceUrl = bidNtceUrl;
	}
	public void setBidPrtcptQlfctRgstClseDate(String bidPrtcptQlfctRgstClseDate) {
		this.bidPrtcptQlfctRgstClseDate = bidPrtcptQlfctRgstClseDate;
	}
	public void setBidPrtcptQlfctRgstClseTm(String bidPrtcptQlfctRgstClseTm) {
		this.bidPrtcptQlfctRgstClseTm = bidPrtcptQlfctRgstClseTm;
	}
	public void setBidprcPsblIndstrytyNm(String bidprcPsblIndstrytyNm) {
		this.bidprcPsblIndstrytyNm = bidprcPsblIndstrytyNm;
	}
	public void setBidwinrDcsnMthdNm(String bidwinrDcsnMthdNm) {
		this.bidwinrDcsnMthdNm = bidwinrDcsnMthdNm;
	}
	public void setBsnsDivNm(String bsnsDivNm) {
		this.bsnsDivNm = bsnsDivNm;
	}
	public void setCmmnCntrctYn(String cmmnCntrctYn) {
		this.cmmnCntrctYn = cmmnCntrctYn;
	}
	public void setCmmnReciptAgrmntClseDate(String cmmnReciptAgrmntClseDate) {
		this.cmmnReciptAgrmntClseDate = cmmnReciptAgrmntClseDate;
	}
	public void setCmmnReciptAgrmntClseTm(String cmmnReciptAgrmntClseTm) {
		this.cmmnReciptAgrmntClseTm = cmmnReciptAgrmntClseTm;
	}
	public void setCmmnReciptMethdNm(String cmmnReciptMethdNm) {
		this.cmmnReciptMethdNm = cmmnReciptMethdNm;
	}
	public void setCntrctCnclsMthdNm(String cntrctCnclsMthdNm) {
		this.cntrctCnclsMthdNm = cntrctCnclsMthdNm;
	}
	public void setCntrctCnclsSttusNm(String cntrctCnclsSttusNm) {
		this.cntrctCnclsSttusNm = cntrctCnclsSttusNm;
	}
	public void setDataBssDate(String dataBssDate) {
		this.dataBssDate = dataBssDate;
	}
	public void setDmndInsttCd(String dmndInsttCd) {
		this.dmndInsttCd = dmndInsttCd;
	}
	public void setDmndInsttNm(String dmndInsttNm) {
		this.dmndInsttNm = dmndInsttNm;
	}
	public void setDmndInsttOfclDeptNm(String dmndInsttOfclDeptNm) {
		this.dmndInsttOfclDeptNm = dmndInsttOfclDeptNm;
	}
	public void setDmndInsttOfclEmailAdrs(String dmndInsttOfclEmailAdrs) {
		this.dmndInsttOfclEmailAdrs = dmndInsttOfclEmailAdrs;
	}
	public void setDmndInsttOfclNm(String dmndInsttOfclNm) {
		this.dmndInsttOfclNm = dmndInsttOfclNm;
	}
	public void setDmndInsttOfclTel(String dmndInsttOfclTel) {
		this.dmndInsttOfclTel = dmndInsttOfclTel;
	}
	public void setElctrnBidYn(String elctrnBidYn) {
		this.elctrnBidYn = elctrnBidYn;
	}
	public void setIndstrytyLmtYn(String indstrytyLmtYn) {
		this.indstrytyLmtYn = indstrytyLmtYn;
	}
	public void setIntrntnlBidYn(String intrntnlBidYn) {
		this.intrntnlBidYn = intrntnlBidYn;
	}
	public void setNtceInsttCd(String ntceInsttCd) {
		this.ntceInsttCd = ntceInsttCd;
	}
	public void setNtceInsttNm(String ntceInsttNm) {
		this.ntceInsttNm = ntceInsttNm;
	}
	public void setNtceInsttOfclDeptNm(String ntceInsttOfclDeptNm) {
		this.ntceInsttOfclDeptNm = ntceInsttOfclDeptNm;
	}
	public void setNtceInsttOfclEmailAdrs(String ntceInsttOfclEmailAdrs) {
		this.ntceInsttOfclEmailAdrs = ntceInsttOfclEmailAdrs;
	}
	public void setNtceInsttOfclNm(String ntceInsttOfclNm) {
		this.ntceInsttOfclNm = ntceInsttOfclNm;
	}
	public void setNtceInsttOfclTel(String ntceInsttOfclTel) {
		this.ntceInsttOfclTel = ntceInsttOfclTel;
	}
	public void setOpengDate(String opengDate) {
		this.opengDate = opengDate;
	}
	public void setOpengPlce(String opengPlce) {
		this.opengPlce = opengPlce;
	}
	public void setOpengTm(String opengTm) {
		this.opengTm = opengTm;
	}
	public void setPpsNtceYn(String ppsNtceYn) {
		this.ppsNtceYn = ppsNtceYn;
	}
	public void setPresmptPrce(double presmptPrce) {
		this.presmptPrce = presmptPrce;
	}
	public void setPresnatnOprtnDate(String presnatnOprtnDate) {
		this.presnatnOprtnDate = presnatnOprtnDate;
	}
	public void setPresnatnOprtnPlce(String presnatnOprtnPlce) {
		this.presnatnOprtnPlce = presnatnOprtnPlce;
	}
	public void setPresnatnOprtnTm(String presnatnOprtnTm) {
		this.presnatnOprtnTm = presnatnOprtnTm;
	}
	public void setPresnatnOprtnYn(String presnatnOprtnYn) {
		this.presnatnOprtnYn = presnatnOprtnYn;
	}
	public void setPrtcptPsblRgnNm(String prtcptPsblRgnNm) {
		this.prtcptPsblRgnNm = prtcptPsblRgnNm;
	}
	public void setRefNtceNo(String refNtceNo) {
		this.refNtceNo = refNtceNo;
	}
	public void setRefNtceOrd(String refNtceOrd) {
		this.refNtceOrd = refNtceOrd;
	}
	public void setRgnLmtYn(String rgnLmtYn) {
		this.rgnLmtYn = rgnLmtYn;
	}
	public void setRsrvtnPrceDcsnMthdNm(String rsrvtnPrceDcsnMthdNm) {
		this.rsrvtnPrceDcsnMthdNm = rsrvtnPrceDcsnMthdNm;
	}
}
