import org.eclipse.swt.graphics.Point; 
import org.eclipse.swt.graphics.Rectangle; 
import org.eclipse.swt.printing.Printer; 
public class PrintMargin { 

public int left; 
public int right; 
public int top; 
public int bottom; 
private PrintMargin(int left, int right, int top, int bottom) { 
this.left = left; 
this.right = right; 
this.top = top; 
this.bottom = bottom; 
} 
static PrintMargin getPrintMargin(Printer printer, double margin) { 
return getPrintMargin(printer, margin, margin, margin, margin); 
} 
static PrintMargin getPrintMargin( 
Printer printer, 
double marginLeft, 
double marginRight, 
double marginTop, 
double marginBottom) { 
Rectangle clientArea = printer.getClientArea(); 
Rectangle trim = printer.computeTrim(0, 0, 0, 0); 
Point dpi = printer.getDPI(); 
int leftMargin = (int) (marginLeft * dpi.x) - trim.x; 
int rightMargin = 
clientArea.width 
+ trim.width 
- (int) (marginRight * dpi.x) 
- trim.x; 
int topMargin = (int) (marginTop * dpi.y) - trim.y; 
int bottomMargin = 
clientArea.height 
+ trim.height 
- (int) (marginBottom * dpi.y) 
- trim.y; 
return new PrintMargin( 
leftMargin, 
rightMargin, 
topMargin, 
bottomMargin); 
} 
public String toString() { 
return "Margin { " 
+ left 
+ ", " 
+ right 
+ "; " 
+ top 
+ ", " 
+ bottom 
+ " }"; 
} 
}