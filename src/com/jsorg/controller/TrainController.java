package com.jsorg.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.jsorg.pojo.Booking;
import com.jsorg.pojo.Refund;
import com.jsorg.pojo.Train;
import com.jsorg.service.BookingService;
import com.jsorg.service.RefundService;
import com.jsorg.service.TrainService;
import com.jsorg.service.UserService;
import com.jsorg.util.SendEmail;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

@Controller
@RequestMapping("train")
public class TrainController {
	
	@Autowired
	TrainService trainService;
	@Autowired
	BookingService bookingService;
	@Autowired
	SendEmail sendEmail;
	@Autowired
	UserService userService;
	@Autowired
	RefundService refundService;
	
	@ResponseBody
	@RequestMapping("addTrain")
	public Object addTrain(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String sponsor = "221500410";
		String uuid = UUID.randomUUID().toString();
		String trainName = (String) map.get("trainName"); 
		String departuretime = (String) map.get("departuretime"); 
		String deadline = (String) map.get("deadline"); 
		String refundtime = (String) map.get("refundtime"); 
		String startingpoint = (String) map.get("startingpoint"); 
		String destination = (String) map.get("destination"); 
		int fare = Integer.parseInt((String) map.get("fare")); 
		String releasetime = DateUtil.now();
		departuretime = departuretime.substring(0, 16);
		departuretime =  departuretime.replace("T", " ");
		deadline = deadline.substring(0, 16);
		deadline =  deadline.replace("T", " ");
		refundtime = refundtime.substring(0, 16);
		refundtime =  refundtime.replace("T", " ");
		
		String list = "";
		
		try {
			trainService.add(uuid, trainName, sponsor, releasetime, departuretime, 
					deadline, refundtime, startingpoint, destination, fare, list,1);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getTrainList")
	public Object getTrainList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		try {
			resultMap.put("trainList1", trainService.getTrainList(1));
			resultMap.put("trainList2", trainService.getTrainList(2));
			resultMap.put("trainList3", trainService.getTrainList(3));
			resultMap.put("trainList4", trainService.getTrainList(4));
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("adjustTrainList")
	public Object adjustTrainList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String datanow = DateUtil.now();
		try {
			List<Train> list = trainService.getAllTrain();
			for (Train train : list) {
				if (datanow.compareTo(train.getDeadline()) > 0) {
					trainService.updatestatus(train.getUuid(), 2);
				}
			}
			for (Train train : list) {
				if (datanow.compareTo(train.getRefundtime()) > 0) {
					trainService.updatestatus(train.getUuid(), 3);
				}
			}
			for (Train train : list) {
				if (datanow.compareTo(train.getDeparturetime()) > 0) {
					trainService.updatestatus(train.getUuid(), 4);
				}
			}
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("addbooking")
	public Object addbooking(@RequestBody Map map,HttpServletRequest request) {
		String uuid = UUID.randomUUID().toString();
		String trainid = (String) map.get("trainid"); 
		String applicantid = "221500410";
		String applicationtime = DateUtil.now();
		int count = Integer.parseInt((String)map.get("count")); 
		Boolean isrefund = false;
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		resultMap.put("message", "订票成功，请等待审核结果，我们将以邮件的方式通知您审核结果");
		Boolean boolean1 = false;
		try {
			for (Booking booking : bookingService.getBookingByDoubleID(trainid, applicantid)) {
				if (booking.getStatus() == 1) {
					boolean1 = true;
				}
			}
			if (boolean1) {
				Console.log("已经有尚未审核的订单，请等待审核后在进行预定");
				resultMap.put("message", "已经有尚未审核的订单，请等待审核");
			}else {
				bookingService.add(uuid, trainid, applicantid, applicationtime, 
						count, isrefund, 1);
				String content = "你已经成功预定了票";
				sendEmail.send(userService.getInformation(applicantid).getEmail(), content);
			}
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getInBookingList")
	public Object getInTrainList(HttpServletRequest request) {
		String applicantid = "221500410";
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		List<Map> list = new ArrayList<Map>();
		List<Map> Moderatedlist = new ArrayList<Map>();
		List<Map> finishlist = new ArrayList<Map>();
		try {
			for (Booking booking : bookingService.getBookingModerated(applicantid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("trainID", trainService.geTrain(booking.getTrainid()).getUuid());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				map.put("startingpoint", trainService.geTrain(booking.getTrainid()).getStartingpoint());
				map.put("destination", trainService.geTrain(booking.getTrainid()).getDestination());
				map.put("bookingID", booking.getUuid());
				map.put("count", booking.getCount());
				Moderatedlist.add(map);
			}
			resultMap.put("Moderated", Moderatedlist);
			for (Booking booking : bookingService.getBookingUnFinish(applicantid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("trainID", trainService.geTrain(booking.getTrainid()).getUuid());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				map.put("startingpoint", trainService.geTrain(booking.getTrainid()).getStartingpoint());
				map.put("destination", trainService.geTrain(booking.getTrainid()).getDestination());
				map.put("bookingID", booking.getUuid());
				map.put("count", booking.getCount());
				if (!booking.getIsrefund()) {
					list.add(map);
				}	
			}
			resultMap.put("unfinish", list);
			for (Booking booking : bookingService.getBookingFinish(applicantid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("trainID", trainService.geTrain(booking.getTrainid()).getUuid());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				map.put("startingpoint", trainService.geTrain(booking.getTrainid()).getStartingpoint());
				map.put("destination", trainService.geTrain(booking.getTrainid()).getDestination());
				map.put("bookingID", booking.getUuid());
				map.put("count", booking.getCount());
				if (booking.getIsrefund()) {
					finishlist.add(map);
				}	
			}
			resultMap.put("finish", finishlist);
			List<Map> refundlist = new ArrayList<Map>();
			for (Refund refund : refundService.getRefundByapplicantid(applicantid)) {
				Map<String, Object> map = new HashMap<String, Object>();
				Booking booking = bookingService.getBookingBybookingid(refund.getBookingid());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				map.put("startingpoint", trainService.geTrain(booking.getTrainid()).getStartingpoint());
				map.put("destination", trainService.geTrain(booking.getTrainid()).getDestination());
				map.put("count", booking.getCount());
				refundlist.add(map);
			}
			resultMap.put("refundList", refundlist);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	
	@ResponseBody
	@RequestMapping("refundticket")
	public Object refundticket(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String uuid = UUID.randomUUID().toString();
		String applicantid = "221500410";
		String bookingID = (String) map.get("bookingID"); 
		String reason = (String) map.get("reason"); 
		String nowdate = DateUtil.now();
		try {
			if (refundService.getRefundBydoubleid(bookingID, applicantid).size() == 0) {
				resultMap.put("message", "退票申请成功，请耐心等待处理结果，我们将以邮件的方式通知您");
				String content = "您正在申请退票，请等待审核";
				refundService.add(uuid, bookingID, applicantid, nowdate, reason, false);
				sendEmail.send(userService.getInformation(applicantid).getEmail(), content);
			}else {
				resultMap.put("message", "您已经提交过退票申请，正在处理中，请勿重复申请。");
			}
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("getTicketingList")
	public Object getTicketingList(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		List<Map> bookingList = new ArrayList<Map>();
		List<Map> refundList = new ArrayList<Map>();
		try {
			for (Booking booking : bookingService.getBookingUnCheck()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("bookingid", booking.getUuid());
				map.put("client_id", booking.getApplicantid());
				map.put("count", booking.getCount());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				bookingList.add(map);
			}
			resultMap.put("bookingList", bookingList);
			for (Refund refund : refundService.getRefundListUncheck()) {
				Map<String, Object> map = new HashMap<String, Object>();
				Booking booking = bookingService.getBookingBybookingid(refund.getBookingid());
				Console.log(refund.getUuid());
				map.put("refundid", refund.getUuid());
				map.put("client_id", booking.getApplicantid());
				map.put("trainName", trainService.geTrain(booking.getTrainid()).getTrainName());
				map.put("departuretime", trainService.geTrain(booking.getTrainid()).getDeparturetime());
				map.put("count", booking.getCount());
				refundList.add(map);
			}
			resultMap.put("refundList", refundList);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("processBooking")
	public Object processBooking(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String bookingid = (String) map.get("bookingid"); 
		String result = (String) map.get("result"); 
		String reason = (String) map.get("reason"); 
		String approval_id = "221500410";
		String datenow = DateUtil.now();
		try {
			bookingService.updateBookingCheck(bookingid, 2, true, 
					approval_id, datenow, result, reason);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
	@ResponseBody
	@RequestMapping("processRefund")
	public Object processRefund(@RequestBody Map map,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("status", "success");
		String refundid = (String) map.get("refundid"); 
		String result = (String) map.get("result"); 
		String reason = (String) map.get("reason"); 
		String approval_id = "221500410";
		String datenow = DateUtil.now();
		try {
			refundService.updateRefundUncheck(refundid, true, approval_id, datenow,
					result, reason);
		} catch (Exception e) {
			resultMap.put("status", "someerror");
			resultMap.put("error", e);
			Console.log(e);
		}
		JSONObject jsonObject = (JSONObject) JSONObject.toJSON(resultMap);
		return jsonObject;
	}
}
