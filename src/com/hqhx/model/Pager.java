package com.hqhx.model;

import java.util.List;

public class Pager<T> {

	private Integer currentPage=1;//当前页
	private Integer offSet;//偏移量
	private Integer pageSize=3;//每页显示条数
	private List<T> datas;//当前页的数据
	private Long totalCount;//总条数
	private Integer totalPage;//总页数
	private Integer previousPage;//上一页
	private Integer nextPage;//下一页
	private Integer startPage;//开始页码
	private Integer endPage;//结束页码
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	//计算并返回偏移量
	public Integer getOffSet() {
		offSet=(getCurrentPage()-1)*getPageSize();
		return offSet;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getDatas() {
		return datas;
	}
	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
	//获取总条数
	public Long getTotalCount() {
		return totalCount;
	}
	//设置总条数
	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
	}
	//获取总页数
	public Integer getTotalPage() {
		totalPage=(int) Math.ceil(getTotalCount()*1.0/getPageSize());
		return totalPage;
	}
	
	
	
	
	
	
	//获取上一页
	public Integer getPreviousPage() {
		//是否有上一页
		if(hasPreviousPage()){
			previousPage=getCurrentPage()-1;
		}else{
			previousPage=1;
		}
		return previousPage;
	}
	
	
	//获取下一页
	public Integer getNextPage() {
		//是否有下一页
		if(hasNextPage()){
			nextPage=getCurrentPage()+1;
		}else{
			nextPage=getTotalPage();
		}
		return nextPage;
	}
	
	//判断是否有上一页
	public boolean hasPreviousPage(){
		if(getCurrentPage()-1>0){
			return true;
		}else{
			return false;
		}
	}
	

	//判断是否有下一页
	public boolean hasNextPage(){
		if(getCurrentPage()+1>getTotalPage()){
			return false;
		}else{
			return true;
		}
	}
	//计算开始页码
	public Integer getStartPage() {
		if(getCurrentPage()>=1&&getCurrentPage()<=5){
			startPage=1;
		}else if(getCurrentPage()>=getTotalPage()-4&&getCurrentPage()<=getTotalPage()){
			startPage=getTotalPage()-4;
		}else{
			startPage=getCurrentPage()-2;
		}
		return startPage;
	}
	//计算结束页码
	public Integer getEndPage() {
		if(getCurrentPage()>=1&&getCurrentPage()<=5){
			if(getTotalPage()>5){
				endPage=5;
			}else{
				endPage=getTotalPage();
			}
		}else if(getCurrentPage()>=getTotalPage()-4&&getCurrentPage()<=getTotalPage()){
			endPage=getTotalPage();
		}else{
			endPage=getCurrentPage()+2;
		}
		return endPage;
	}	
}
