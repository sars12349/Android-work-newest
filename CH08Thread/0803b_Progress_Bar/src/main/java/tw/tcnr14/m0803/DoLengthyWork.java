package tw.tcnr14.m0803;


import android.os.Handler;
import android.util.Log;

import java.util.Calendar;

public class DoLengthyWork extends Thread {
    public interface IProgressPresenter {
        void updateProgress(int progress, int secondProgress);
        void finish();
    }

    private String logPrompt = "M0803 =====> ";
    private IProgressPresenter presenter;
    private Handler handler = new Handler();
    private boolean isRunning = false;

    private DoLengthyWork() {
    }

    public DoLengthyWork(IProgressPresenter presenter) {
        setPresenter(presenter);
    }

    protected void setPresenter(IProgressPresenter presenter) {
        this.presenter = presenter;
    }

    public void run() {
        Calendar begin = Calendar.getInstance();
        isRunning = true;
        int lastSec = 0;
        updateProgress(0,0);

        do {
            Calendar now = Calendar.getInstance();
            int iDiffSec = 60 * (now.get(Calendar.MINUTE) - begin.get(Calendar.MINUTE))
                    + now.get(Calendar.SECOND) - begin.get(Calendar.SECOND);
            if (lastSec != iDiffSec) {
                lastSec = iDiffSec;

                if (iDiffSec * 5 > 100) {
                    updateProgress(100, 100);
                    end();
                } else if (iDiffSec * 7 > 100) {
                    updateProgress(iDiffSec * 5, 100);
                } else {
                    updateProgress(iDiffSec * 5, iDiffSec * 7);
                }
            }
        }
        while (isRunning);
        finish();
        Log.d(logPrompt, "end");
    }

    public void updateProgress(final int progress, final int secondProgress) {
        handler.post(new Runnable() {
            public void run() {
                if (presenter != null) {
                    presenter.updateProgress(progress, secondProgress);
                }
            }
        });
    }
    public void finish() {
        handler.post(new Runnable() {
            public void run() {
                if (presenter != null) {
                    presenter.finish();
                }
            }
        });
    }

    public void end() {
        isRunning = false;
    }

    public boolean isRunning() {
        return isRunning;
    }
}
