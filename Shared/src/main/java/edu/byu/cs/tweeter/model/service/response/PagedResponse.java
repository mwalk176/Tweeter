package edu.byu.cs.tweeter.model.service.response;

/**
 * A response that can indicate whether there is more data available from the server.
 */
public class PagedResponse extends Response {

    private boolean morePages;

    public PagedResponse() {
    }

    PagedResponse(boolean success, boolean morePages) {
        super(success);
        this.morePages = morePages;
    }

    PagedResponse(boolean success, String message, boolean morePages) {
        super(success, message);
        this.morePages = morePages;
    }

    public boolean isMorePages() {
        return morePages;
    }

    public void setMorePages(boolean morePages) {
        this.morePages = morePages;
    }

    @Override
    public String toString() {
        return "PagedResponse{" +
                "hasMorePages=" + morePages +
                '}';
    }
}
