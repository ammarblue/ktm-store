package org.ktm.actions;

public interface GridField {

    // get how many rows we want to have into the grid - rowNum attribute in the
    // grid
    public Integer getRows();

    public void setRows(Integer rows);

    // Get the requested page. By default grid sets this to 1.
    public Integer getPage();

    public void setPage(Integer page);

    // sorting order - asc or desc
    public String getSord();

    public void setSord(String sord);

    // get index row - i.e. user click to sort.
    public String getSidx();

    public void setSidx(String sidx);

    // Search Field
    public String getSearchField();

    public void setSearchField(String searchField);

    // The Search String
    public String getSearchString();

    public void setSearchString(String searchString);

    // Limit the result when using local data, value form attribute rowTotal
    public Integer getTotalrows();

    public void setTotalrows(Integer totalrows);

    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    public String getSearchOper();

    public void setSearchOper(String searchOper);

    // Your Total Pages
    public Integer getTotal();

    public void setTotal(Integer total);

    // All Records
    public Integer getRecords();

    public void setRecords(Integer records);

    public boolean isLoadonce();

    public void setLoadonce(boolean loadonce);

    public Integer getId();

    public void setId(Integer id);

}
