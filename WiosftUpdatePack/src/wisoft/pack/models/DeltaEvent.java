package wisoft.pack.models;

public class DeltaEvent {
	 protected Object actedUpon;

	 public DeltaEvent(Object receiver)
	 {
		 this.actedUpon = receiver;
	 }

	 public Object receiver() {
		 return this.actedUpon;
	 }
}
