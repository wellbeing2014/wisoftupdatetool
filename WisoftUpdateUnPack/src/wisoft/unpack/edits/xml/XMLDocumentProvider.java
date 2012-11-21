package wisoft.unpack.edits.xml;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentExtension3;
import org.eclipse.jface.text.IDocumentPartitioner;
import org.eclipse.jface.text.rules.FastPartitioner;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.texteditor.AbstractDocumentProvider;

public class XMLDocumentProvider extends AbstractDocumentProvider {
	
    protected void setupDocument(IDocument document) {
		if (document instanceof IDocumentExtension3) {
			IDocumentPartitioner partitioner =
				new FastPartitioner(
					new XMLPartitionScanner(),
					new String[] {
						XMLPartitionScanner.XML_TAG,
						XMLPartitionScanner.XML_COMMENT });
			partitioner.connect(document);
			((IDocumentExtension3) document).setDocumentPartitioner(XMLPartitionScanner.XML_PARTITIONING, partitioner);
		}
	}

	@Override
    protected IDocument createDocument(Object element) throws CoreException {
		if (element instanceof IEditorInput) {
			IDocument document= new Document();
			if (setDocumentContent(document, (IEditorInput) element)) {
				setupDocument(document);
			}
			return document;
		}
		return null;
	}

	private boolean setDocumentContent(IDocument document, IEditorInput input) throws CoreException {
		// XXX handle encoding
		Reader reader;
		try {
			if (input instanceof IPathEditorInput)
				reader= new FileReader(((IPathEditorInput)input).getPath().toFile());
			else
				return false;
		} catch (FileNotFoundException e) {
			// return empty document and save later
			return true;
		}
		
		try {
			setDocumentContent(document, reader);
			return true;
		} catch (IOException e) {
			throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.ui.examples.rcp.texteditor", IStatus.OK, "error reading file", e)); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
	
	private void setDocumentContent(IDocument document, Reader reader) throws IOException {
		Reader in= new BufferedReader(reader);
		try {
			StringBuffer buffer= new StringBuffer(512);
			char[] readBuffer= new char[512];
			int n= in.read(readBuffer);
			while (n > 0) {
				buffer.append(readBuffer, 0, n);
				n= in.read(readBuffer);
			}
			document.set(buffer.toString());
		} finally {
			in.close();
		}
	}
	
	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element,
			IDocument document, boolean overwrite) throws CoreException {
		// TODO Auto-generated method stub
		if (element instanceof IPathEditorInput) {
			IPathEditorInput pei= (IPathEditorInput) element;
			IPath path= pei.getPath();
			File file= path.toFile();
			
			try {
				file.createNewFile();

				if (file.exists()) {
					if (file.canWrite()) {
						Writer writer= new FileWriter(file);
						writeDocumentContent(document, writer, monitor);
					} else {
						// XXX prompt to SaveAs
						throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.ui.examples.rcp.texteditor", IStatus.OK, "file is read-only", null)); //$NON-NLS-1$ //$NON-NLS-2$
					}
				} else {
					throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.ui.examples.rcp.texteditor", IStatus.OK, "error creating file", null)); //$NON-NLS-1$ //$NON-NLS-2$
				}
			} catch (IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, "org.eclipse.ui.examples.rcp.texteditor", IStatus.OK, "error when saving file", e)); //$NON-NLS-1$ //$NON-NLS-2$
			}

		}
		
	}
	
	private void writeDocumentContent(IDocument document, Writer writer, IProgressMonitor monitor) throws IOException {
		Writer out= new BufferedWriter(writer);
		try {
			out.write(document.get());
		} finally {
			out.close();
		}
	}
	@Override
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
    public boolean isModifiable(Object element) {
		File file= getFile(element);
		if (file == null)
			return super.isModifiable(element);
		return file.canWrite() || !file.exists();
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.IDocumentProviderExtension#isReadOnly(java.lang.Object)
	 */
	@Override
    public boolean isReadOnly(Object element) {
		return !isModifiable(element);
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.IDocumentProviderExtension#isStateValidated(java.lang.Object)
	 */
	@Override
    public boolean isStateValidated(Object element) {
		return true;
	}

	/*
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#isDeleted(java.lang.Object)
	 */
	@Override
    public boolean isDeleted(Object element) {
		File file= getFile(element);
		if (file == null)
			return super.isDeleted(element);
		return !file.exists();
	}
	
	/*
	 * @see org.eclipse.ui.texteditor.AbstractDocumentProvider#getModificationStamp(java.lang.Object)
	 */
	@Override
    public long getModificationStamp(Object element) {
		File file= getFile(element);
		if (file == null)
			return super.getModificationStamp(element);
		return file.lastModified();
	}
	
	/**
	 * Returns the file corresponding to the input element, <code>null</code> if
	 * <code>element</code> is not an {@link IPathEditorInput}.
	 * 
	 * @param element the input element
	 * @return the file corresponding to <code>element</code>
	 */
	protected final File getFile(Object element) {
		if (element instanceof IPathEditorInput) {
			IPathEditorInput pei= (IPathEditorInput) element;
			File file= pei.getPath().toFile();
			return file;
		}
		return null; // FIXME return dummy file
	}

	@Override
	protected IAnnotationModel createAnnotationModel(Object element)
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}
}