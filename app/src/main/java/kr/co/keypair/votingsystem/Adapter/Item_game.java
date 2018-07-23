package kr.co.keypair.votingsystem.Adapter;

public class Item_game {
    private String country1;
    private String country2;
    private String times;
    int image1;
    int image2;


    public Item_game(int image1, int image2, String country1, String country2, String times){
        this.image1= image1;
        this.image2= image2;
        this.country1 = country1;
        this.country2 = country2;
        this.times = times;
    }

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

}
