package wisoft.pack.edits.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.eclipse.core.runtime.CoreException;
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

public class SQLDocumentProvider extends AbstractDocumentProvider {

	 protected void setupDocument(IDocument document) {
			if (document instanceof IDocumentExtension3) {
				IDocumentPartitioner partitioner =
					new FastPartitioner(
						new SQLPartitionScanner(),
						new String[] { SQLPartitionScanner.SQL_KEYWORD, SQLPartitionScanner.SQL_COMMENT });
				partitioner.connect(document);
				document.setDocumentPartitioner(partitioner);
			}
		}
	@Override
	protected IDocument createDocument(Object element) throws CoreException {
		// TODO Auto-generated method stub
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
		InputStreamReader reader;
		try {
			if (input instanceof IPathEditorInput)
				reader= new InputStreamReader(new FileInputStream(((IPathEditorInput)input).getPath().toFile()),"UTF-8");
			else
				return false;
		} catch (FileNotFoundException e) {
			// return empty document and save later
			return true;
		}
		catch (UnsupportedEncodingException e) {
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
	protected final File getFile(Object element) {
		if (element instanceof IPathEditorInput) {
			IPathEditorInput pei= (IPathEditorInput) element;
			File file= pei.getPath().toFile();
			return file;
		}
		return null; // FIXME return dummy file
	}
	@Override
    public boolean isModifiable(Object element) {
		return true;
	}
	@Override
	protected IAnnotationModel createAnnotationModel(Object element)
			throws CoreException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doSaveDocument(IProgressMonitor monitor, Object element,
			IDocument document, boolean overwrite) throws CoreException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected IRunnableContext getOperationRunner(IProgressMonitor monitor) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
