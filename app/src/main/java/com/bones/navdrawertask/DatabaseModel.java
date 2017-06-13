package com.bones.navdrawertask;

/**
 * Created by lenovo ip on 13/06/2017.
 */

public class DatabaseModel {

    private String incomeTitle;
    private String outcomeTitle;
    private int incomeAmount;
    private int outcomeAmount;

    public String getIncomeTitle() {
        return incomeTitle;
    }

    public void setIncomeTitle(String incomeTitle) {
        this.incomeTitle = incomeTitle;
    }

    public String getOutcomeTitle() {
        return outcomeTitle;
    }

    public void setOutcomeTitle(String outcomeTitle) {
        this.outcomeTitle = outcomeTitle;
    }

    public int getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(int incomeAmount) {
        this.incomeAmount = incomeAmount;
    }

    public int getOutcomeAmount() {
        return outcomeAmount;
    }

    public void setOutcomeAmount(int outcomeAmount) {
        this.outcomeAmount = outcomeAmount;
    }
}
