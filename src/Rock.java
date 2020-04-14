

public class Rock extends Tile{
    public Rock(int id) {
        super(Assets.stone, id);
    }
    @Override
    public boolean isSolid(){
        return true;
    }
}
