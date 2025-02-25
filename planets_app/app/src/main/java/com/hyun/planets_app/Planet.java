package com.hyun.planets_app;

public class Planet {
    private String planetName;
    private String moonCount;
    // 리소스 식별자로 앱 내의 이미지 및 기타 리소스를 효율적으로 관리하기 위한 값
    private int planetImage;

    public Planet(String planetName, String moonCount, int planetImage) {
        this.planetImage = planetImage;
        this.moonCount = moonCount;
        this.planetName = planetName;
    }

    public String getPlanetName() {
        return planetName;
    }

    public String getMoonCount() {
        return moonCount;
    }

    public int getPlanetImage() {
        return planetImage;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public void setMoonCount(String moonCount) {
        this.moonCount = moonCount;
    }

    public void setPlanetImage(int planetImage) {
        this.planetImage = planetImage;
    }
}
