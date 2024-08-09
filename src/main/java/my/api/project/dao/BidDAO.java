package my.api.project.dao;

import org.apache.ibatis.annotations.Mapper;

import my.api.project.dto.BidInfoDTO;

@Mapper
public interface BidDAO {

	void insertBid(BidInfoDTO bidInfo);

}
