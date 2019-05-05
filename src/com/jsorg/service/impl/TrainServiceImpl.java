package com.jsorg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsorg.mapper.TrainMapper;
import com.jsorg.pojo.Train;
import com.jsorg.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService{
	@Autowired
	TrainMapper trainMapper;
	
	@Override
	public void add(String uuid, String trainName, String sponsor, String releasetime, String departuretime,
			String deadline, String refundtime, String startingpoint, String destination, int fare, String list,
			int status) {
		trainMapper.add(uuid, trainName, sponsor, releasetime, departuretime, 
				deadline, refundtime, startingpoint, destination, fare, list, status);
		
	}

	@Override
	public List<Train> getTrainList(int status) {
		// TODO Auto-generated method stub
		return trainMapper.getTrainList(status);
	}

	@Override
	public List<Train> getAllTrain() {
		// TODO Auto-generated method stub
		return trainMapper.getAllTrain();
	}

	@Override
	public void updatestatus(String uuid, int status) {
		trainMapper.updatestatus(uuid, status);
		
	}

	@Override
	public Train geTrain(String uuid) {
		// TODO Auto-generated method stub
		return trainMapper.geTrain(uuid);
	}

}
