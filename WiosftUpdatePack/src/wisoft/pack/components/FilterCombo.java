package wisoft.pack.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleControlAdapter;
import org.eclipse.swt.accessibility.AccessibleControlEvent;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;



public final class FilterCombo extends Composite {
 private boolean hasFocus;
 private Shell popup;
 private Text text;
 private List list;
 /**
  * @param arrays是一个二级数据
  *            ，如new String[][2]
  */
 private HashMap<String , Object> arrays;
 private static final int len = 180;
 private int width = 120;
 private int height = 18;
 // private String key;
 private String value;
 private boolean isActivate;

 public FilterCombo(Composite parent, int style) {
  super(parent, checkStyle(style));

  style = getStyle();

  int textStyle = SWT.BORDER;

  if ((style & SWT.FLAT) != 0) {
   textStyle |= SWT.FLAT;
  }

  text = new Text(this, textStyle);
  text.setSize(width, height);
  text.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));

  popup = new Shell(getShell(), SWT.NO_TRIM);

  int pickerStyle = SWT.SINGLE | SWT.V_SCROLL;// | SWT.H_SCROLL

  if ((style & SWT.FLAT) != 0) {
   pickerStyle |= SWT.FLAT;
  }

  list = new List(popup, pickerStyle);

  int arrowStyle = SWT.ARROW | SWT.DOWN;

  if ((style & SWT.FLAT) != 0) {
   arrowStyle |= SWT.FLAT;
  }

  Listener listener = new Listener() {
   public void handleEvent(Event event) {
    if (popup == event.widget) {
     popupEvent(event);

     return;
    }

    if (text == event.widget) {
     textEvent(event);

     return;
    }

    if (list == event.widget) {
     listEvent(event);
     return;
    }

    if (FilterCombo.this == event.widget) {
     comboEvent(event);

     return;
    }
   }
  };

  int[] comboEvents = { SWT.Dispose, SWT.Move, SWT.Resize };

  for (int i = 0; i < comboEvents.length; i++)
   this.addListener(comboEvents[i], listener);

  int[] popupEvents = { SWT.Close, SWT.Paint, SWT.Deactivate,
    SWT.Activate };

  for (int i = 0; i < popupEvents.length; i++)
   popup.addListener(popupEvents[i], listener);

  int[] textEvents = { SWT.KeyDown, SWT.KeyUp, SWT.Modify, SWT.MouseDown,
    SWT.MouseUp, SWT.Traverse, SWT.FocusIn, SWT.FocusOut,
    SWT.Modify };

  for (int i = 0; i < textEvents.length; i++)
   text.addListener(textEvents[i], listener);

  int[] listEvents = { SWT.MouseDoubleClick, SWT.Selection, SWT.Traverse,
    SWT.MouseDown, SWT.MouseDown, SWT.Resize };

  for (int i = 0; i < listEvents.length; i++)
   list.addListener(listEvents[i], listener);

  initAccessible();
  initList();
  //SWTUtil.setCompositeBackground(popup);
 }

 //========================================事件处理开始========================================================== 
 
 //本窗体事件
 private void comboEvent(Event event) {
  switch (event.type) {
  case SWT.Dispose:
   if ((popup != null) && !popup.isDisposed()) {
    popup.dispose();
   }
   popup = null;
   text = null;
   list = null;
   break;
  case SWT.Move:
   dropDown(false);
   break;
  case SWT.Resize:
   internalLayout();
   break;
  case SWT.FocusIn:
   Control focusControl = getDisplay().getFocusControl();
   if (focusControl == list)
    return;
   if (isDropped()) {
    list.setFocus();
   } else {
    text.setFocus();
   }
   break;
  }
 }

 /**
  * text事件
  * @param event
  */
 private void textEvent(Event event) {
  switch (event.type) {

  case SWT.FocusIn: {
   if (hasFocus) {
    return;
   }
   hasFocus = true;
   String value = text.getText();
   if (value.length() > 0)
    text.setSelection(value.length());
   Event e = new Event();
   e.time = event.time;
   notifyListeners(SWT.FocusIn, e);
   break;
  }
  case SWT.FocusOut: {
   hasFocus = false;
   if (!isActivate)
    dropDown(false);
   Event e = new Event();
   e.time = event.time;
   notifyListeners(SWT.FocusOut, e);
   break;
  }
  case SWT.KeyDown: {
   if (event.character == SWT.ESC) {
    dropDown(false);
   }
   // 回车
   if (event.character == SWT.CR) {
    dropDown(false);
    Event e = new Event();
    e.time = event.time;
    e.stateMask = event.stateMask;
    notifyListeners(SWT.DefaultSelection, e);
   }
   if (isDisposed()) {
    break;
   }
   if ((event.keyCode == SWT.ARROW_UP)
     || (event.keyCode == SWT.ARROW_DOWN)) {
    if (isDisposed()) {
     break;
    }
   }
   Event e = new Event();
   e.time = event.time;
   e.character = event.character;
   e.keyCode = event.keyCode;
   e.stateMask = event.stateMask;
   notifyListeners(SWT.KeyDown, e);

   break;
  }
  case SWT.KeyUp: {
   if (event.keyCode == SWT.ARROW_DOWN) {
    if (isDisposed()) {
     break;
    }
   }
   Event e = new Event();
   e.time = event.time;
   e.character = event.character;
   e.keyCode = event.keyCode;
   e.stateMask = event.stateMask;
   notifyListeners(SWT.KeyUp, e);

   break;
  }
  case SWT.Modify: {
   String value = text.getText();
   if (value == null || "".equals(value)) {
    initList();
   } else {
    setList(value);
   }
   if (!popup.isDisposed() && !popup.isVisible())
    popup.setVisible(true);
   Event e = new Event();
   e.time = event.time;
   notifyListeners(SWT.Modify, e);

   break;
  }

  case SWT.MouseDown: {
   boolean dropped = isDropped();
   if (!dropped) {
    setFocus();
   }
   dropDown(!dropped);
   break;
  }
  case SWT.MouseUp: {
   if ((event.button != 1) || text.getEditable()) {
    return;
   }
   //text.selectAll();
   break;
  }
  case SWT.Traverse: {
   switch (event.detail) {
   case SWT.TRAVERSE_RETURN:
   case SWT.TRAVERSE_ARROW_PREVIOUS:
   case SWT.TRAVERSE_ARROW_NEXT:
    event.doit = false;
    break;
   }

   Event e = new Event();
   e.time = event.time;
   e.detail = event.detail;
   e.doit = event.doit;
   e.keyCode = event.keyCode;
   notifyListeners(SWT.Traverse, e);
   event.doit = e.doit;

   break;
  }
  }
 }

 /**
  * list事件处理
  * 
  * @param event
  */
 private void listEvent(Event event) {
  switch (event.type) {
  case SWT.Dispose:
   if (getShell() != popup.getParent()) {
    popup = null;
    list = null;
   }
   break;
  case SWT.MouseUp: {
   if (event.button != 1)
    return;
   dropDown(false);
   break;
  }
  case SWT.MouseDoubleClick: {
   int index = list.getSelectionIndex();
   if (index == -1)
    return;
   text.setText(list.getItem(index));
   value = list.getData(list.getItem(index)).toString();
   // text.selectAll();
   list.setSelection(index);
   Event e = new Event();
   e.time = event.time;
   e.stateMask = event.stateMask;
   e.doit = event.doit;
   notifyListeners(SWT.Selection, e);
   event.doit = e.doit;
   popup.setVisible(false);
   break;
  }
  case SWT.Selection: {
   int index = list.getSelectionIndex();
   if (index == -1)
    return;
   text.setText(list.getItem(index));
   value = list.getData(list.getItem(index)).toString();
   // text.selectAll();
   // list.setSelection(index);
   dropDown(false);
   Event e = new Event();
   e.time = event.time;
   e.stateMask = event.stateMask;
   e.doit = event.doit;
   notifyListeners(SWT.Selection, e);
   event.doit = e.doit;
   break;
  }
  case SWT.Traverse: {
   switch (event.detail) {
   case SWT.TRAVERSE_RETURN:
   case SWT.TRAVERSE_ESCAPE:
   case SWT.TRAVERSE_ARROW_PREVIOUS:
   case SWT.TRAVERSE_ARROW_NEXT:
    event.doit = false;
    break;
   case SWT.TRAVERSE_TAB_NEXT:
   case SWT.TRAVERSE_TAB_PREVIOUS:
    event.doit = text.traverse(event.detail);
    event.detail = SWT.TRAVERSE_NONE;
    if (event.doit)
     dropDown(false);
    return;
   }
   Event e = new Event();
   e.time = event.time;
   e.detail = event.detail;
   e.doit = event.doit;
   e.character = event.character;
   e.keyCode = event.keyCode;
   notifyListeners(SWT.Traverse, e);
   event.doit = e.doit;
   event.detail = e.detail;
   break;
  }
  case SWT.KeyUp: {
   Event e = new Event();
   e.time = event.time;
   e.character = event.character;
   e.keyCode = event.keyCode;
   e.stateMask = event.stateMask;
   notifyListeners(SWT.KeyUp, e);
   break;
  }
  case SWT.KeyDown: {
   if (event.character == SWT.ESC) {
    // Escape key cancels popup list
    dropDown(false);
   }
   if ((event.stateMask & SWT.ALT) != 0
     && (event.keyCode == SWT.ARROW_UP || event.keyCode == SWT.ARROW_DOWN)) {
    dropDown(false);
   }
   if (event.character == SWT.CR) {
    // Enter causes default selection
    dropDown(false);

    if (list.getSelectionIndex() >= 0) {
     text.setText(list.getItem(list.getSelectionIndex()));
    }

    Event e = new Event();
    e.time = event.time;
    e.stateMask = event.stateMask;
    notifyListeners(SWT.DefaultSelection, e);
   }
   if (isDisposed())
    break;
   Event e = new Event();
   e.time = event.time;
   e.character = event.character;
   e.keyCode = event.keyCode;
   e.stateMask = event.stateMask;
   notifyListeners(SWT.KeyDown, e);
   break;

  }
  case SWT.Resize: {
   layoutList();
  }
  }
 }

 /**
  * 弹出面版事件处理
  * 
  * @param event
  */
 private void popupEvent(Event event) {
  switch (event.type) {
  case SWT.Paint:{
   Rectangle txtRect = text.getBounds();
   Rectangle listRect = new Rectangle(txtRect.x, +txtRect.y
     + txtRect.height, txtRect.width, len);
   Color black = getDisplay().getSystemColor(SWT.COLOR_BLACK);
   event.gc.setForeground(black);
   event.gc.drawRectangle(0, 0, listRect.width, listRect.height);
   // Rectangle bound = list.getBounds();
   // Rectangle rect = list.getBounds();
   // Rectangle rect0 = new Rectangle(rect.x, rect.y, 300, len);
   // list.setBounds(rect0);
   layoutList();

   break;
  }
  case SWT.Close:{
   event.doit = false;
   dropDown(false);

   break;
  }
  case SWT.Deactivate:{// 使用效事件
   isActivate = false;
   dropDown(false);
   break;
  }
  case SWT.Activate:{
   isActivate = true;
   break;
  }
  }
 }

 
//========================================事件处理完========================================================== 
 
 /**
  * 初始化text,list
  */
 private void internalLayout() {
  if (isDropped()) {
   dropDown(false);
  }
  text.setBounds(0, 0, width, height);
  layoutList();
 }

 /**
  * 重新布局list
  */
 private void layoutList() {
  Point size = getSize();
  // Point listSize = list.computeSize(SWT.DEFAULT, SWT.DEFAULT);
  Point point = popup.getSize();
  // 设置相对父值坐标与长宽
  list.setBounds(1, 1, Math.max(size.x, point.x), len);
 }

 public boolean getEditable() {
  return text.getEditable();
 }

 private void dropDown(boolean drop) {
  if (drop == isDropped()) {
   return;
  }
  if (!drop) {
   popup.setVisible(false);
   text.setFocus();
   return;
  }
  Rectangle txtRect = text.getBounds();
  Rectangle listRect = new Rectangle(txtRect.x, +txtRect.y
    + txtRect.height, txtRect.width, len);
  Point point = getParent().toDisplay(getLocation());
  Point comboSize = getSize();
  int width = Math.max(comboSize.x, listRect.width);
  layoutList();
  popup.setBounds(point.x, point.y + listRect.y, width, listRect.height);
  popup.setVisible(true);
 }

 private boolean isDropped() {
  return popup.getVisible();
 }

 /**
  * Style cheking
  * 
  * @param style
  *            STT style
  * 
  * @return style
  */
 public static int checkStyle(int style) {
  int mask = SWT.BORDER | SWT.READ_ONLY | SWT.FLAT;
  return style & mask;
 }

 private void initAccessible() {
  getAccessible().addAccessibleListener(new AccessibleAdapter() {
   public void getHelp(AccessibleEvent e) {
    e.result = getToolTipText();
   }
  });

  getAccessible().addAccessibleControlListener(
    new AccessibleControlAdapter() {
     public void getChildAtPoint(AccessibleControlEvent e) {
      Point testPoint = toControl(new Point(e.x, e.y));

      if (getBounds().contains(testPoint)) {
       e.childID = ACC.CHILDID_SELF;
      }
     }

     public void getChildCount(AccessibleControlEvent e) {
      e.detail = 0;
     }

     public void getLocation(AccessibleControlEvent e) {
      Rectangle location = getBounds();
      Point pt = toDisplay(new Point(location.x, location.y));
      e.x = pt.x;
      e.y = pt.y;
      e.width = location.width;
      e.height = location.height;
     }

     public void getRole(AccessibleControlEvent e) {
      e.detail = ACC.ROLE_COMBOBOX;
     }

     public void getState(AccessibleControlEvent e) {
      e.detail = ACC.STATE_NORMAL;
     }

     public void getValue(AccessibleControlEvent e) {
      e.result = text.getText();
     }
    });
 }

 /**
  * 重新初始化列表
  * 
  * @param value
  */
 private void setList(String value) {
  if (arrays == null || arrays.size() <= 0)
   return;
  list.removeAll();
  Set<String> keys = arrays.keySet();
  Iterator<String> iterator = keys.iterator();
  while(iterator.hasNext()) {
	    String key = iterator.next();
	    Object object = arrays.get(key);
	    if (key.indexOf(value) < 0)
	    	continue;
	    setData(object);
  }
 }

 /**
  * 初始始化list
  */
 private void initList() {
  if (arrays == null || arrays.size() <= 0)
   return;
  list.removeAll();
  Set<String> keys = arrays.keySet();
  Iterator<String> iterator = keys.iterator();
  while(iterator.hasNext()) {
	    String key = iterator.next();
	    Object object = arrays.get(key);
	    setData(object);
  }
 }

 private void setData(String[] array) {
  list.add(array[0].toString());
  list.setData(array[0].toString(), array[1]);
 }

 /**
  * @param width
  *            the width to set
  */
 public void setWidth(int width) {
  if (width <= 0)
   return;
  Point point = text.getSize();
  point.x = width;

  this.width = width;
 }

 /**
  * @param height
  *            the height to set
  */
 public void setHeight(int height) {
  this.height = height;
 }

 /**
  * @param arrays
  *            the arrays to set
  */
 public void setArrays(HashMap<String,Object> arrays) {
  this.arrays = arrays;
  initList();
 }

 public String getValue() {
  return value;
 }

 public String getKey() {
  return text.getText();
 }
}