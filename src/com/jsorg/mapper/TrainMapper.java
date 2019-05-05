package com.jsorg.mapper;

import java.util.List;

import com.jsorg.pojo.Train;

public interface TrainMapper {
	public void add(String uuid,String trainName,String sponsor,String releasetime,
			String departuretime,String deadline,String refundtime,String startingpoint,
			String destination,int fare,String list,int status);
	public List<Train> getTrainList(int status);
	public List<Train> getAllTrain();
	public void updatestatus(String uuid,int status);
	public Train geTrain(String uuid);
}
