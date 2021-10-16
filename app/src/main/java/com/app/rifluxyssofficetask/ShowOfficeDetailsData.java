package com.app.rifluxyssofficetask;

public class ShowOfficeDetailsData {

    private final boolean showOfficeData;

    private final boolean stateChecked;

    public ShowOfficeDetailsData(boolean showOfficeData,boolean stateChecked) {
        this.showOfficeData = showOfficeData;
        this.stateChecked = stateChecked;
    }

    public boolean isStateChecked() { return stateChecked; }

    public boolean isShowOfficeData() { return showOfficeData; }

}
