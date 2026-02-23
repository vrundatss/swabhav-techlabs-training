package com.tss.SOLID.OCP.FixDepositSystem.model;

public class FixedDeposit {
    private Integer id;
    private String holderName;
    private Double amount;
    private Integer duration;
    private FestivalOffer festivalOffer;

    public FixedDeposit(Integer id, String holderName, Double amount, Integer duration  , FestivalOffer festivalOffer) {
        this.id = id;
        this.holderName = holderName;
        this.amount = amount;
        this.duration = duration;
        this.festivalOffer = festivalOffer;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public FestivalOffer getFestivalOffer() {
        return festivalOffer;
    }

    public void setFestivalOffer(FestivalOffer festivalOffer) {
        this.festivalOffer = festivalOffer;
    }
}
