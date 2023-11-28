package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;

@Data
public class PaginationInfoVO<T> {
	private int totalRecord;	// �� �Խñ� ��
	private int totalPage;		// �� ������ ��
	private int currentPage;	// ���� ������
	private int screenSize = 10;		// ������ �� �Խñ� ��
	private int blockSize = 5;		// ������ ��� ��
	private int startRow;		// ���� row
	private int endRow;			// �� row
	private int startPage;		// ���� ������
	private int endPage;		// �� ������
	private List<T> dataList;	// ����� ���� ������ ����Ʈ
	private String searchType;	// �˻� Ÿ��
	private String searchWord;	// �˻� �ܾ�(Ű����)
	
	public PaginationInfoVO() {}
	
	// PageinationInfoVO ��ü�� ���鶧, �� �������� �Խñ� ���� ������ ��� ���� ���ϴ� ������ �ʱ�ȭ �� �� �ִ�.
	public PaginationInfoVO(int screenSize, int blockSize) {
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		
		// ceil �Լ� : �ø�(ex) 121/10 = 13)
		totalPage = (int)Math.ceil(totalRecord / (double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;					// ���� ������
		endRow = currentPage * screenSize;				// �� row = ���������� * �� �������� �Խñ� ��
		startRow = endRow - (screenSize - 1);			// ���� row = �� row - (�� ������ �� �Խñ� �� - 1)
			// ������ ������ = (���� ������ + (������ ��� ������ - 1)) / ������ ��� ������ * ������ ��� ������
			// /blockSize * blockSize�� 1,2,3,4,5,... ������ ���� �Ǽ� ����� �ƴ� ���� ����� ���� endPage�� ���ϱ� ����
		endPage = (currentPage + (blockSize - 1)) / blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	public String getPagingHTML() {
		// startPage�� 1,6,11... ������ �ش� ������ ���·� �����ؼ� �ö�
		// 1-5 ���� �ȿ� �ִ� ���� Prev�� �������� �ʴ´�.
		// 6 ���� ���� Prev�� ��������� ������ �����ȴ�.
		StringBuffer html = new StringBuffer();
		html.append("<ul class='pagination pagination-sm m-0 float-right'>");
		
		if(startPage > 1) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='" +
					(startPage - blockSize) + "'>Prev</a></li>");
		}
		
		for(int i = startPage; i <= (endPage < totalPage ? endPage : totalPage); i++) {
			if(i == currentPage) {
				html.append("<li class='page-item active'><span class='page-link'>" + i + "</span></li>");
			}else {
				html.append("<li class='page-item'><a href='' class='page-link' data-page='" +
						i + "'>" + i + "</a></li>");
			}
		}
		
		if(endPage < totalPage) {
			html.append("<li class='page-item'><a href='' class='page-link' data-page='" +
						(endPage + 1) + "'>Next</a></li>");
		}
		
		html.append("</ul>");
		return html.toString();
	}
	
}
