package com.jsorg.service;

import java.util.List;

import com.jsorg.pojo.Train;

public interface TrainService {
	void add(String uuid,String trainName,String sponsor,String releasetime,
			String departuretime,String deadline,String refundtime,String startingpoint,
			String destination,int fare,String list,int status);
	List<Train> getTrainList(int status);
	List<Train> getAllTrain();
	void updatestatus(String uuid,int status);
	Train geTrain(String uuid);
}
