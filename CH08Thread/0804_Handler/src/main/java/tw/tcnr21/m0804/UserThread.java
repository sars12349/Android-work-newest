package tw.tcnr21.m0804;

public class UserThread extends Thread{
    M0804 activity;
    private int what=1;


    public UserThread(M0804 activity){
    this.activity=activity;
}
    @Override
    public void run() {
        super.run();
        while (true){
            activity.myHandler.sendEmptyMessage((what++)%4);
            try {
                Thread.sleep(3000);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
