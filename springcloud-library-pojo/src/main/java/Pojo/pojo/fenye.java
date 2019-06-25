package Pojo.pojo;

public class fenye {
    //当前页码
    private int currentPageNo;
    //总数
    private int totalCount;
    //页面容量
    private int pageSize;
    //总页
    private int totalPageCount;
    public int getCurrentPageNo() {
        return currentPageNo;
    }
    public void setCurrentPageNo(int currentPageNo) {
        if (currentPageNo<1) {
            currentPageNo=1;
        }
        this.currentPageNo = currentPageNo;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        if(totalCount>0){
            if (totalCount<this.pageSize) {
                this.totalPageCount=1;
            }else {
                this.totalPageCount=totalCount%pageSize==0?(this.totalCount/this.pageSize):(this.totalCount/this.pageSize)+1;
            }
        }
    }
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public int getTotalPageCount() {
        return totalPageCount;
    }
    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }
}
