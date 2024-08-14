package my.api.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import my.api.project.dto.BidInfoDTO;
import my.api.project.dto.FilterDTO;

@Mapper
public interface BidDAO {

	void insertBid(BidInfoDTO bidInfo);

	List<BidInfoDTO> bidList();

	int bidListCnt();

	List<BidInfoDTO> filterBidList(FilterDTO data);

	int filterBidListCnt(FilterDTO data);

	BidInfoDTO newBid();

	List<BidInfoDTO> newBidList();

	int newBidCnt();

}
