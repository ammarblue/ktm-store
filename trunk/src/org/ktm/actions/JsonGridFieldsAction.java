package org.ktm.actions;


public abstract class JsonGridFieldsAction extends CrudAction implements GridField {

    private static final long serialVersionUID = 1L;

    protected Integer         id;

    // get how many rows we want to have into the grid - rowNum attribute in the
    // grid
    protected Integer         rows             = 0;

    // Get the requested page. By default grid sets this to 1.
    protected Integer         page             = 0;

    // sorting order - asc or desc
    protected String          sord;

    // get index row - i.e. user click to sort.
    protected String          sidx;

    // Search Field
    protected String          searchField;

    // The Search String
    protected String          searchString;

    // Limit the result when using local data, value form attribute rowTotal
    protected Integer         totalrows;

    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    protected String          searchOper;

    // Your Total Pages
    protected Integer         total            = 0;

    // All Records
    protected Integer         records          = 0;

    protected boolean         loadonce         = false;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public Integer getTotalrows() {
        return totalrows;
    }

    public void setTotalrows(Integer totalrows) {
        this.totalrows = totalrows;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;

        if (this.records > 0 && this.rows > 0) {
            this.total = (int) Math.ceil((double) this.records / (double) this.rows);
        } else {
            this.total = 0;
        }
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
