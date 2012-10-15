package wisoft.pack.models;

public interface IDeltaListener {
	public abstract void add(DeltaEvent paramDeltaEvent);
	
	public abstract void remove(DeltaEvent paramDeltaEvent);
}
