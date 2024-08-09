package my.api.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import my.api.project.dto.BidInfoDTO;

@Mapper
public interface BidDAO {

	void insertBid(BidInfoDTO bidInfo);

	List<BidInfoDTO> bidList();

	int bidListCnt();

}
