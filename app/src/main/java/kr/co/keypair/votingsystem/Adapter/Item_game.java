package kr.co.keypair.votingsystem.Adapter;

public class Item_game {
    private String country1;
    private String country2;
    private String frag1;
    private String frag2;
    private String times;
    int image1;
    int image2;

    public int getImage1(){ return image1; }
    public int getImage2(){ return image2; }

    public String getName1() {
        return country1;
    }
    public String getName2() {
        return country2;
    }

    public String getTimes() {
        return times;
    }

    public void setImage1(int image1){ this.image1= image1; }
    public void setImage2(int image2){ this.image2= image2; }

    public void setName1(String country1) { this.country1 = country1; }
    public void setName2(String country2) {
        this.country2 = country2;
    }

    public void setTimes(String times) {
        this.times= times;
    }

    public String getFra1() { return frag1;}
    public void setFra1(String frag1) {this.frag1 = frag1;}

    public String getFra2() { return frag2;}
    public void setFra2(String frag2) {this.frag2 = frag2;}

}
