package com.jemmyee.framework.dao;

import java.util.List;

import com.jemmyee.framework.web.Constants;

/**
 * @Description:hibernate离线查询，分页辅助类
 * @author:jemmyee@gmail.com
 * @date:2010-10-20
 * @version:v1.0
 */
public class Page {
	// 默认记录数
	public final static int PAGESIZE = 10;
	// 每页记录数
	private int pageSize = PAGESIZE;

	private List items;

	private int totalCount;

	private int pageCount;

	private int[] indexes = new int[0];//eg  0  16   32
	//所有的page
	private int[] pages = new int[0];//eg  1  2   3  4
	//间隔显示的page
	private int[] showpages = new int[0];//eg  1  2   3  4

	private int startIndex = 0;

	private int nextIndex;

	private int previousIndex;

	private int lastIndex;

	private final int firstIndex = 0;
	// 显示当前页
	private int currentPage;

	// 数字分页间隔
	private int intervalCount = 5;
    //分页标签
	private String pageContent;
	
	//无数据时显示内容
	private String noDataMSG;

	public Page() {
	}

	public Page(List items, int totalCount) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		if (totalCount >= PAGESIZE) {
			this.setPageCount(totalCount / PAGESIZE);
		} else {
			this.setPageCount(1);
		}
		setItems(items);
		setStartIndex(0);
	}

	public Page(List items, int totalCount, int startIndex) {
		setPageSize(PAGESIZE);
		setTotalCount(totalCount);
		if (totalCount >= PAGESIZE) {
			this.setPageCount(totalCount / PAGESIZE);
		} else {
			this.setPageCount(1);
		}
		setItems(items);
		setStartIndex(startIndex);
	}

	public Page(List items, int totalCount, int pageSize, int startIndex) {
		setPageSize(pageSize);
		setTotalCount(totalCount);
		if (totalCount >= pageSize) {
			// 解决最后一页问题
			if (totalCount % pageSize > 0) {
				this.setPageCount(totalCount / pageSize + 1);
			} else {
				this.setPageCount(totalCount / pageSize);
			}
		} else {
			this.setPageCount(1);
		}
		setItems(items);
		setStartIndex(startIndex);
	}

	public List getItems() {
		return items;
	}

	public int getItemsSize(){
		if(this.items != null && this.items.size() != 0){
			return this.items.size();
		}
		return 0 ;
	}
	
	public void setItems(List items) {
		this.items = items;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			int count = totalCount / pageSize;
			if (totalCount % pageSize > 0)
				count++;
			indexes = new int[count];
			for (int i = 0; i < count; i++) {
				indexes[i] = pageSize * i;
			}
		} else {
			this.totalCount = 0;
		}
	}

	public int[] getIndexes() {
		return indexes;
	}

	public void setIndexes(int[] indexes) {
		this.indexes = indexes;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		if (totalCount <= 0)
			this.startIndex = 0;
		else if (startIndex >= totalCount)
			this.startIndex = indexes[indexes.length - 1];
		else if (startIndex < 0)
			this.startIndex = 0;
		else {
			this.startIndex = indexes[startIndex / pageSize];
		}
	}

	public int getNextIndex() {
		nextIndex = getStartIndex() + pageSize;
		if (nextIndex >= totalCount)
			return getStartIndex();
		else
			return nextIndex;
	}

	public int getPreviousIndex() {
		previousIndex = getStartIndex() - pageSize;
		if (previousIndex < 0)
			return 0;
		else
			return previousIndex;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getLastIndex() {
		if (totalCount % pageSize == 0) {
			lastIndex = totalCount - pageSize;
		} else {
			lastIndex = totalCount - totalCount % pageSize;
		}
		if (lastIndex <= 0) {
			lastIndex = 0;
		}
		return lastIndex;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	/**
	 * 功能:取得当前页值
	 * 
	 * @return
	 * @date Sep 25, 2008
	 * @time 12:38:30 PM
	 */
	public int getCurrentPage() {
		return startIndex / pageSize + 1;
	}

	public int getIntervalCount() {
		return intervalCount;
	}

	public String getHtml() {
		StringBuffer html = new StringBuffer();

		html.append("总记录:\n");
		html.append("<font color=\"red\">" + this.getTotalCount()+ "</font>\n");
		html.append("总页数:\n");
		html.append("<font color=\"red\">" + this.getPageCount() + "</font>\n");
		html.append("当前页:\n");
		html.append("<font color=\"red\">" + this.getCurrentPage()+ "</font>\n");

		if (this.totalCount == 0)
			html.append("首页  上一页 下一页  末页\n");
		else {
			if (this.getStartIndex() > this.getFirstIndex()) {
				html.append(" <a href=\"javascript:gotoPage("
						+ this.getFirstIndex() + ")\">首页</a> \n");
				html.append(" <a href=\"javascript:gotoPage("
						+ this.getPreviousIndex() + ")\">上一页</a> \n");
			} else
				html.append(" 首页  上一页 \n");

			for (int i = 0; i < this.getIndexes().length; i++) {
				if ((this.getIndexes()[i] / this.getPageSize() + 1) > (this.getCurrentPage() - this.getIntervalCount()))
					if ((this.getIndexes()[i] / this.getPageSize() + 1) < this.getCurrentPage()+ this.getIntervalCount()) {
						html.append(" <a href=\"javascript:gotoPage("+ this.getIndexes()[i]+ ")\">");
						if(this.getCurrentPage() != (this.getIndexes()[i]/ this.getPageSize() + 1))html.append("[");	
						html.append(this.getIndexes()[i]/ this.getPageSize() + 1);
						if(this.getCurrentPage() != (this.getIndexes()[i]/ this.getPageSize() + 1))html.append("]");	
						html.append("</a> \n");
					}
			}

			if (this.getStartIndex() < this.getLastIndex()) {
				html.append(" <a href=\"javascript:gotoPage("
						+ this.getNextIndex() + ")\">下一页</a> \n");
				html.append(" <a href=\"javascript:gotoPage("
						+ this.getLastIndex() + ")\">末页</a> \n");
			} else
				html.append(" 下一页  末页 \n");
		}
		html.append("\n\n");
		html.append("<script>\n");
		html.append("function gotoPage(index){\n");
		html.append("\t var pageForm = document.getElementsByTagName(\"form\")[0];\n");
		html.append("\t pageForm.action = pageForm.action + \"?startIndex=\" + index \n");
		html.append("\t pageForm.submit();\n");
		html.append("}\n");
		html.append("</script>");
		return html.toString();
	}
	/**
	 * 功能:功能:分页标签。需要在action中置顶跳转的url 如：showLog.xhtml
	 * @param action 跳转的url
	 * @param flag 判断是否是查询分页，true为是，false为否
	 * @date Nov 5, 2008
	 * @time 11:20:36 AM
	 */
	public void setPageContent(String action){
		StringBuffer sb=new StringBuffer();
		sb.append("共\n");
		sb.append("" + this.getTotalCount()+ "条记录&nbsp;\n");
		sb.append("每页\n");
		sb.append("" + this.getPageSize()+ "条&nbsp;\n");
		sb.append("当前第\n");
		sb.append("" + this.getCurrentPage() + "/\n"+this.getPageCount()+"页\n&nbsp;&nbsp;&nbsp;&nbsp;");

		if (this.totalCount == 0)
			sb.append("首页  上一页 下一页  末页\n");
		else {
			if (this.getStartIndex() > this.getFirstIndex()) {
		
					sb.append("<a href=\""+ action +this.getFirstIndex()+"\"> 首页 </a>\n");
					sb.append("<a href=\""+ action +this.getPreviousIndex()+"\"> 上一页 </a>\n");
				
			} else
				sb.append(" 首页  上一页 \n");

			for (int i = 0; i < this.getIndexes().length; i++) {
				if ((this.getIndexes()[i] / this.getPageSize() + 1) > (this.getCurrentPage() - this.getIntervalCount()))
					if ((this.getIndexes()[i] / this.getPageSize() + 1) < this.getCurrentPage()+ this.getIntervalCount()) {
					    sb.append("<a href=\""+ action +this.getIndexes()[i]+"\">" +(this.getIndexes()[i]/ this.getPageSize() + 1)+ " </a>\n");
					}
			}

			if (this.getStartIndex() < this.getLastIndex()) {
					sb.append("<a href=\""+ action +this.getNextIndex()+"\"> 下一页 </a>\n");
					sb.append("<a href=\""+ action +this.getLastIndex()+"\">末页 </a>\n");
			} else
				sb.append("下一页  末页 \n");
		}
		pageContent=sb.toString();
	}

	public String getPageContent() {
		return pageContent;
	}
	public int[] getPages() {
		pages=new int[getIndexes().length];
		for(int i=0;i<getIndexes().length;i++){
		    pages[i]=i+1;
		}
		return pages;
	}

	public int[] getShowpages() {
		showpages=new int[getIndexes().length];
		for (int i = 0; i < this.getIndexes().length; i++) {
			if ((this.getIndexes()[i] / this.getPageSize() + 1) > (this.getCurrentPage() - this.getIntervalCount()))
				if ((this.getIndexes()[i] / this.getPageSize() + 1) < this.getCurrentPage()+ this.getIntervalCount()) {
				    showpages[i]=this.getIndexes()[i]/ this.getPageSize() + 1;
				}
		}
		return showpages;
	}

	public String getNoDataMSG() {
		noDataMSG=Constants.NO_DATA_MSG;
		return noDataMSG;
	}

	public void setNoDataMSG(String noDataMSG) {
		this.noDataMSG = noDataMSG;
	}
   
	


}
