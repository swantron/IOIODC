package swantron.project.ioiodc;

//import ioio.lib.api.AnalogInput;
import ioio.lib.api.DigitalOutput;
//import ioio.lib.api.IOIO;
//import ioio.lib.api.PwmOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.AbstractIOIOActivity;
import android.os.Bundle;
//import android.widget.TextView;
import android.widget.ToggleButton;

public class IOIODC extends AbstractIOIOActivity {
	private ToggleButton toggleButton1_;
	private ToggleButton toggleButton2_;
	private ToggleButton toggleButton3_;
	private ToggleButton toggleButton4_;
	private ToggleButton toggleButton5_;
	private ToggleButton toggleButton6_;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        toggleButton1_ = (ToggleButton)findViewById(R.id.ToggleButton1);
        toggleButton2_ = (ToggleButton)findViewById(R.id.ToggleButton2);
        toggleButton3_ = (ToggleButton)findViewById(R.id.ToggleButton3);
        toggleButton4_ = (ToggleButton)findViewById(R.id.ToggleButton4);
        toggleButton5_ = (ToggleButton)findViewById(R.id.ToggleButton5);
        toggleButton6_ = (ToggleButton)findViewById(R.id.ToggleButton6);
              
        
        enableUi(false);
    }
	
	class IOIOThread extends AbstractIOIOActivity.IOIOThread {
		private DigitalOutput digi1_;
		private DigitalOutput digi2_;
		private DigitalOutput digi3_;
		private DigitalOutput digi4_;
		private DigitalOutput digi5_;
		private DigitalOutput digi6_;
		
		public void setup() throws ConnectionLostException {
			try {
				digi1_ = ioio_.openDigitalOutput(21, false);
				digi2_ = ioio_.openDigitalOutput(22, false);
				digi3_ = ioio_.openDigitalOutput(23, false);
				digi4_ = ioio_.openDigitalOutput(24, false);
				digi5_ = ioio_.openDigitalOutput(25, false);
				digi6_ = ioio_.openDigitalOutput(26, false);
				
				enableUi(true);
			} catch (ConnectionLostException e) {
				enableUi(false);
				throw e;
			}
		}
		
		public void loop() throws ConnectionLostException {
			try {
							
				digi1_.write(!toggleButton1_.isChecked());
				digi2_.write(!toggleButton2_.isChecked());
				digi3_.write(!toggleButton3_.isChecked());
				digi4_.write(!toggleButton4_.isChecked());
				digi5_.write(!toggleButton5_.isChecked());
				digi6_.write(!toggleButton6_.isChecked());
				
				sleep(10);
			} catch (InterruptedException e) {
				ioio_.disconnect();
			} catch (ConnectionLostException e) {
				enableUi(false);
				throw e;
			}
		}
	}

	@Override
	protected AbstractIOIOActivity.IOIOThread createIOIOThread() {
		return new IOIOThread();
	}

	private void enableUi(final boolean enable) {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				toggleButton1_.setEnabled(enable);
				toggleButton2_.setEnabled(enable);
				toggleButton3_.setEnabled(enable);
				toggleButton4_.setEnabled(enable);
				toggleButton5_.setEnabled(enable);
				toggleButton6_.setEnabled(enable);
				
			}
		});
	}
	
}
