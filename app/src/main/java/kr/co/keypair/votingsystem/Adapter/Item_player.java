package kr.co.keypair.votingsystem.Adapter;


public class Item_player {
    private String player_name;
    private String player_position;
    private int player_num;

    public String getPosition(){
        return player_position;
    }
    public void setPosition(String player_position) {
        this.player_position = player_position;
    }

    public String getName() {return player_name; }
    public void setName(String player_name) {
        this.player_name = player_name;
    }

    public int getNum() { return player_num;}
    public void setNum(int player_num){this.player_num=player_num;}

}
