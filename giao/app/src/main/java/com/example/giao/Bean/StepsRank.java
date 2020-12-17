package com.example.giao.Bean;

import java.util.List;

public class StepsRank {
    private String username;
    private int step;
    private String head_image;
    private int rank;
    List<UserRank> others;

    public List<UserRank> getOthers() {
        return others;
    }

    public void setOthers(List<UserRank> others) {
        this.others = others;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getHead_image() {
        return head_image;
    }

    public void setHead_image(String head_image) {
        this.head_image = head_image;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
