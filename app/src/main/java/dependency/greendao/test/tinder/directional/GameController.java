package dependency.greendao.test.tinder.directional;

public class GameController {

    static void setAmbiance(String ambianceName) {
        SoundController soundController = new SoundController();
        soundController.initialize(this);
        //soundController.playOneShot("ambianceName",0 );
    }
}
