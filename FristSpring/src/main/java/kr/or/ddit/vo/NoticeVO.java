package kr.or.ddit.vo;

import lombok.Data;

@Data
public class NoticeVO {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
	private String noticeWriter;
	private String noticeDate;
	private String noticeHit;
}
