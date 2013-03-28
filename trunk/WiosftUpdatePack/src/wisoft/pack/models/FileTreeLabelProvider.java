package wisoft.pack.models;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

public class FileTreeLabelProvider implements ILabelProvider {
	  // The listeners
	  private List listeners;

	  /**
	   * Constructs a FileTreeLabelProvider
	   */
	  public FileTreeLabelProvider() {
	    // Create the list to hold the listeners
	    listeners = new ArrayList();
	  }


	  /**
	   * Gets the image to display for a node in the tree
	   * 
	   * @param arg0
	   *            the node
	   * @return Image
	   */
	  public Image getImage(Object arg0) {
	    // If the node represents a directory, return the directory image.
	    // Otherwise, return the file image.
	    return null;
	  }

	  /**
	   * Gets the text to display for a node in the tree
	   * 
	   * @param arg0
	   *            the node
	   * @return String
	   */
	  public String getText(Object arg0) {
	    // Get the name of the file
	    String text = ((File) arg0).getName();

	    // If name is blank, get the path
	    if (text.length() == 0) {
	      text = ((File) arg0).getPath();
	    }

	    // Check the case settings before returning the text
	    return text;
	  }


	  /**
	   * Called when this LabelProvider is being disposed
	   */
	  public void dispose() {
	    // Dispose the images
	  }

	  /**
	   * Returns whether changes to the specified property on the specified
	   * element would affect the label for the element
	   * 
	   * @param arg0
	   *            the element
	   * @param arg1
	   *            the property
	   * @return boolean
	   */
	  public boolean isLabelProperty(Object arg0, String arg1) {
	    return false;
	  }


	@Override
	public void addListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeListener(ILabelProviderListener listener) {
		// TODO Auto-generated method stub
		
	}

	}